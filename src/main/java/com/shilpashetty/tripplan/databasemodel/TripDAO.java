package com.shilpashetty.Tripplan.databasemodel;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import com.shilpashetty.Tripplan.appmodel.Trip;
import com.shilpashetty.Tripplan.appmodel.TripMapper;
@RegisterMapper(TripMapper.class)
public interface TripDAO {
	@SqlQuery("select id,name,startdate,enddate,organizer from tripplan.trip_info where id = :id")
	Trip getNameById(@Bind("id") Integer id);
}
