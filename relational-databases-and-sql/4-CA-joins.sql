
SHOW DATABASES;
USE TrackIt;
SHOW TABLES;

-- List of Tasks Resolved
-- Could figure out IDs and find separately -> 2 queries.
-- Stuff can change so shouldn't hard code stuff of course..
-- Error prone then too.

-- Can use join and do in 1 query.
-- Task record and TaskStatus record matching when they have the same taskstatusid.
SELECT *
FROM Task
JOIN TaskStatus ON Task.TaskStatusId = TaskStatus.TaskStatusId
WHERE IsResolved = 1;

-- Don't need to qualify, as long as can unique identify column.
SELECT 
	Task.TaskId,
    Task.Title,
    TaskStatus.Name As StatusName
FROM TaskStatus
INNER JOIN Task ON TaskStatus.TaskStatusId = Task.TaskStatusId
WHERE IsResolved = 1;
-- Recommended to use INNER, even though can omit.
-- So know intention is clear.

-- Or with a WHERE
SELECT 
	Task.TaskId,
    Task.Title,
    TaskStatus.Name As StatusName
FROM TaskStatus, Task -- Must include both tables included in query
WHERE TaskStatus.TaskStatusId = Task.TaskStatusId -- Asks SQL engine to match PK value to FK value
	AND TaskStatus.IsResolved = 1; -- All results must match both criteria.

-- Who's working on who's a good boy project? -- 7 people
SELECT 
	Project.Name,
    Worker.FirstName,
    Worker.LastName
    
FROM Project
INNER JOIN ProjectWorker ON Project.ProjectId = ProjectWorker.ProjectId
INNER JOIN Worker ON ProjectWorker.WorkerId = Worker.WorkerId
INNER JOIN Task ON ProjectWorker.ProjectId = Task.ProjectId
	AND ProjectWorker.WorkerId = Task.WorkerId
WHERE Project.ProjectId = 'game-goodboy';

-- Select all tasks, with status displayed.
-- Outer keyword is optional. Left is a type of outer join.
SELECT * 
FROM Task
LEFT OUTER JOIN TaskStatus ON Task.TaskStatusId = TaskStatus.TaskStatusId;
-- Now can see all 543 rows again, with matches if available.

-- Without aliasing, literally returns with the IFNULL(...
-- But it does still work.
-- Can also alias w/ or wo/ AS
SELECT 
	Task.TaskId,
    Task.Title,
    IFNULL(Task.TaskStatusId, 0) AS StatusId,
    IFNULL(TaskStatus.Name, '[None]') StatusName
FROM Task
LEFT OUTER JOIN TaskStatus ON Task.TaskStatusId = TaskStatus.TaskStatusId;

-- Are there Projects without Workers or Workers without Projects?

-- All projects with workers, even without workers. Using LEFT OUTER JOIN.
-- While walking to related tables, once used OUTER JOIN, always need OUTER JOIN.
SELECT 
	Project.Name AS ProjectName,
    Worker.FirstName,
    Worker.LastName
FROM Project
LEFT JOIN ProjectWorker ON Project.ProjectId = ProjectWorker.ProjectId
LEFT JOIN Worker ON ProjectWorker.WorkerId = Worker.WorkerId;
-- Not great, have to scroll 166 to find the project wo/ worker.
-- Add a where to remove non-nulls.
-- Like inverse INNER JOIN -> The negative space of inner join.

SELECT 
	Project.Name AS ProjectName,
    Worker.FirstName,
    Worker.LastName
FROM Project
LEFT JOIN ProjectWorker ON Project.ProjectId = ProjectWorker.ProjectId
LEFT JOIN Worker ON ProjectWorker.WorkerId = Worker.WorkerId
WHERE ProjectWorker.WorkerId IS NULL; -- Only include projects without workers.

-- I would omit workers table actually, don't really need. Just need to know where no bridge.
-- Don't need to include worker if only checking that they don't exist.
SELECT 
	Project.Name AS ProjectName
FROM Project
LEFT JOIN ProjectWorker ON Project.ProjectId = ProjectWorker.ProjectId
WHERE ProjectWorker.WorkerId IS NULL;

-- Workers without a project?
-- If we switch to right join, worker always included.
SELECT *
-- 	Worker.FirstName,
--  Worker.LastName
FROM Project
RIGHT OUTER JOIN ProjectWorker ON Project.ProjectId = ProjectWorker.ProjectId
RIGHT OUTER JOIN Worker ON ProjectWorker.WorkerId = Worker.WorkerId
WHERE ProjectWorker.ProjectId IS NULL; -- This works too, since we joined it this field is also null if absent.

-- Similarly simplify
SELECT
	Worker.FirstName,
	Worker.LastName
FROM ProjectWorker
RIGHT OUTER JOIN Worker ON ProjectWorker.WorkerId = Worker.WorkerId
WHERE ProjectWorker.ProjectId IS NULL; -- This works too, since we joined it this field is also null if absent.

-- Or rewrite query with the important concept first.
-- Results and performance characteristics will be the same.
-- Better practice for consistency, and often easier to visualise in one direction.
SELECT 
	Worker.FirstName,
	Worker.LastName
FROM Worker
LEFT JOIN ProjectWorker ON Worker.WorkerId = ProjectWorker.WorkerId
WHERE ProjectWorker.WorkerId IS NULL;

-- Table alias to make less verbose
SELECT 
	w.FirstName,
	w.LastName
FROM Worker w
LEFT JOIN ProjectWorker pw ON w.WorkerId = pw.WorkerId
WHERE pw.WorkerId IS NULL;


-- ParentTaskId is nullable, optional self referential relationship.
DESCRIBE Task;

-- Can join table to itself?
-- SELECT *
-- FROM Task
-- INNER JOIN Task ON Task.TaskId = Task.ParentTaskId;
-- Error Code: 1066. Not unique table/alias: 'Task'

-- SQL engine cannot tell how one Task table is different than the other.
-- Can use table alias, right after the table.
SELECT
	parent.TaskId ParentTaskId,
    child.TaskId ChildTaskId,
    CONCAT( parent.Title, " > ", child.Title ) Task -- Have to use commas in concat
FROM Task parent
INNER JOIN Task child ON parent.TaskId = child.ParentTaskId;


-- Cross join - cartesian product, not a relationship
-- A worker combined with every non game project
-- 6.
SELECT
	CONCAT(w.FirstName, ' ', w.LastName) WorkerName,
    p.Name ProjectName
FROM Worker w
CROSS JOIN Project p
WHERE w.WorkerId = 1
	AND p.ProjectId NOT LIKE 'game-%';

