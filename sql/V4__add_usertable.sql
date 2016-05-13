CREATE TABLE tripplan.user_trip(
		trip_id INT NOT NULL,
		user_id INT NOT NULL,
		FOREIGN KEY(trip_id) REFERENCES tripplan.trip_info(id),
		FOREIGN KEY(user_id) REFERENCES tripplan.user_info(id)
)