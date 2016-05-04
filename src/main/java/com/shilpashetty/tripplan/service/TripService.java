package com.shilpashetty.Tripplan.service;
import com.codahale.metrics.annotation.Timed;
import com.shilpashetty.Tripplan.appmodel.Trip;
import com.shilpashetty.Tripplan.databasemodel.TripDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.skife.jdbi.v2.DBI;

import java.util.List;

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
		Trip trip = dao.getNameById(id);
		Trip newTrip = new Trip(id, trip.getName(),trip.getStartdate(),trip.getEnddate(),trip.getOrganizer());
		return newTrip;

	}


}
