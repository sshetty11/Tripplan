package com.shilpashetty.Tripplan.appmodel;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

public class TripMapper implements ResultSetMapper<Trip>
{
	public Trip map(int index, ResultSet r, StatementContext ctx) throws SQLException
	{
		return new Trip(r.getInt("id"), r.getString("name"), r.getDate("startdate"), r.getDate("enddate"), r.getString("organizer"));
	}
}