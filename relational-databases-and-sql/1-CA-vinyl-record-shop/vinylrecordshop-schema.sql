-- VrishtiVA
-- 24/09/2026

-- Create the database, ensure starting empty
-- Without if exists, it will error if doesn't exist.
DROP DATABASE IF EXISTS vinylrecordshop;
-- SHOW DATABASES;

CREATE DATABASE vinylrecordshop;

-- Activate database to use
USE vinylrecordshop;

-- Create primary tables first
-- Using camelCase.
-- Field order not really matter, but PK first can help speed retrieval.
-- Less important for FK, but some db designers put them right after PK.
-- Cannot have multiple PK using the PRIMARY KEY shortcut on field.
-- Commas to differentiate items.

CREATE TABLE album (
	albumId 		INT AUTO_INCREMENT, -- auto incrementing field
    albumTitle 		VARCHAR(100) NOT NULL, -- required
    label			VARCHAR(50),
    releaseDate		DATE,
    price			DECIMAL(5,2), -- 5 digits, 2dp (max value is 999.99)
    
    CONSTRAINT pk_album PRIMARY KEY (albumId) -- No comma on end
);
-- DESCRIBE album; -- Can use to verify table is defined correctly.

CREATE TABLE band (
	bandId			INT AUTO_INCREMENT,
    bandName		VARCHAR(50) NOT NULL,
    
    CONSTRAINT pk_band PRIMARY KEY (bandId)
);

CREATE TABLE artist (
	artistId		INT AUTO_INCREMENT,
    artistFirstName	VARCHAR(25),
    artistLastName	VARCHAR(50) NOT NULL,
    
    CONSTRAINT pk_artist PRIMARY KEY (artistId)
);

-- Create the related tables, with foreign keys
-- Name FK constraints with both tables, 
		-- to ensure constraint name unique,
        -- and support documentation purposes.

DROP TABLE IF EXISTS song;
CREATE TABLE song (
	songId 		INT AUTO_INCREMENT,
    songTitle	VARCHAR(100) NOT NULL,
    videoUrl	VARCHAR(100),
    bandId		INT NOT NULL, -- FK as normal field first
    
    CONSTRAINT pk_song 
		PRIMARY KEY (songId),
    CONSTRAINT fk_song_band -- Then add the FK constraint
		FOREIGN KEY (bandId) 
		REFERENCES band(bandId)
);

-- Has composite primary key
-- Has dependency on 2 separate tables that should exist first
-- Both are not null but that will be auto required on pk constraint
		-- due to entity integrity.

DROP TABLE IF EXISTS songAlbum;
CREATE TABLE songAlbum (
	songId		INT, -- no auto increment ofc, this is a FK
    albumId		INT,
    
    -- PK constraint includes all fields included in the PK, comma sep.
    CONSTRAINT pk_songAlbum PRIMARY KEY (songId, albumId),
    
    -- FKs
    CONSTRAINT fk_songAlbum_song 
		FOREIGN KEY (songId) 
        REFERENCES song(songId),
	CONSTRAINT fk_songAlbum_album
		FOREIGN KEY (albumId)
        REFERENCES album(albumId)
);

DROP TABLE IF EXISTS bandArtist;
CREATE TABLE bandArtist (
	bandId		INT,
    artistId	INT,
    
    CONSTRAINT pk_bandArtist 
		PRIMARY KEY (bandId, artistId),
    CONSTRAINT fk_bandArtist_band 
		FOREIGN KEY (bandId)
        REFERENCES band(bandId),
	CONSTRAINT fk_bandArtist_artist 
		FOREIGN KEY (artistId)
        REFERENCES artist(artistId)
);

-- Display what done
SHOW TABLES;