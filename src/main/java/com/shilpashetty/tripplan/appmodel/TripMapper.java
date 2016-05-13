package com.shilpashetty.Tripplan.appmodel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

public class TripMapper implements ResultSetMapper<Trip>
{
	private Trip trip;

	public Trip map(int index, ResultSet r, StatementContext ctx) throws SQLException
	{
		if (index == 0){
			User organizer = new User(r.getString("org_fname"),r.getString("org_lname"),r.getString("org_email"));
			trip = new Trip(r.getString("name"),r.getDate("startdate"),r.getDate("endDate"), organizer, new ArrayList<String>(), new ArrayList<User>());
		}
		User attendee = new User(r.getString("attfname"), r.getString("attlname"), r.getString("attemail"));
		if (!trip.getInvitees().contains(attendee)){
			trip.getInvitees().add(attendee);
		}
		String location = r.getString("location_name");
		if (!trip.getLocation_name().contains(location)){
			trip.getLocation_name().add(location);
		}
		return trip;
	}


}