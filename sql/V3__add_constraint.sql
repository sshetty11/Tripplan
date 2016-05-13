ALTER TABLE tripplan.trip_info
	ADD CONSTRAINT user_info FOREIGN KEY(organizer) REFERENCES tripplan.user_info(id)
