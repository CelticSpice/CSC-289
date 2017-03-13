-- Insert test Locations records.

INSERT INTO Locations (locationName, capacity)
VALUES ('Camp 01', 35);

INSERT INTO Locations (locationName, capacity)
VALUES ('Cabin 01', 15);

INSERT INTO Locations (locationName, capacity)
VALUES ('Cabin 02', 15);

INSERT INTO Locations (locationName, capacity)
VALUES ('Theater 01', 100);

INSERT INTO Locations (locationName, capacity)
VALUES ('Ballroom 01', 225);


-- Insert test Timeframes records.

INSERT INTO Timeframes (startDate, endDate, startTime, endTime)
VALUES ('2017-03-20', '2017-03-20', '13:00', '14:00');

INSERT INTO Timeframes (startDate, endDate, startTime, endTime)
VALUES ('2017-03-20', '2017-03-20', '14:00', '15:00');

INSERT INTO Timeframes (startDate, endDate, startTime, endTime)
VALUES ('2017-03-21', '2017-03-21', '09:00', '10:00');


-- Insert test Reservables records.

INSERT INTO Reservables (locationName, timeframeID, cost)
VALUES ('Camp 01', 1, 325);

INSERT INTO Reservables (locationName, timeframeID, cost)
VALUES ('Camp 01', 2, 325);

INSERT INTO Reservables (locationName, timeframeID, cost)
VALUES ('Camp 01', 3, 325);

INSERT INTO Reservables (locationName, timeframeID, cost)
VALUES ('Cabin 01', 1, 250);

INSERT INTO Reservables (locationName, timeframeID, cost)
VALUES ('Cabin 01', 2, 250);

INSERT INTO Reservables (locationName, timeframeID, cost)
VALUES ('Cabin 01', 3, 250);

INSERT INTO Reservables (locationName, timeframeID, cost)
VALUES ('Cabin 02', 1, 250);

INSERT INTO Reservables (locationName, timeframeID, cost)
VALUES ('Cabin 02', 2, 250);

INSERT INTO Reservables (locationName, timeframeID, cost)
VALUES ('Cabin 02', 3, 250);

INSERT INTO Reservables (locationName, timeframeID, cost)
VALUES ('Theater 01', 1, 400);

INSERT INTO Reservables (locationName, timeframeID, cost)
VALUES ('Theater 01', 2, 400);

INSERT INTO Reservables (locationName, timeframeID, cost)
VALUES ('Theater 01', 3, 400);

INSERT INTO Reservables (locationName, timeframeID, cost)
VALUES ('Ballroom 01', 1, 525);

INSERT INTO Reservables (locationName, timeframeID, cost)
VALUES ('Ballroom 01', 2, 525);

INSERT INTO Reservables (locationName, timeframeID, cost)
VALUES ('Ballroom 01', 3, 525);


-- Insert test Reservers records.

INSERT INTO Reservers (firstName, lastName, email, phone)
VALUES ('Shane', 'McCann', 'shane.mccann@email.com', '9105551234');


-- Insert test Reservations records.

INSERT INTO Reservations (locationName, timeframeID, reserverID, eventType, numberAttending)
VALUES ('Camp 01', 1, 1, 'Camping', 34);

INSERT INTO Reservations (locationName, timeframeID, reserverID, eventType, numberAttending)
VALUES ('Cabin 01', 1, 1, 'Camping', 12);

INSERT INTO Reservations (locationName, timeframeID, reserverID, eventType, numberAttending)
VALUES ('Cabin 01', 3, 1, 'Camping', 14);

INSERT INTO Reservations (locationName, timeframeID, reserverID, eventType, numberAttending)
VALUES ('Ballroom 01', 2, 1, 'Wedding', 189);