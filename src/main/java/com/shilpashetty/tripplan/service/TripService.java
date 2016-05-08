package com.shilpashetty.Tripplan.service;
import com.codahale.metrics.annotation.Timed;
import com.shilpashetty.Tripplan.appmodel.Trip;
import com.shilpashetty.Tripplan.appmodel.User;
import com.shilpashetty.Tripplan.databasemodel.TripDAO;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.eclipse.jetty.util.log.Log;
import org.skife.jdbi.v2.DBI;


@Path("/trip")
public class TripService {
	private DBI jdbi = null;
	public TripService(){

	}
	public TripService(DBI jdbi){
		this.jdbi = jdbi;
	}
	@GET
	@Timed
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Trip getTrip(@PathParam("id") int id) {
		final TripDAO dao = jdbi.onDemand(TripDAO.class);
		List<Trip> trip = dao.getTripById(id);
		//Trip newTrip = new Trip(id, trip.getName(),trip.getStartdate(),trip.getEnddate(),trip.getOrganizer());
		//trip.setId(2);
		return trip.get(0);

	}
	

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Integer setTrip(Trip trip){
		final TripDAO dao = jdbi.onDemand(TripDAO.class);
		Integer organizerId = dao.checkUser(trip.getOrganizer().getEmail());
		if (organizerId == null){
			organizerId = dao.setUser(trip.getOrganizer().getFirstName(), trip.getOrganizer().getLastName(), trip.getOrganizer().getEmail());
			//organizerId = dao.getOrgId();
		}
		Integer tripId = dao.setTrip(trip.getName(), trip.getStartdate(), trip.getEnddate(), organizerId);
//		Integer tripId = dao.getTripId();

		System.out.println(tripId.toString());
		List<String> locations = trip.getLocation_name();
		for (String location:locations){
			System.out.println(location);
			dao.setLocation(location, tripId);
		}
		
		for(User user:trip.getInvitees()){
			Integer userId = dao.checkUser(user.getEmail());
			if (userId == null){
				 userId = dao.setUser(user.getFirstName(), user.getLastName(), user.getEmail());
				//userId = dao.getOrgId();
			}
			dao.setUserTrip(userId, tripId);
		}
		return tripId;
		
	}


}
