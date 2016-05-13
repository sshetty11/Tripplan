package com.shilpashetty.modeltest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.PathParam;

import org.junit.Assert;
import org.junit.Test;
import org.skife.jdbi.v2.DBI;

import com.shilpashetty.Tripplan.appmodel.Chat;
import com.shilpashetty.Tripplan.appmodel.Trip;
import com.shilpashetty.Tripplan.appmodel.User;
import com.shilpashetty.Tripplan.databasemodel.TripDAO;
import com.shilpashetty.Tripplan.service.TripService;

import mockit.Expectations;
import mockit.Mocked;

public class TripServiceTest {
	@Mocked 
	DBI dbi;
	@Mocked
	TripDAO dao;

	@Test
	public void testGetTrip() throws Exception {
		Date date = new Date();
		User user = new User("Suhas","Shetty","email");
		List<String> location = new ArrayList<String>();
		location.add("Yosemite");
		List<User> invitees = new ArrayList<User>();
		invitees.add(new User("Shilpa","Shetty","test"));
		final Trip trip = new Trip ("Florida",date,date,user,location,invitees);
		new Expectations() {{
			dbi.onDemand(TripDAO.class); result = dao;
			dao.getTripById(anyInt); result = trip; times = 1;
		}};
		TripService ts = new TripService(dbi);
		Trip result = ts.getTrip(1);
		Assert.assertEquals("Florida", result.getName());
		Assert.assertEquals(date,result.getStartdate());
		Assert.assertEquals(user, result.getOrganizer());
		Assert.assertEquals(location, result.getLocation_name());
		Assert.assertEquals(invitees, result.getInvitees());
	}
	
	@Test
	public void testSetTrip() throws Exception{
		Date date = new Date();
		User user = new User("Suhas", "Shetty", "test@email.com");
		List<String> location = new ArrayList<String>();
		location.add("Yosemite");
		location.add("Castle");
		List<User> invitees = new ArrayList<User>();
		invitees.add(new User("Tyrion", "Lannister", "tryion@email.com"));
		final Trip trip = new Trip ("Florida", date, date, user, location, invitees);
		final Integer organizerId = 5;
		final Integer tripId = 3;
		final Integer userId = 4;
		new Expectations() {{
			dbi.onDemand(TripDAO.class); result = dao;
			dao.checkUser(trip.getOrganizer().getEmail()); result=organizerId; times=1;
			dao.setUser(trip.getOrganizer().getFirstName(),trip.getOrganizer().getLastName(),trip.getOrganizer().getEmail()); result=1; times = 0;
			dao.setTrip(trip.getName(), trip.getStartdate(), trip.getEnddate(), organizerId); result=tripId; times=1;
			dao.setLocation(anyString, tripId); times=2;
			dao.checkUser(anyString);times = 1 ; result = userId;
			dao.setUser(anyString, anyString, anyString); times = 0; 
			dao.setUserTrip(userId, tripId);times=1;
			
			
		}};
		TripService ts = new TripService(dbi);
		Integer result = ts.setTrip(trip);
		
		
	}
	
	public void testSetTripWithOrganizerIdNull() throws Exception{
		Date date = new Date();
		User user = new User("Suhas", "Shetty", "test@email.com");
		List<String> location = new ArrayList<String>();
		location.add("Yosemite");
		location.add("Castle");
		List<User> invitees = new ArrayList<User>();
		invitees.add(new User("Tyrion", "Lannister", "tryion@email.com"));
		final Trip trip = new Trip ("Florida", date, date, user, location, invitees);
		final Integer organizerId = 5;
		final Integer tripId = 3;
		final Integer userId = 4;
		new Expectations() {{
			dbi.onDemand(TripDAO.class); result = dao;
			dao.checkUser(trip.getOrganizer().getEmail()); result=null; times=1;
			dao.setUser(trip.getOrganizer().getFirstName(),trip.getOrganizer().getLastName(),trip.getOrganizer().getEmail()); result=organizerId; times = 1;
			dao.setTrip(trip.getName(), trip.getStartdate(), trip.getEnddate(), organizerId); result=tripId; times=1;
			dao.setLocation(anyString, tripId); times=2;
			dao.checkUser(anyString);times = 1 ; result = null;
			dao.setUser(anyString, anyString, anyString); times = 1; 
			dao.setUserTrip(userId, tripId);times=1;
			
			
		}};
		TripService ts = new TripService(dbi);
		Integer result = ts.setTrip(trip);
	}
	
	public void updateTripWithUserIdNull() throws Exception{
		Date date = new Date();
		User user = new User("Suhas", "Shetty", "test@email.com");
		List<String> location = new ArrayList<String>();
		location.add("Yosemite");
		location.add("Castle");
		List<User> invitees = new ArrayList<User>();
		invitees.add(new User("Tyrion", "Lannister", "tryion@email.com"));
		final Trip trip = new Trip ("Florida", date, date, user, location, invitees);
		final Integer organizerId = 5;
		final Integer tripId = 3;
		final Integer userId = 4;
		new Expectations() {{
			dbi.onDemand(TripDAO.class); result = dao;
			dao.checkUser(trip.getOrganizer().getEmail()); result = null; times = 1;
			dao.setUser(trip.getOrganizer().getFirstName(),trip.getOrganizer().getLastName(),trip.getOrganizer().getEmail()); result=organizerId; times = 1;
			dao.updateUser(tripId, trip.getOrganizer().getFirstName(), trip.getOrganizer().getLastName(), trip.getOrganizer().getEmail());times = 0;
			dao.updateTripInfo(tripId,trip.getName(), trip.getStartdate(), trip.getEnddate(), organizerId); times=1;
			dao.setLocation(anyString, tripId); times=2;
			dao.deleteLocation(tripId, anyString);times=0;
			dao.checkUser(anyString);times = 1 ; result = null;
			dao.setUser(anyString, anyString, anyString); times = 1; 
			dao.updateUser(tripId, anyString, anyString, anyString);times = 0;
			dao.setUserTrip(userId, tripId);times = 1;
			dao.deleteUser(tripId, userId);times = 0;
			
			
		}};
		TripService ts = new TripService(dbi);
		 ts.updateTrip(tripId,trip);
	}
	
	public void updateTrip() throws Exception{
		Date date = new Date();
		User user = new User("Suhas", "Shetty", "test@email.com");
		List<String> location = new ArrayList<String>();
		location.add("Yosemite");
		location.add("Castle");
		List<User> invitees = new ArrayList<User>();
		invitees.add(new User("Tyrion", "Lannister", "tryion@email.com"));
		final Trip trip = new Trip ("Florida", date, date, user, location, invitees);
		final Integer organizerId = 5;
		final Integer tripId = 3;
		final Integer userId = 4;
		new Expectations() {{
			dbi.onDemand(TripDAO.class); result = dao;
			dao.checkUser(trip.getOrganizer().getEmail()); result = organizerId; times = 1;
			dao.setUser(trip.getOrganizer().getFirstName(),trip.getOrganizer().getLastName(),trip.getOrganizer().getEmail()); result=organizerId; times = 0;
			dao.updateUser(tripId, trip.getOrganizer().getFirstName(), trip.getOrganizer().getLastName(), trip.getOrganizer().getEmail());times = 1;
			dao.updateTripInfo(tripId,trip.getName(), trip.getStartdate(), trip.getEnddate(), organizerId); times=1;
			dao.setLocation(anyString, tripId); times=2;
			dao.deleteLocation(tripId, anyString);times=0;
			dao.checkUser(anyString);times = 1 ; result = userId;
			dao.setUser(anyString, anyString, anyString); times = 0; 
			dao.updateUser(tripId, anyString, anyString, anyString);times = 1;
			dao.setUserTrip(userId, tripId);times = 1;
			dao.deleteUser(tripId, userId);times = 0;
			
			
		}};
		TripService ts = new TripService(dbi);
		 ts.updateTrip(tripId,trip);
	}
	
	public void testGetChat() throws Exception{
		final Integer tripId=4;
		final Chat chat = new Chat(tripId,"shilpa shetty","hey how are ya",341233);
		new Expectations() {{
			dao.getChatById(tripId); result = chat; times = 1;
		}};
		TripService ts = new TripService(dbi);
		 List<Chat> result = ts.getChat(tripId);
		 Assert.assertEquals(tripId, result.get(0).getTripId());
		 Assert.assertEquals("shilpa shetty", result.get(0).getCreator());
		 Assert.assertEquals("hey how are ya", result.get(0).getText());
		 Assert.assertEquals(341233, result.get(0).getCreatedDate());
	}
	
	public void testSetChat() throws Exception{
		final Integer tripId = 4;
		final Integer creatorId = 2;
		final Chat chat = new Chat(tripId,"shilpashetty@email.com","hey how are ya",341233);
		new Expectations() {{
			dao.checkUser(chat.getCreator()); times = 1; result = creatorId;
			dao.setChat(chat.getTripId(), creatorId, chat.getText(), chat.getCreatedDate()); times = 1;
		}};
		TripService ts = new TripService(dbi);
		 ts.setChat(chat);
	}

}



