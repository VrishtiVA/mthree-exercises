
USE personaltrainer;
SHOW TABLES;

-- Client - A personal trainers customers
		 -- Each client has name, address, unique id
-- Workout - A themed schedule of exercises with a set of goals
-- Exerise - A physical activity
		-- - Can be configured per workout
		-- - May run a bit or lot
		-- - Weight amounts and reps can differ
-- Goal - A desired physical or emotional outcome for a client/workout
-- Login - Credentials for the personal trainer application
		-- Clients may/may not have a login
-- ExerciseCategory - A logical group of exercises
					-- Each exercise has a category
                    -- Categories have parent categories
-- Invoice - A dated bill for a client
-- InvoiceLineItem - An itemized charge on an invoice.

-- 1. All from Exercise
SELECT *
FROM Exercise;

-- 2. All form Client
SELECT *
FROM Client;

-- 3. All clients from city metairie
SELECT * 
FROM Client 
WHERE City = 'Metairie';

-- 4. Client with the ClientId '818u7faf-7b4b-48a2-bf12-7a26c92de20c'? 
SELECT *
FROM Client
WHERE ClientId = '818u7faf-7b4b-48a2-bf12-7a26c92de20c';
-- No.

-- 5. How many goals?
SELECT *
FROM Goal;

SELECT Count(*) 'Number of Goals'
FROM Goal;

-- 6. Name and LevelId from Workout
SELECT name, levelid
FROM Workout;

-- 7. Name, LevelId, and Notes from Workout where LevelId is 2. 
SELECT name, levelid, notes
FROM Workout
WHERE levelid = 2;

-- 8. FirstName, LastName, and City from Client where City is Metairie, Kenner, or Gretna
SELECT FirstName, LastName, City
FROM Client
WHERE City IN ('metairie', 'kenner', 'gretna');

-- 9. FirstName, LastName, and BirthDate from Client for Clients born in the 1980s
SELECT FirstName, LastName, BirthDate
FROM Client
WHERE BirthDate BETWEEN '1980-01-01' AND '1989-12-31';

-- 10. 9 but differently
SELECT FirstName, LastName, BirthDate
FROM Client
WHERE BirthDate LIKE '198%';

-- 11. rows in the Login table have a .gov EmailAddress
SELECT *
FROM Login
WHERE EmailAddress LIKE "%.gov";

-- 12. How many Logins do NOT have a .com EmailAddress? 
SELECT *
FROM Login
WHERE EmailAddress NOT LIKE "%.com";

-- 13. first and last name of Clients without a BirthDate?
SELECT firstname, lastname
FROM Client
WHERE birthdate IS NULL;

-- 14. Name of each ExerciseCategory that has a parent
DESCRIBE ExerciseCategory; -- if has parent category id

SELECT Name
FROM ExerciseCategory
WHERE ParentCategoryId IS NOT NULL;

-- 15. Name and Notes of each level 3 Workout that contains the word 'you' in its Notes.
SELECT name, notes
FROM Workout
WHERE levelid = 3 AND notes LIKE "%you%";

-- 16. FirstName, LastName, City from Client whose LastName starts with L,M, or N and who live in LaPlace
SELECT firstname, lastname, city
FROM Client
WHERE (lastname LIKE "L%" OR lastname LIKE "M" OR lastname LIKE "N")
    AND city = 'LaPlace';
    
-- 17. InvoiceId, Description, Price, Quantity, ServiceDate and the line item total, 
	-- a calculated value, from InvoiceLineItem, where the line item total is between 15 and 25 dollars
DESCRIBE InvoiceLineItem;
SELECT 
	invoiceId, 
    description, 
    price, 
    quantity, 
    serviceDate, 
    (price * quantity) AS lineItemTotal
FROM InvoiceLineItem
WHERE (price * quantity) BETWEEN 15 AND 25; 
-- Can't use alias in where, since select not applied yet.

-- 18. Does the database include an email address for the Client, Estrella Bazely
DESCRIBE Client;
DESCRIBE Login;

SELECT 
	Client.ClientId,
	Client.FirstName,
    Client.LastName,
    Login.EmailAddress
FROM Client
JOIN Login ON Client.ClientId = Login.ClientId
WHERE Client.LastName = 'Bazely' AND Client.FirstName = 'Estrella'; -- Yes has.

-- Subquery version
SELECT 
	c.FirstName,
    c.LastName,
    ( 
		SELECT l.EmailAddress
		FROM Login l
        WHERE l.ClientId = c.ClientId
    ) AS EmailAddress
FROM Client c
WHERE c.LastName = 'Bazely' AND c.FirstName = 'Estrella';

-- 19. What are the Goals of the Workout with the Name 'This Is Parkour'?
DESCRIBE Goal;
DESCRIBE Workout;
DESCRIBE WorkoutGoal;

SELECT 
	w.Name AS Workout,
    g.Name AS Goal
FROM Workout w
JOIN WorkoutGoal wg ON w.WorkoutId = wg.WorkoutId
JOIN Goal g ON wg.GoalId = g.GoalId
WHERE w.Name = 'This Is Parkour';