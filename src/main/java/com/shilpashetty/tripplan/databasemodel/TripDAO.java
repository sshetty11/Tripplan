package com.shilpashetty.Tripplan.databasemodel;

import java.util.Date;
import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import com.shilpashetty.Tripplan.appmodel.Trip;
import com.shilpashetty.Tripplan.appmodel.TripMapper;
import com.shilpashetty.Tripplan.appmodel.User;
@RegisterMapper(TripMapper.class)
public interface TripDAO {
	@SqlQuery("SELECT name,startdate,enddate,ui.first_name as 'org_fname',ui.last_name as 'org_lname',"+
				"ui.email as 'org_email',location_name,att.first_name as 'attfname',att.last_name as 'attlname',"+
				"att.email as 'attemail'FROM tripplan.trip_info ti INNER JOIN tripplan.user_info ui ON ti.organizer = ui.id "+
					 "INNER JOIN tripplan.location l ON l.trip_id = ti.id  INNER JOIN tripplan.user_trip ut ON ut.trip_id=ti.id INNER JOIN  "
					 +"tripplan.user_info att ON att.id=ut.user_id where ti.id  = :id")
	 List<Trip> getTripById(@Bind("id") Integer id);
	
	@SqlQuery("Select id from tripplan.user_info where email=:email")
	Integer checkUser(@Bind("email") String email);
	
	@SqlQuery("Select id from tripplan.user_info where email=any(:email)")
	List<Integer> checkUserList(@Bind("email") List<String> email);
	
	@SqlUpdate("INSERT INTO tripplan.user_info(first_name, last_name, email) VALUES(:firstName, :lastName, :email)")              
	@GetGeneratedKeys
	Integer setUser(@Bind("firstName") String firstName,@Bind("lastName") String lastName, @Bind("email") String email);

	
	@SqlUpdate("INSERT INTO tripplan.trip_info(name, startdate, enddate, organizer) VALUES(:name, :startDate, :endDate, :organizerId)")
	@GetGeneratedKeys
	Integer setTrip(@Bind("name") String name, @Bind("startDate") Date startDate, @Bind("endDate") Date endDate, @Bind("organizerId") Integer organizerId);
	
	@SqlQuery("SELECT last_insert_id()")
	Integer getTripId();
	
	@SqlUpdate("INSERT INTO tripplan.location(trip_id,location_name) VALUES(:tripId,:location)")
	void setLocation(@Bind("location") String location,@Bind("tripId") Integer tripId);
	
	@SqlUpdate("INSERT INTO tripplan.user_trip(trip_id,user_id) VALUES(:tripId,:userId)")
	void setUserTrip(@Bind("userId") Integer userId, @Bind("tripId") Integer tripId);
	
}

