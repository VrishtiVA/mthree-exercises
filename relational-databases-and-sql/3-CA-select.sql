-- SELECT Statement

USE ConsumerComplaints;
SHOW TABLES; -- Only 1 table found
DESCRIBE Complaint;

-- View dates received, products, company, state
-- Results in natural order
-- SQL execution engines don't guarantee an order unless we specifically ask.
SELECT DateReceived, Product, Company, State
FROM Complaint;

-- Select all columns, using wildcard.
-- Don't have to list all columns manually...
-- Be careful though since can be wasteful to select everthing when you don't need.
-- E.g. due to network traffic, server processing, and client processing.
SELECT * 
FROM Complaint;

-- Where operator
-- Even the string is not case sensitive.

SELECT DateReceived, Product, Company, State
FROM Complaint
WHERE State = 'LA';

SELECT DateReceived, Product, Company, State
FROM Complaint
WHERE State = 'la' AND Product = 'mortgage'; 

-- Force boolean operator precedence using parenthesis.

SELECT ComplaintId, Product, State
FROM Complaint
WHERE State = 'LA'
	AND (Product = 'Mortgage' OR Product = 'Debt collection');
-- 6 rows returned (State is forced here)

SELECT ComplaintId, Product, State
FROM Complaint
WHERE State = 'LA'
	AND Product = 'Mortgage' OR Product = 'Debt collection';
-- 194 rows returned (so it seems l-r evaluated)
-- e.g. equivalent to (la and morgage) or debt collection from anywhere.

-- Whats the most common product between complaint id 100000 and 200000?
SELECT Product, COUNT(Product) AS Count
FROM Complaint
WHERE ComplaintId BETWEEN 100000 AND 300000
GROUP BY Product;
-- Mortgage

SELECT 
  Product, 
  Issue, 
  Company, 
  ResponseToConsumer
FROM Complaint
WHERE ConsumerDisputed = 1
AND ConsumerConsent = 1
AND Product NOT IN ('Mortgage', 'Debt collection');
-- Select Disputed, consented complaints, not about mortgages or debt collections.

-- Any complaint on new years 2014? -> No
-- Cannot use IS, doesn't work here.
SELECT * 
FROM Complaint 
WHERE DateReceived = '2014-01-01';

-- Any complaints in 2018? -- 3.
SELECT * 
FROM Complaint 
WHERE DateReceived BETWEEN '2018-01-01' AND '2018-12-31';
-- Inclusive range

-- Any complaints July 2015? -- Yes, 15.
SELECT * 
FROM Complaint 
WHERE DateReceived BETWEEN '2015-07-01' AND '2015-07-31';

-- Any complaints sent before receives? - Yes, 6.
SELECT *
FROM Complaint
WHERE DateSentToCompany < DateReceived;

-- Complaints with consumer companies starting with 'V'
SELECT ComplaintId, Product, State, Company 
FROM Complaint
WHERE Company LIKE 'V%';

-- 'whom' in complaint narative
SELECT ComplaintId, ComplaintNarrative
FROM Complaint
WHERE ComplaintNarrative LIKE '%whom%';

-- Submissive methods with exactly 3 characters - Web, Fax
SELECT DISTINCT SubmissionMethod
FROM Complaint
WHERE SubmissionMethod LIKE '___';

-- Complaints mentioning loan in issue.
SELECT ComplaintId, Issue
FROM Complaint
WHERE Issue LIKE '%loan%';

-- Nulls, have to use IS

SELECT *
FROM Complaint
WHERE SubProduct IS NOT NULL;

SELECT *
FROM Complaint 
WHERE ComplaintId > 15000 OR ComplaintId IS NULL;

SELECT * 
FROM Complaint 
WHERE SubIssue = 'Account status'
	OR SubIssue IS NULL;

-- All complaints with a complaint narrative - hence exclude null values.
SELECT *
FROM Complaint
WHERE ComplaintNarrative IS NOT NULL;

-- Performing calculations

-- DateDiff
SELECT 
	ComplaintId AS Id, -- Can also alias on regular fields
    DateReceived, 
    DateSentToCompany, 
    DATEDIFF(DateSentToCompany, DateReceived) AS DateDifference
FROM Complaint
WHERE DATEDIFF(DateSentToCompany, DateReceived) > 100
ORDER BY DateDifference DESC;
-- Doesn't allow to use the alias in where.
-- Was originally sorted by PK, but can also order.