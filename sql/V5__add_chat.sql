CREATE TABLE tripplan.chat(
	chat_id INT NOT NULL AUTO_INCREMENT,
	trip_id INT NOT NULL,
	creator INT NOT NULL,
	created_date datetime,
	text VARCHAR(1000),
	PRIMARY KEY(chat_id),
	FOREIGN KEY(trip_id) REFERENCES tripplan.trip_info(id),
	FOREIGN KEY(creator) REFERENCES tripplan.user_info(id)
	
)