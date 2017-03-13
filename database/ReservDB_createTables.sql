-- Create Locations table.

CREATE TABLE Locations(
locationName VARCHAR(20) NOT NULL,
capacity INT NOT NULL,
PRIMARY KEY (locationName)
);


-- Create Timeframes table.

CREATE TABLE Timeframes(
timeframeID INT NOT NULL AUTO_INCREMENT,
startDate DATE NOT NULL,
endDate DATE NOT NULL,
startTime TIME NOT NULL,
endTime TIME NOT NULL,
PRIMARY KEY (timeframeID)
);


-- Create Reservables table.

CREATE TABLE Reservables(
locationName VARCHAR(20) NOT NULL,
timeframeID INT NOT NULL,
cost DECIMAL(7,2) NOT NULL,
FOREIGN KEY (locationName) REFERENCES Locations(locationName),
FOREIGN KEY (timeframeID) REFERENCES Timeframes(timeframeID),
CONSTRAINT locTime PRIMARY KEY (locationName, timeframeID)
);


-- Create Reservers table.

CREATE TABLE Reservers(
reserverID INT NOT NULL AUTO_INCREMENT,
firstName VARCHAR(35) NOT NULL,
lastName VARCHAR(35) NOT NULL,
email VARCHAR(255) NOT NULL,
phone VARCHAR(16) NOT NULL,
PRIMARY KEY (reserverID)
);


-- Create Reservations table.

CREATE TABLE Reservations(
locationName VARCHAR(20) NOT NULL,
timeframeID INT NOT NULL,
reserverID INT NOT NULL,
eventType VARCHAR(35) NOT NULL,
numberAttending INT NOT NULL,
approved BOOLEAN NOT NULL DEFAULT 0,
FOREIGN KEY (locationName) REFERENCES Locations(locationName),
FOREIGN KEY (timeframeID) REFERENCES Timeframes(timeframeID),
FOREIGN KEY (reserverID) REFERENCES Reservers(reserverID),
CONSTRAINT locTime PRIMARY KEY (locationName, timeframeID)
);