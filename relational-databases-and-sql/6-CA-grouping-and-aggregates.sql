
USE TrackIt;
DESCRIBE Task;
DESCRIBE TaskStatus;

-- Count TaskIds - 543 values
SELECT COUNT(TaskId)
FROM Task;

-- Count everything - 543 values
SELECT COUNT(*) -- Count records, not values.
FROM Task;

SELECT COUNT(TaskStatusId)
FROM Task; -- 532. Not matching number of records
-- Because some are null. So it is counting non-null values.
-- Nulls are omitted.

SELECT
	COUNT(t.TaskId) -- Can also aggregate values from a filtered, joined table.
FROM Task t
INNER JOIN TaskStatus s ON t.TaskStatusId = s.TaskStatusId
WHERE s.IsResolved = 1; -- 276

-- Full count.
SELECT 
    COUNT(t.TaskId) Count
FROM Task t
INNER JOIN TaskStatus ts ON t.TaskStatusId = ts.TaskStatusId
ORDER BY Count;

-- Count tasks per status
-- By grouping by status - needed since both aggregate and non-aggregate fields.
SELECT 
	ts.Name `Task Status`,
    COUNT(t.TaskId) Count
FROM Task t
INNER JOIN TaskStatus ts ON t.TaskStatusId = ts.TaskStatusId
GROUP BY ts.Name -- References the non-aggregated field in the select statement.
ORDER BY Count;
-- This works.

-- Aliases help provide more meaningful names for aggregates here.
SELECT 
	IFNULL(ts.Name, '[None]') `Task Status`, -- Adjustment to show null in string, works without too though in MySQL.
    COUNT(t.TaskId) Count
FROM Task t
LEFT OUTER JOIN TaskStatus ts ON t.TaskStatusId = ts.TaskStatusId -- Switch to outer to include nulls.
GROUP BY ts.Name 
ORDER BY Count;

-- We can't also include ts.isResolved because it includes a non-aggregated column that isn't functionally dependenton group by clause.
-- Error Code: 1055. Expression #3 of SELECT list is not in GROUP BY clause and contains nonaggregated column 'trackit.ts.IsResolved' which is not functionally dependent on columns in GROUP BY clause; this is incompatible with sql_mode=only_full_group_by
-- In core SQL, SELECT... GROUP BY can't select a value that's not an aggregate or part of the group.

SELECT 
	IFNULL(ts.Name, '[None]') `Task Status`,
    IFNULL(ts.IsResolved, '[None]') `Is Resolved`,
    COUNT(t.TaskId) Count
FROM Task t
LEFT OUTER JOIN TaskStatus ts ON t.TaskStatusId = ts.TaskStatusId
GROUP BY ts.Name, ts.IsResolved  -- Works if we add it to the group. Now there are groups of all combos.
ORDER BY Count;
-- It has same no. rows though, since status - is resolved always had same matching


SELECT DISTINCT
	p.Name ProjectName,
    p.ProjectId
FROM Project p
INNER JOIN Task t ON p.ProjectId = t.ProjectId
ORDER BY p.Name;

-- Optimise DISTINCT queries in MySQL using GROUP BY.
-- Can be more appropriate
SELECT
	p.Name ProjectName,
    p.ProjectId
FROM Project p
INNER JOIN Task t ON p.ProjectId = t.ProjectId
GROUP BY p.Name, p.ProjectId
ORDER BY p.Name; 

-- Fetch estimated hours in tasks assigned to workers, 
-- calc total per worker, 
-- find all workers with 100+ hours.
SELECT 
	w.FirstName,
    w.LastName,
    SUM(t.EstimatedHours) TotalEstimatedHours
FROM Worker w 
INNER JOIN ProjectWorker pw ON w.WorkerId = pw.WorkerId
INNER JOIN Task t ON pw.WorkerId = t.WorkerId AND pw.ProjectId = t.ProjectId
GROUP BY w.LastName, w.FirstName
HAVING TotalEstimatedHours >= 100; -- Yeaa filter
-- works


SELECT 
	CONCAT(w.FirstName, ' ', w.LastName) AS Name,
    SUM(t.EstimatedHours) TotalEstimatedHours
FROM Worker w 
INNER JOIN ProjectWorker pw ON w.WorkerId = pw.WorkerId
INNER JOIN Task t ON pw.WorkerId = t.WorkerId AND pw.ProjectId = t.ProjectId
GROUP BY Name -- Also works.
HAVING TotalEstimatedHours >= 100
ORDER BY TotalEstimatedHours DESC;

-- Find minimum project task due dates
SELECT
	p.Name ProjectName,
    MIN(t.DueDate) MinimumDueDate
FROM Project p
INNER JOIN Task t ON p.ProjectId = t.ProjectId
GROUP BY p.Name
ORDER BY p.Name;

-- overview of each Project: first and last Task due date, total estimated hours, 
-- total number of Tasks, and average Task hours estimate, and at least 10 tasks
SELECT
	p.Name ProjectName,
    MIN(t.DueDate) EarliestDueDate,
    MAX(t.DueDate) LatestDueDate,
    SUM(t.EstimatedHours) TotalEstimatedHours,
    COUNT(t.TaskId) TaskCount,
    AVG(t.EstimatedHours) AverageEstimatedTaskHours
FROM Task t
INNER JOIN Project p ON t.ProjectId = p.ProjectId
GROUP BY p.Name
HAVING TaskCount >= 10
ORDER BY p.Name;