package com.shilpashetty.Tripplan.databasemodel;

import java.util.Date;
import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import com.shilpashetty.Tripplan.appmodel.Chat;
import com.shilpashetty.Tripplan.appmodel.ChatMapper;
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

	@SqlUpdate("UPDATE tripplan.user_info SET first_name = :firstName , last_name = :lastName , email = :email where id= :id")
	void updateUser(@Bind("id") Integer id, @Bind("firstName") String firstName,@Bind("lastName") String lastName, @Bind("email") String email);

	@SqlUpdate("UPDATE tripplan.trip_info SET name = :name , startdate = :startDate, enddate = :endDate , organizer= :organizerId where id= :id")
	void updateTripInfo(@Bind("id") Integer id, @Bind("name") String name, @Bind("startDate") Date startDate, @Bind("endDate") Date endDate, @Bind("organizerId") Integer organizerId);

	@SqlQuery("SELECT location_name from tripplan.location where location_name = :locationName and trip_id = :id")
	String checkLocation(@Bind("locationName") String locationName, @Bind("id") Integer id);

	@SqlQuery("SELECT location_name from tripplan.location where trip_id = :id")
	List<String> getLocation(@Bind("id") Integer id);

	@SqlUpdate("DELETE FROM tripplan.location where trip_id = :id and location_name = :locationName")
	void deleteLocation(@Bind("id") Integer id, @Bind("locationName") String locationName);

	@SqlQuery("SELECT user_id FROM tripplan.user_trip WHERE trip_id = :id")
	List<Integer> getUserId(@Bind("id") Integer id);

	@SqlUpdate("DELETE FROM tripplan.user_trip WHERE trip_id = :id and user_id = :userId")
	void deleteUser(@Bind("id") Integer id, @Bind("userId") Integer userId);

	@Mapper(ChatMapper.class)
	@SqlQuery("SELECT trip_id,concat(first_name,' ',last_name) as 'creator', text, unix_timestamp(created_date) as'created_date' FROM tripplan.chat C INNER JOIN tripplan.user_info UI ON C.creator = UI.id WHERE trip_id = 1 ORDER BY created_date")
	List<Chat> getChatById(@Bind("id") Integer id);

	@SqlUpdate("INSERT INTO tripplan.chat(trip_id,creator,created_date,text) VALUES(:trip_id, :creator, from_unixtime(:created_date), :text)")
	void setChat(@Bind("trip_id") Integer tripId, @Bind("creator") Integer creator, @Bind("text") String text, @Bind("created_date") long createdDate);
}

