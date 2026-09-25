
USE TrackIt;

SELECT * 
FROM Worker
ORDER BY LastName; -- Default ASC

SELECT * 
FROM Worker
ORDER BY LastName ASC; -- A-Z

SELECT * 
FROM Worker
ORDER BY LastName DESC; -- Z-A

-- Sort worker projects
-- to make it easier for us to scan through workers and see their projects.
SELECT
	w.FirstName,
    w.LastName,
    p.Name ProjectName
FROM Worker w
INNER JOIN ProjectWorker pw ON w.WorkerId = pw.WorkerId
INNER JOIN Project p ON pw.ProjectId = p.ProjectId
ORDER BY w.LastName; -- Default ASC = A-Z

SELECT
	w.FirstName,
    w.LastName,
    p.Name ProjectName
FROM Worker w
INNER JOIN ProjectWorker pw ON w.WorkerId = pw.WorkerId
INNER JOIN Project p ON pw.ProjectId = p.ProjectId
ORDER BY w.LastName ASC, ProjectName ASC; -- Here allows use alias.
-- Sort by name, then sort by projects
-- Each column can have an independent sort direction.
-- Can even omit

-- Projects list, then worker on project
SELECT
    p.Name ProjectName,
	w.FirstName,
    w.LastName
FROM Worker w
INNER JOIN ProjectWorker pw ON w.WorkerId = pw.WorkerId
INNER JOIN Project p ON pw.ProjectId = p.ProjectId
ORDER BY ProjectName ASC, w.LastName ASC;

SELECT 
	t.Title,
    s.Name StatusName
FROM Task t
LEFT OUTER JOIN TaskStatus s ON t.TaskStatusId = s.TaskStatusId
ORDER BY s.Name ASC; -- Nulls appear on top.

SELECT 
	t.Title,
    s.Name StatusName
FROM Task t
LEFT OUTER JOIN TaskStatus s ON t.TaskStatusId = s.TaskStatusId
ORDER BY ISNULL(s.Name), s.Name ASC; -- Nulls appear last, since sorted 0 -> 1. False -> true.


-- First 10 workers - w/ Natural order - works
SELECT * 
FROM Worker
LIMIT 0, 10;

-- First 10 workers - w/ Order by Last Name ASC (A-Z)
SELECT * 
FROM Worker
ORDER BY LastName ASC
LIMIT 0, 10;

SELECT * 
FROM Worker
ORDER BY LastName DESC
LIMIT 10; -- By default starts at 0

-- Offset past what's available? No error, just empty result.
SELECT * 
FROM Worker
ORDER BY LastName DESC
LIMIT 200, 10; 

-- Find all distinct projects and their ids (showing distinct working)
-- DISTINCT removes duplicate records.
SELECT DISTINCT
	p.Name ProjectName,
    p.ProjectId
FROM Project p
INNER JOIN Task t ON p.ProjectId = t.ProjectId
ORDER BY p.Name;