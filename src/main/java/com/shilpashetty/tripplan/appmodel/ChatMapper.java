package com.shilpashetty.Tripplan.appmodel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

public class ChatMapper implements ResultSetMapper<Chat>{
	private Chat chat;
	public Chat map(int index, ResultSet r, StatementContext ctx) throws SQLException
	{
		chat = new Chat(r.getInt("trip_id"),r.getString("creator"), r.getString("text"), r.getLong("created_date") );
		return chat;
	}

}
