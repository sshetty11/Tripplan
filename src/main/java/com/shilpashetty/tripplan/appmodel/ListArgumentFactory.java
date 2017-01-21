package com.shilpashetty.Tripplan.appmodel;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.List;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.Argument;
import org.skife.jdbi.v2.tweak.ArgumentFactory;

/**
 * Implementing custom List ArgumentFactory in order use 'IN' as part of SQL query with JDBI
 * 
 * @author shilpa
 *
 */
public class ListArgumentFactory implements ArgumentFactory<List> {

	public boolean accepts(Class<?> paramType, Object value, StatementContext ctx) {
		return value instanceof List;
	}

	public Argument build(Class<?> paramType, final List paramValue, StatementContext ctx) {
		return new Argument() {
			public void apply(int paramPosition, PreparedStatement statement, StatementContext ctx) throws SQLException {
				String paramType = null;
				if (paramValue.get(0).getClass() == String.class) {
					paramType = "varchar";
				} else if(paramValue.get(0).getClass() == Integer.class){
					paramType = "integer";
                } else {
                    throw new SQLException();
                }
				Array values = ctx.getConnection().createArrayOf(paramType, paramValue.toArray());
				statement.setArray(paramPosition, values);
			}
		};
	}

}