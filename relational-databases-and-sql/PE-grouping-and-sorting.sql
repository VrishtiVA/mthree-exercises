
USE PersonalTrainer;
SHOW TABLES;

-- 1. count the number of Clients.
SELECT COUNT(*) AS ClientCount
FROM Client; -- 500
-- Counts number of client records.

-- 2. count Client.BirthDate
SELECT COUNT(Client.BirthDate)
FROM Client; -- 463
-- Different since doesn't count nulls.

-- 3. Group Clients by City and count them
SELECT city, COUNT(*) AS client_count
FROM Client
GROUP BY city
ORDER BY client_count DESC;

-- 4. total per invoice using only the InvoiceLineItem table.
DESCRIBE InvoiceLineItem;

SELECT invoiceid, SUM(price * quantity) AS invoice_total
FROM InvoiceLineItem
GROUP BY invoiceid;

-- 5. Previous, Only include totals greater than $500.00.
-- Sort from lowest total to highest
SELECT invoiceid, SUM(price * quantity) AS invoice_total
FROM InvoiceLineItem
GROUP BY invoiceid
HAVING invoice_total > 500
ORDER BY invoice_total;

-- 6. average line item total, grouped by InvoiceLineItem.Description
SELECT description, AVG(price * quantity) AS invoice_average
FROM InvoiceLineItem
GROUP BY description;

-- 7. ClientId, FirstName, and LastName from Client 
-- for clients who have paid over $1000 total
SELECT c.ClientId, c.FirstName, c.LastName, SUM(ili.price * ili.quantity) TotalPaid
FROM Client c
INNER JOIN Invoice i ON c.ClientId = i.ClientId
INNER JOIN InvoiceLineItem ili ON i.InvoiceId = ili.InvoiceId
WHERE i.InvoiceStatus = 2
GROUP BY c.ClientId
HAVING TotalPaid > 1000
ORDER BY c.LastName, c.FirstName;

-- 8. Count exercises by category.
DESCRIBE ExerciseCategory;
SELECT ec.Name Category, Count(*) ExerciseCount
FROM Exercise e
INNER JOIN ExerciseCategory ec ON e.ExerciseCategoryId = ec.ExerciseCategoryId
GROUP BY Category
ORDER BY ExerciseCount DESC;

-- 9. Exercise.Name along with the minimum, maximum, and average ExerciseInstance.Sets
DESCRIBE Exercise;
DESCRIBE ExerciseInstance;

-- Even without space before alias can work if there is bracket.
SELECT e.name Exercise, MIN(ei.sets) MinSets, MAX(ei.sets) MaxSets, AVG(ei.sets) MaxSets
FROM Exercise e
INNER JOIN ExerciseInstance ei ON ei.ExerciseId = e.ExerciseId
GROUP BY Exercise
ORDER BY Exercise;

-- 10. Minimum and maximum Client.BirthDate per Workout.
SELECT 
	w.Name WorkoutName, 
    MIN(c.BirthDate) EarliestBirthDate, 
    MAX(c.BirthDate) LatestBirthDate
FROM Workout w
INNER JOIN ClientWorkout cw ON w.WorkoutId = cw.WorkoutId
INNER JOIN Client c ON cw.ClientId = c.ClientId
GROUP BY WorkoutName;

-- 11. Count client goals (include those without goals)
SELECT c.ClientId, COUNT(cg.GoalId) GoalCount
FROM Client c
LEFT JOIN ClientGoal cg ON c.ClientId = cg.ClientId
GROUP BY c.ClientId
ORDER BY GoalCount;

-- 12. Exercise.Name, Unit.Name, and minimum and maximum ExerciseInstanceUnitValue.Value 
-- for all exercises with a configured ExerciseInstanceUnitValue.
SELECT 
	e.name ExerciseName, 
    u.name UnitName, 
    MIN(eiuv.value) MinValue, 
    MAX(eiuv.value) 'MaxValue' -- MaxValue is a keyword apparently..
FROM Exercise e
INNER JOIN ExerciseInstance ei ON e.ExerciseId = ei.ExerciseId
INNER JOIN ExerciseInstanceUnitValue eiuv ON ei.ExerciseInstanceId = eiuv.ExerciseInstanceId
INNER JOIN Unit u ON eiuv.UnitId = u.UnitId
WHERE eiuv.value IS NOT NULL
GROUP BY ExerciseName, UnitName
ORDER BY ExerciseName, UnitName;

-- 13. Include ec.name
SELECT 
	ec.name ExerciseCategory,
	e.name ExerciseName, 
    u.name UnitName, 
    MIN(eiuv.value) MinValue, 
    MAX(eiuv.value) 'MaxValue' -- MaxValue is a keyword apparently..
FROM Exercise e
INNER JOIN ExerciseCategory ec ON e.ExerciseCategoryId = ec.ExerciseCategoryId
INNER JOIN ExerciseInstance ei ON e.ExerciseId = ei.ExerciseId
INNER JOIN ExerciseInstanceUnitValue eiuv ON ei.ExerciseInstanceId = eiuv.ExerciseInstanceId
INNER JOIN Unit u ON eiuv.UnitId = u.UnitId
WHERE eiuv.value IS NOT NULL
GROUP BY ExerciseCategory, ExerciseName, UnitName
ORDER BY ExerciseCategory, ExerciseName, UnitName;

-- 14. Min Max Age per level
DESCRIBE Client;
DESCRIBE Workout;


-- Use CURRENT_DATE to get todays date.
SELECT 
	l.Name LevelName,
    DATEDIFF(CURRENT_DATE(), MAX(c.BirthDate))/365.25 YoungestAge,
    DATEDIFF(CURRENT_DATE(), MIN(c.BirthDate))/365.25 OldestAge
FROM Workout w
INNER JOIN ClientWorkout cw ON w.WorkoutId = cw.WorkoutId
INNER JOIN Client c ON cw.ClientId = c.ClientId
INNER JOIN Level l ON w.LevelId = l.LevelId
GROUP BY LevelName;

-- 15. Count logins by extension
-- Use substring index to split string at a delimiter, negative occurance to get the last = ext.
SELECT
	SUBSTRING_INDEX(l.EmailAddress, '.', -1) EmailExt,
	COUNT(*) ExtCount
FROM Login l
GROUP BY EmailExt;

-- 16. Match client goals to workout goals.
SELECT 
	w.Name WorkoutName, 
    CONCAT(c.FirstName, ' ', c.LastName) ClientName,
    Count(*) GoalCount
FROM Workout w
INNER JOIN WorkoutGoal wg ON w.WorkoutId = wg.WorkoutId
INNER JOIN Goal g ON wg.GoalId = g.GoalId
INNER JOIN ClientGoal cg ON g.GoalId = cg.GoalId
INNER JOIN Client c ON cg.ClientId = c.ClientId
GROUP BY WorkoutName, c.LastName, c.FirstName
HAVING GoalCount >= 2
ORDER BY c.LastName, c.FirstName;
