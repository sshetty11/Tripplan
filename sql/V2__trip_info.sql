CREATE TABLE tripplan.trip_info(
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(255),
	startdate DATE,
	enddate DATE,
	organizer INT,
	PRIMARY KEY(id) 
);

CREATE TABLE tripplan.user_info(
	id INT NOT NULL AUTO_INCREMENT,
	first_name VARCHAR(255),
	last_name VARCHAR(255),
	email VARCHAR(500),
	PRIMARY KEY(id)
);

CREATE TABLE tripplan.location(
	trip_id INT ,
	location_name VARCHAR(500),
    FOREIGN KEY(trip_id) REFERENCES tripplan.trip_info(id)
);
