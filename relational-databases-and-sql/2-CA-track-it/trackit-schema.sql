DROP DATABASE IF EXISTS TrackIt; -- careful, deletes any data too.
-- so only run if you need to rebuild and restart db from beginning.

CREATE DATABASE TrackIt;
USE TrackIt; -- Using correct db before creating schema

-- can use PRIMARY KEY on field when only 1 pk.

CREATE TABLE Project (
    ProjectId CHAR(50) PRIMARY KEY, -- use meaningful values to identify projects.
    `Name` VARCHAR(100) NOT NULL,
    Summary VARCHAR(2000) NULL,
    DueDate DATE NOT NULL,
    IsActive BOOL NOT NULL DEFAULT 1 -- default value 1 if we don't set a value.
);

CREATE TABLE Worker (
    WorkerId INT PRIMARY KEY AUTO_INCREMENT, -- autoincremented by mysql when data added.
    FirstName VARCHAR(50) NOT NULL,
    LastName VARCHAR(50) NOT NULL
);

CREATE TABLE ProjectWorker (
    ProjectId CHAR(50) NOT NULL,
    WorkerId INT NOT NULL,
    -- composite pk, have to use separate pk definition to include all appropriate fields.
    PRIMARY KEY pk_ProjectWorker (ProjectId, WorkerId),
    FOREIGN KEY fk_ProjectWorker_Project (ProjectId)
        REFERENCES Project(ProjectId),
    FOREIGN KEY fk_ProjectWorker_Worker (WorkerId)
        REFERENCES Worker(WorkerId)
);

CREATE TABLE Task (
    TaskId INT PRIMARY KEY AUTO_INCREMENT,
    Title VARCHAR(100) NOT NULL,
    Details TEXT NULL,
    DueDate DATE NOT NULL,
    EstimatedHours DECIMAL(5, 2) NULL,
    ProjectId CHAR(50) NOT NULL,
    WorkerId INT NOT NULL,
    -- The whole PK is there Composite FK here. wow
    FOREIGN KEY fk_Task_ProjectWorker (ProjectId, WorkerId)
        REFERENCES ProjectWorker(ProjectId, WorkerId)
);

-- Check DB exists
SHOW DATABASES;
SHOW TABLES;

-- Adding data -- ------------------------------

-- Works as long as WorkerId 1 doesn't exist.
-- Will see "1 row(s) affected".
-- The second time: "Error Code 1062. Duplicate entry '1' for key 'PRIMARY'"
-- Cannot have 2 records with same primary key value in same table.
INSERT INTO Worker (WorkerId, FirstName, LastName) VALUES 
	(1, 'Rosemonde', 'Featherbie');
    
-- Actually its auto-increment, so don't need to include id.
-- If this is second worker inserted, they receive id 2.
INSERT INTO Worker (FirstName, LastName) VALUES 
	('Kingsly', 'Besantie');
    
-- View table contents
SELECT * FROM Worker;

-- Insert multiple rows
INSERT INTO Worker (FirstName, LastName) VALUES
	('Goldi', 'Pilipets'),
    ('Dorey', 'Rulf'),
    ('Panchito', 'Ashtonhurst');
    
-- ProjectId is assigned (not generated), so we can specify value
-- Summary is nullable, so we can safely omit the column name and value
-- IsActive has default value 1, so we can omit the column name and value if happy.
INSERT INTO Project (ProjectId, `Name`, DueDate) VALUES
	('db-milestone', 'Database Material', '2018-12-31');
    
-- Assign worker to project, by inserting values to project worker table.
-- Has to exist - referential integrity
	-- Otherwise: Error Code: 1452. Cannot add or update a child row:
	-- 			  a foreign key constraint fails (`trackit`.`projectworker`,
    -- 			  CONSTRAINT `fk_ProjectWorker_Worker` FOREIGN KEY (`WorkerId`) 
	--            REFERENCES `worker`(`workerid`))	 
    -- One way relational databases protect you from bad data.
INSERT INTO ProjectWorker (ProjectId, WorkerId) VALUES
	('db-milestone', 2);
    
SELECT * FROM ProjectWorker;

-- Field should exist ofc
UPDATE Project SET 
	Summary = 'All lessons and exercises for the relational database milestone.',
    DueDate = '2018-10-15'
WHERE ProjectId = 'db-milestone';

UPDATE Worker SET
	LastName = 'Oaks'
WHERE WorkerId = 2;

-- Move assignments from 1 worker to another
UPDATE ProjectWorker SET
	WorkerId = '5'
WHERE WorkerId = '2';
-- can do since the other part of the PK is word
-- when previewing, row order changed bc db engine w/ index.
-- found kitchen + 5 comes later than kitchen + 2.

-- Disable safe updates
SET SQL_SAFE_UPDATES = 0;

UPDATE Project SET
	IsActive = 0
WHERE DueDate BETWEEN '2017-01-01' AND '2017-12-31'
AND IsActive = 1;
-- Error Code: 1175. You are using safe update mode and you tried to update a table without a WHERE that uses a KEY column. 
-- To disable safe mode, toggle the option in Preferences -> SQL Editor and reconnect.
-- Or with statement

UPDATE Task SET
	EstimatedHours = EstimatedHours * 1.25
WHERE WorkerId = 2;

-- Enable safe updates
SET SQL_SAFE_UPDATES = 1;

-- Delete
DELETE FROM Worker
WHERE WorkerId = 50;
-- "X row(s) affected" can be 0 if none effected, but still a success.

-- Referential integrity
-- Cant remove PK row if referenced in another table
-- DELETE FROM Worker
-- WHERE WorkerId = 5;
-- Error Code: 1451. Cannot delete or update a parent row: 
-- 		a foreign key constraint fails (`trackit`.`projectworker`, 
-- 		CONSTRAINT `projectworker_ibfk_2` FOREIGN KEY (`WorkerId`) 
-- 		REFERENCES `worker` (`WorkerId`))

-- Have to delete all records that reference the PK first

SET SQL_SAFE_UPDATES = 0; -- Even have to disable for FK here
DELETE FROM Task WHERE WorkerId = 5; 
DELETE FROM ProjectWorker WHERE WorkerId = 5;
DELETE FROM Worker WHERE WorkerId = 5;
SET SQL_SAFE_UPDATES = 1;

-- View
SELECT * FROM Task;
SELECT * FROM ProjectWorker;
SELECT * FROM Worker;