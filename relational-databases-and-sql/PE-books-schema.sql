
DROP SCHEMA IF EXISTS books;
CREATE SCHEMA books;
USE books;

DROP TABLE IF EXISTS author;
CREATE TABLE author (
	authorId	 	INT AUTO_INCREMENT PRIMARY KEY,
    firstName		VARCHAR(25) NOT NULL,
    middleName		VARCHAR(25),
    lastName		VARCHAR(50) NOT NULL,
    gender			CHAR(1),
    dateOfBirth		DATETIME NOT NULL,
    dateOfDeath		DATETIME
);

DROP TABLE IF EXISTS book;
CREATE TABLE book (
	bookId 			INT AUTO_INCREMENT PRIMARY KEY,
    title			VARCHAR(100) NOT NULL,
    publicationDate DATETIME
);

DROP TABLE IF EXISTS format;
CREATE TABLE format (
	formatId 		INT AUTO_INCREMENT PRIMARY KEY,
    formatName 		CHAR(12) NOT NULL
);

DROP TABLE IF EXISTS genre;
CREATE TABLE genre (
	genreId 	INT AUTO_INCREMENT PRIMARY KEY,
    genreName 	VARCHAR(25) NOT NULL
);

DROP TABLE IF EXISTS authorBook;
CREATE TABLE authorBook (
	authorId 	INT,
    bookId		INT,
    
	CONSTRAINT pk_authorBook PRIMARY KEY (authorId, bookId),
    CONSTRAINT fk_authorBook_author FOREIGN KEY (authorId) REFERENCES author(authorId),
	CONSTRAINT fk_authorBook_book FOREIGN KEY (bookId) REFERENCES book(bookId)
);

DROP TABLE IF EXISTS bookFormat;
CREATE TABLE bookFormat (
	bookId			INT,
    formatId		INT,
    price			DOUBLE(5,2),
    quantityOnHand	INT,
    
	CONSTRAINT pk_bookFormat PRIMARY KEY (bookId, formatId),
	CONSTRAINT fk_bookFormat_book FOREIGN KEY (bookId) REFERENCES book(bookId),
	CONSTRAINT fk_bookFormat_format FOREIGN KEY (formatId) REFERENCES format(formatId)
);

DROP TABLE IF EXISTS bookGenre;
CREATE TABLE bookGenre (
	bookId		INT,
    genreId		INT,
    
    CONSTRAINT pk_bookGenre PRIMARY KEY (bookId, genreId),
	FOREIGN KEY fk_bookGenre_book (bookId) REFERENCES book(bookId),
    CONSTRAINT FOREIGN KEY fk_bookGenre_genre (genreId) REFERENCES genre(genreId) 
    -- Can also reorder, and even omit constraint
);

SHOW TABLES;