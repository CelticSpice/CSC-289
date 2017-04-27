-- Create Locations table.

CREATE TABLE IF NOT EXISTS Locations(
locationID INT NOT NULL AUTO_INCREMENT,
locationName VARCHAR(20) NOT NULL UNIQUE,
capacity INT NOT NULL,
PRIMARY KEY (locationID)
);


-- Create Timeframes table.

CREATE TABLE IF NOT EXISTS Timeframes(
timeframeID INT NOT NULL AUTO_INCREMENT,
startDate DATE NOT NULL,
endDate DATE NOT NULL,
startTime TIME NOT NULL,
endTime TIME NOT NULL,
PRIMARY KEY (timeframeID),
CONSTRAINT UC_Timeframes UNIQUE (StartDate, EndDate, StartTime, EndTime)
);


-- Create Reservables table.

CREATE TABLE IF NOT EXISTS Reservables(
locationID INT NOT NULL,
timeframeID INT NOT NULL,
cost DECIMAL(7,2) NOT NULL,
FOREIGN KEY (locationID) REFERENCES Locations(locationID) ON UPDATE CASCADE ON DELETE CASCADE,
FOREIGN KEY (timeframeID) REFERENCES Timeframes(timeframeID) ON DELETE CASCADE,
CONSTRAINT PK_Reservables PRIMARY KEY (locationID, timeframeID)
);


-- Create Reservers table.

CREATE TABLE IF NOT EXISTS Reservers(
reserverID INT NOT NULL AUTO_INCREMENT,
firstName VARCHAR(35) NOT NULL,
lastName VARCHAR(35) NOT NULL,
email VARCHAR(255) NOT NULL,
phone VARCHAR(16),
PRIMARY KEY (reserverID)
);


-- Create Reservations table.

CREATE TABLE IF NOT EXISTS Reservations(
locationID INT NOT NULL,
timeframeID INT NOT NULL,
reserverID INT NOT NULL,
eventType VARCHAR(35) NOT NULL,
numberAttending INT NOT NULL,
reviewed BOOLEAN NOT NULL DEFAULT 0,
FOREIGN KEY (locationID) REFERENCES Reservables(locationID) ON UPDATE CASCADE ON DELETE CASCADE,
FOREIGN KEY (timeframeID) REFERENCES Reservables(timeframeID) ON DELETE CASCADE,
FOREIGN KEY (reserverID) REFERENCES Reservers(reserverID) ON DELETE CASCADE,
CONSTRAINT PK_Reservations PRIMARY KEY (locationID, timeframeID)
);
