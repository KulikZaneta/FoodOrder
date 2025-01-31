CREATE TABLE Country (
CountryID INT PRIMARY KEY,
Name VARCHAR(100) NOT NULL);

CREATE TABLE City (
CityID INT PRIMARY KEY,
CountryID INT NOT NULL,
Name VARCHAR(100) NOT NULL,
Population INT NOT NULL,
FOREIGN KEY (CountryID) REFERENCES Country(CountryID));

CREATE TABLE Building (
BuildingID INT PRIMARY KEY,
CityID INT NOT NULL,
Name VARCHAR(100) NOT NULL,
Floors INT NOT NULL,
FOREIGN KEY (CityID) REFERENCES City(CityID));

-----------------------------
SELECT c.Name
FROM Country c
JOIN City ci ON c.CountryID = ci.CountryID
GROUP BY c.CountryID
HAVING SUM(ci.Population) > 400;

SELECT c.Name FROM Country c
LEFT JOIN City ci ON c.CountryID = ci.CountryId
LEFT JOIN Building b ON ci.CityID = b.CityID
WHERE b.BuildingID IS NULL;