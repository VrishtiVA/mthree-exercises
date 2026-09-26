
-- My own normalised db.
-- I'll use snake case, since sql seems to lowercase everything.
-- And I've also seen this behaviour with JPA to SQL.

DROP SCHEMA IF EXISTS winx_club;
CREATE SCHEMA winx_club;
USE winx_club;

CREATE TABLE element (
	id		INT AUTO_INCREMENT PRIMARY KEY,
    name	VARCHAR(50) NOT NULL
);

CREATE TABLE realm (
	id		INT AUTO_INCREMENT PRIMARY KEY,
    name	VARCHAR(50) NOT NULL
);

CREATE TABLE transformation (
	id		INT AUTO_INCREMENT PRIMARY KEY,
    name	VARCHAR(50) NOT NULL
);

CREATE TABLE school (
	id		INT AUTO_INCREMENT PRIMARY KEY,
    name	VARCHAR(50) NOT NULL
);

CREATE TABLE person (
	id			INT AUTO_INCREMENT PRIMARY KEY,
    gender		CHAR(1),
    first_name	VARCHAR(50),
    last_name	VARCHAR(50) NOT NULL,
    element_id	INT,
    realm_id	INT NOT NULL,
    school_id	INT,
    
    CONSTRAINT fk_person_elementId FOREIGN KEY (element_id) REFERENCES element (id),
    CONSTRAINT fk_person_realmId FOREIGN KEY (realm_id) REFERENCES realm (id),
    CONSTRAINT fk_person_schoolId FOREIGN KEY (school_id) REFERENCES school (id)
);

CREATE TABLE person_transformation (
	transformation_id	INT NOT NULL,
    person_id			INT NOT NULL,
    
    CONSTRAINT pk_personTransformation PRIMARY KEY (transformation_id, person_id),
    CONSTRAINT fk_personTransformation_transformationId 
		FOREIGN KEY (transformation_id) REFERENCES transformation (id),
	CONSTRAINT fk_personTransformation_personId 
		FOREIGN KEY (person_id) REFERENCES person (id)
);

CREATE TABLE friendship (
	initiator_id		INT NOT NULL,
    follower_id			INT NOT NULL,
    
	CONSTRAINT pk_friendship PRIMARY KEY (initiator_id, follower_id),
    CONSTRAINT pk_friendship_initiatorId 
		FOREIGN KEY (initiator_id) REFERENCES person (id),
	CONSTRAINT pk_friendship_followerId
		FOREIGN KEY (follower_id) REFERENCES person (id)
);

SHOW TABLES;