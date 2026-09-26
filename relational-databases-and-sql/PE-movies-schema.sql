
DROP DATABASE IF EXISTS movies;
CREATE DATABASE movies;
USE movies;

-- extended character set?
CREATE TABLE genre (
	genreId 	INT AUTO_INCREMENT PRIMARY KEY,
    genreName	VARCHAR(30) NOT NULL
);

CREATE TABLE director (
	directorId 	INT AUTO_INCREMENT PRIMARY KEY,
    firstName	VARCHAR(30) NOT NULL,
    lastName	VARCHAR(30) NOT NULL,
    birthDate	DATE
);

-- standard character set
CREATE TABLE rating (
	ratingId	INT AUTO_INCREMENT PRIMARY KEY,
    ratingName	CHAR(5)
);

CREATE TABLE actor (
	actorId		INT AUTO_INCREMENT PRIMARY KEY,
    firstName	VARCHAR(30) NOT NULL,
    lastName	VARCHAR(30) NOT NULL,
    birthDate	DATE
);

CREATE TABLE movie (
	movieId			INT AUTO_INCREMENT PRIMARY KEY,
    genreId			INT NOT NULL,
    directorId		INT,
    ratingId		INT,
    title			VARCHAR(128) NOT NULL,
    releaseDate		DATE,
    
    -- Brackets needed on FK here.
    CONSTRAINT fk_movie_genre FOREIGN KEY (genreId) REFERENCES genre (genreId),
    CONSTRAINT fk_movie_director FOREIGN KEY (directorId) REFERENCES director (directorId),
    CONSTRAINT fk_movie_rating FOREIGN KEY (ratingId) REFERENCES rating (ratingId)
);

CREATE TABLE castMember (
	castMemberId 	INT AUTO_INCREMENT PRIMARY KEY,
    actorId			INT NOT NULL,
    movieId			INT NOT NULL,
    role			VARCHAR(50) NOT NULL,
    
    CONSTRAINT fk_castMember_actor FOREIGN KEY (actorId) REFERENCES actor (actorId),
	CONSTRAINT fk_castMember_movie FOREIGN KEY (movieId) REFERENCES movie (movieId)
);

SHOW TABLES;