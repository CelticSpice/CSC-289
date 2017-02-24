-- Create the Reservers table.

CREATE TABLE Reservers(
reserverID int NOT NULL AUTO_INCREMENT,
firstName varchar(35) NOT NULL,
lastName varchar(35) NOT NULL,
email varchar(255) NOT NULL,
phone int NOT NULL,
PRIMARY KEY (reserverID)
);


-- Create the Reservables table.

CREATE TABLE Reservables(
reservableID int NOT NULL AUTO_INCREMENT,
locationName varchar(35) NOT NULL,
capacity int NOT NULL,
startDate date NOT NULL,
endDate date NOT NULL,
startTime time NOT NULL,
endTime time NOT NULL,
cost decimal(7,2) NOT NULL,
PRIMARY KEY (reservableID)
);


-- Create the Reservations table.

CREATE TABLE Reservations(
reservableID int NOT NULL,
reserverID int NOT NULL,
eventType varchar(35) NOT NULL,
numberAttending int NOT NULL,
PRIMARY KEY (reservableID),
FOREIGN KEY (reservableID) REFERENCES Reservables(reservableID),
FOREIGN KEY (reserverID) REFERENCES Reservers(reserverID)
);