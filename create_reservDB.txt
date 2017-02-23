CREATE TABLE Reservers(
ReserverID int NOT NULL PRIMARY KEY,
FirstName varchar(35) NOT NULL,
LastName varchar(35) NOT NULL,
Email varchar(255) NOT NULL,
Phone varchar(16) NOT NULL
);

CREATE SEQUENCE seq_reserver
MINVALUE 0
START WITH 0
INCREMENT BY 1
CACHE 10;

CREATE TABLE Timeframes(
TimeframeID int NOT NULL PRIMARY KEY,
StartDate date NOT NULL,
EndDate date NOT NULL,
StartTime varchar(5) NOT NULL,
EndTime varchar(5) NOT NULL
);

CREATE SEQUENCE seq_timeframe
MINVALUE 0
START WITH 0
INCREMENT BY 1
CACHE 10;

CREATE TABLE Locations(
LocationName varchar(35) NOT NULL PRIMARY KEY,
Capacity int NOT NULL
);

CREATE TABLE LocationTimeframes(
LocationName varchar(35) NOT NULL,
TimeframeID int NOT NULL,
Cost number(*,2) NOT NULL
);

ALTER TABLE LocationTimeframes
ADD FOREIGN KEY (LocationName)
REFERENCES Locations(LocationName)
ADD FOREIGN KEY (TimeframeID)
REFERENCES Timeframes(TimeframeID);

CREATE TABLE Reservations(
LocationName varchar(35) NOT NULL,
TimeframeID int NOT NULL,
EventType varchar(35) NOT NULL,
NumberAttending int NOT NULL,
ReserverID int NOT NULL
);

ALTER TABLE Reservations
ADD FOREIGN KEY (LocationName)
REFERENCES Locations(LocationName)
ADD FOREIGN KEY (TimeframeID)
REFERENCES Timeframes(TimeframeID)
ADD FOREIGN KEY (ReserverID)
REFERENCES Reservers(ReserverID)
ADD CONSTRAINT pk_reservations PRIMARY KEY (LocationName, TimeframeID);