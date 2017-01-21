package com.shilpashetty.Tripplan.service;
import com.codahale.metrics.annotation.Timed;
import com.shilpashetty.Tripplan.appmodel.Chat;
import com.shilpashetty.Tripplan.appmodel.Trip;
import com.shilpashetty.Tripplan.appmodel.User;
import com.shilpashetty.Tripplan.databasemodel.TripDAO;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.MediaType;

import org.skife.jdbi.v2.DBI;


@Path("/trip")
public class TripService {
	private DBI jdbi = null;
	public TripService(){

	}
	public TripService(DBI jdbi){
		this.jdbi = jdbi;
	}

	//Get all the trip data
	@GET
	@Timed
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Trip getTrip(@PathParam("id") int id) {
		final TripDAO dao = jdbi.onDemand(TripDAO.class);
		List<Trip> trip = dao.getTripById(id);
		if (trip.isEmpty()) {
			throw new NotFoundException();
		}
		return trip.get(0);
	}

	//get all the chat data
	@GET
	@Timed
	@Path("/{id}/chat")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Chat> getChat(@PathParam("id") int id) {
		final TripDAO dao = jdbi.onDemand(TripDAO.class);
		List<Chat> chat = dao.getChatById(id);
		return chat;
	}

	//set the chat data
	@POST
	@Path("/chat")
	@Consumes(MediaType.APPLICATION_JSON)
	public void setChat(Chat chat) {
		final TripDAO dao = jdbi.onDemand(TripDAO.class);
		Integer creatorId = dao.checkUser(chat.getCreator());
		dao.setChat(chat.getTripId(),creatorId,chat.getText(),chat.getCreatedDate());
	}

	//set all trip data
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Integer setTrip(Trip trip) {
		final TripDAO dao = jdbi.onDemand(TripDAO.class);
		//check if the user already exists
		Integer organizerId = dao.checkUser(trip.getOrganizer().getEmail());
		if (organizerId == null){
			//If the organizer is not present in the db add the user
			organizerId = dao.setUser(trip.getOrganizer().getFirstName(), trip.getOrganizer().getLastName(), trip.getOrganizer().getEmail());

		}
		//Add data to trip table
		Integer tripId = dao.setTrip(trip.getName(), trip.getStartdate(), trip.getEnddate(), organizerId);

		List<String> locations = trip.getLocation_name();
		for (String location:locations){
			//Add location
			dao.setLocation(location, tripId);
		}

		for(User user:trip.getInvitees()){
			Integer userId = dao.checkUser(user.getEmail());
			if (userId == null){
				//If invitee is not present in db add to the user table
				userId = dao.setUser(user.getFirstName(), user.getLastName(), user.getEmail());

			}
			//Set user association to the trip
			dao.setUserTrip(userId, tripId);
		}
		return tripId;

	}

	//update trip data
	@POST
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateTrip(@PathParam("id") Integer id, Trip trip) {
		final TripDAO dao = jdbi.onDemand(TripDAO.class);
		//check if the user already exists
		Integer organizerId = dao.checkUser(trip.getOrganizer().getEmail());
		if (organizerId == null){
			//If organizer is not present in db add to the user table
			organizerId = dao.setUser(trip.getOrganizer().getFirstName(), trip.getOrganizer().getLastName(), trip.getOrganizer().getEmail());

		}else{
			//If organizer is  present in db then update
			dao.updateUser(organizerId,trip.getOrganizer().getFirstName(), trip.getOrganizer().getLastName(), trip.getOrganizer().getEmail());
		}

		//update trip table
		dao.updateTripInfo(id, trip.getName(), trip.getStartdate(),trip.getEnddate(), organizerId);

		List<String> locations = trip.getLocation_name();
		List<String> dbLocations = dao.getLocation(id);
		for (String location:locations){
			//If location is not already present in the db  add it
			if (!dbLocations.contains(location)){
				dao.setLocation(location, id);
			}
		}

		for (String location:dbLocations){
			if(!locations.contains(location)){
				//If the existing location is deleted then delete from db
				dao.deleteLocation(id, location);
			}
		}

		List<Integer> dbUserIds = dao.getUserId(id);
		List<Integer> userIdList = new ArrayList<Integer>();
		for(User user:trip.getInvitees()){
			Integer userId = dao.checkUser(user.getEmail());
			if (userId == null){
				//If invite is not present in db add to the user table
				userId = dao.setUser(user.getFirstName(), user.getLastName(), user.getEmail());
			}else{
				//If invite is already present then update it
				dao.updateUser(userId, user.getFirstName(), user.getLastName(), user.getEmail());
			}
			if(!dbUserIds.contains(userId)){
				//update the user association in db if not already present
				dao.setUserTrip(userId, id);
			}
			userIdList.add(userId);
		}

		for (Integer userId:dbUserIds){
			if(!userIdList.contains(userId)){
				//Delete user association which are no longer present.
				dao.deleteUser(id, userId);
			}

		}
	}

}
