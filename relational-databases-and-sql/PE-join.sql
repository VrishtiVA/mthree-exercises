
USE PersonalTrainer;
SHOW TABLES;

-- 1. All columns from ExerciseCategory and Exercise
SELECT *
FROM Exercise e
INNER JOIN ExerciseCategory ec ON e.ExerciseCategoryId = ec.ExerciseCategoryId;

-- 2. ExerciseCategory.Name and Exercise.Name 
-- where the ExerciseCategory does not have a ParentCategoryId
SELECT 
	ec.Name AS ExerciseCategory, 
    e.Name AS ExerciseName
FROM Exercise e
INNER JOIN ExerciseCategory ec ON e.ExerciseCategoryId = ec.ExerciseCategoryId
WHERE ec.ParentCategoryId IS NULL;

-- 4. FirstName, LastName, and BirthDate from Client and EmailAddress from Login 
-- where Client.BirthDate is in the 1990s.
SELECT FirstName, LastName, BirthDate, EmailAddress
FROM Client c
INNER JOIN Login l ON c.ClientId = l.ClientId
WHERE c.BirthDate LIKE "199%";

-- 5. Workout.Name, Client.FirstName, and Client.LastName for Clients 
-- with LastNames starting with 'C'?
SELECT w.Name, c.FirstName, c.LastName
FROM Client c
INNER JOIN ClientWorkout cw ON c.ClientId = cw.ClientId
INNER JOIN Workout w ON cw.WorkoutId = w.WorkoutId
WHERE c.LastName LIKE "C%";

-- 6. Names from Workouts and their Goals
SELECT 
	w.name AS Workout,
    g.name AS Goal
FROM Workout w
INNER JOIN WorkoutGoal wg ON w.WorkoutId = wg.WorkoutId
INNER JOIN Goal g ON wg.GoalId = g.GoalId;

-- 7. client names and email addresses. Login optional.
SELECT 
	Client.ClientId,
	Client.FirstName,
    Client.LastName,
    Login.EmailAddress
FROM Client
LEFT JOIN Login ON Client.ClientId = Login.ClientId;

-- Clients without logins?
SELECT 
	Client.ClientId,
	Client.FirstName,
    Client.LastName,
    Login.EmailAddress
FROM Client
LEFT JOIN Login ON Client.ClientId = Login.ClientId
WHERE Login.EmailAddress IS NULL; 

-- 8. Does the Client, Romeo Seaward, have a Login?
SELECT 
	Client.ClientId,
	Client.FirstName,
    Client.LastName,
    Login.EmailAddress
FROM Client
LEFT JOIN Login ON Client.ClientId = Login.ClientId
WHERE Client.LastName = "Seaward"; -- No he doesn't have.

-- 9. Select ExerciseCategory.Name and its parent ExerciseCategory's Name
SELECT ec.name ExerciseCategory, ecp.name ParentCategory
FROM ExerciseCategory ec
INNER JOIN ExerciseCategory ecp ON ec.ParentCategoryId = ecp.ExerciseCategoryId;

-- 10. Above but include ec name even if no parent
SELECT ec.name ExerciseCategory, ecp.name ParentCategory
FROM ExerciseCategory ec
LEFT JOIN ExerciseCategory ecp ON ec.ParentCategoryId = ecp.ExerciseCategoryId;

-- 11. Are there Clients who are not signed up for a Workout?
SELECT *
FROM Client c
LEFT OUTER JOIN ClientWorkout cw ON c.ClientId = cw.ClientId
WHERE cw.WorkoutId IS NULL; -- Yes, 50 of them

-- 12. Which Beginner-Level Workouts satisfy at least one of Shell Creane's Goals?
DESCRIBE Client;
DESCRIBE ClientGoal;
DESCRIBE Goal;
DESCRIBE Workout;

SELECT w.*
FROM Workout w
INNER JOIN WorkoutGoal wg ON w.WorkoutId = wg.WorkoutId
INNER JOIN Goal g ON wg.GoalId = g.GoalId
INNER JOIN ClientGoal cg ON g.GoalId = cg.GoalId
INNER JOIN Client c ON cg.ClientId = c.ClientId
WHERE c.LastName = "Creane" AND c.FirstName = "Shell" AND w.LevelId = 1;

-- 13. Select Workout.Name and Exercise.Name for related Workouts and Exercises
SELECT w.Name Workout, e.Name Exercise
FROM Workout w
INNER JOIN WorkoutDay wd ON w.WorkoutId = wd.WorkoutId
INNER JOIN WorkoutDayExerciseInstance wdei ON wd.WorkoutDayId = wdei.WorkoutDayId
INNER JOIN ExerciseInstance ei ON wdei.ExerciseInstanceId = ei.ExerciseInstanceId
INNER JOIN Exercise e ON e.ExerciseId = ei.ExerciseId;

-- 14. Select Exercise.Name, ExerciseInstanceUnitValue.Value, and 
-- Unit.Name for the 'Plank' exercise.
SELECT e.Name, eiuv.Value, u.Name
FROM Exercise e
INNER JOIN ExerciseInstance ei ON e.ExerciseId = ei.ExerciseId
INNER JOIN ExerciseInstanceUnitValue eiuv ON ei.ExerciseInstanceId = eiuv.ExerciseInstanceId
INNER JOIN Unit u ON eiuv.UnitId = u.UnitId
WHERE e.Name = 'Plank';