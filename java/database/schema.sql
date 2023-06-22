BEGIN TRANSACTION;

-- DROP TABLE IF EXISTS users;

-- DROP TABLE IF EXISTS 

DROP TABLE IF EXISTS users, location, image, award, social, check_in_history, user_award, schedule, category CASCADE;
DROP VIEW IF EXISTS vw_checkin_history;


CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

-- TEAM CREATED

-- Account Table
-- CREATE TABLE account (
-- 	account_id SERIAL,
-- 	user_id int NOT NULL,
	
-- 	CONSTRAINT PK_account_id PRIMARY KEY (account_id)
-- );

-- Location Table
-- REMOVE NOT NULL TO TEST
CREATE TABLE location (
	location_id SERIAL,
	name varchar (100) NOT NULL,
	business_id varchar(100) NOT NULL UNIQUE,
	lat decimal (16,14),
	long decimal (16,14),
	address varchar(100),
	category varchar(50),
	description varchar(200),
	featured boolean,
	image_url varchar(100),
	
	CONSTRAINT PK_location_id PRIMARY KEY (location_id)
);

-- Image Table
CREATE TABLE image(
	image_id SERIAL,
	location_id int NOT NULL,
	image_uri varchar(100) NOT NULL,
	
	CONSTRAINT PK_image_id PRIMARY KEY(image_id)
);

-- Award Table
CREATE TABLE award (
	award_id SERIAL,
	name varchar(100) NOT NULL,
	description varchar(100) NOT NULL,
	icon varchar(100) ,
	
	CONSTRAINT PK_award_id PRIMARY KEY(award_id)
);

-- Social Table
CREATE TABLE social(
	social_id SERIAL,
	name varchar(50) NOT NULL,
	location_id int NOT NULL,
	social_url varchar(100) NOT NULL,
	
	CONSTRAINT PK_social_id PRIMARY KEY (social_id)
);

-- Category table
CREATE TABLE category (
	category_id SERIAL,
	name varchar(50) NOT NULL,
	
	CONSTRAINT PK_category_id PRIMARY KEY (category_id)
);

-- schedule table
CREATE TABLE schedule (
	schedule_id SERIAL,
	location_id int NOT NULL,
	day int NOT NULL,
	hour_from time,
	hour_to time,
	
	CONSTRAINT PK_schedule_id PRIMARY KEY (schedule_id)
);

-- Check-in Table
CREATE TABLE check_in_history(
	user_id int NOT NULL,
	business_id varchar(100) NOT NULL,
	-- design consideration to take out checked_in boolean
-- 	checked_in boolean NOT NULL DEFAULT FALSE,
	

	CONSTRAINT PK_account_id_business_id PRIMARY KEY (user_id, business_id)
);

-- User-Award Table
CREATE TABLE user_award (
	user_id int NOT NULL,
	award_id int NOT NULL,
	CONSTRAINT PK_account_id_award_id PRIMARY KEY (user_id, award_id)
);

-- Location-Category Table
-- CREATE TABLE location_category (
-- 	location_id int NOT NULL,
-- 	category_id int NOT NULL,
	
-- 	CONSTRAINT PK_location_id_category_id PRIMARY KEY (location_id, category_id)
-- );

/**
ALTER TABLES
**/
ALTER TABLE check_in_history
	ADD CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES users (user_id);

ALTER TABLE check_in_history
	ADD CONSTRAINT FK_business_id FOREIGN KEY (business_id) REFERENCES location (business_id);
	
ALTER TABLE image
	ADD CONSTRAINT FK_location_id FOREIGN KEY (location_id) REFERENCES location (location_id);
	
ALTER TABLE social
	ADD CONSTRAINT FK_location_id FOREIGN KEY (location_id) REFERENCES location (location_id);
	
ALTER TABLE user_award
	ADD CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES users (user_id);
	
ALTER TABLE user_award
	ADD CONSTRAINT FK_award_id FOREIGN KEY (award_id) REFERENCES award (award_id);
	
ALTER TABLE schedule
	ADD CONSTRAINT FK_location_id FOREIGN KEY (location_id) REFERENCES location (location_id);
	
-- ALTER TABLE location_category
-- 	ADD CONSTRAINT FK_location_id FOREIGN KEY (location_id) REFERENCES location (location_id);
	
-- ALTER TABLE location_category
-- 	ADD CONSTRAINT FK_category_id FOREIGN KEY (category_id) REFERENCES category (category_id);
--

CREATE VIEW vw_checkin_history AS
SELECT
	cih.user_id, l.location_id, l.business_id, l.name, l.lat, l.long, l.address, l.category, l.description, l.featured, l.image_url
FROM
	check_in_history cih
	INNER JOIN location l
		ON cih.business_id = l.business_id;
		
--ROLLBACK TRANSACTION;
		
COMMIT TRANSACTION;
