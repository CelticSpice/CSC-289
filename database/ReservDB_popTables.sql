-- Insert Reservers table test record.

INSERT INTO Reservers (firstName, lastName, email, phone)
VALUES ('Shane', 'McCann', 'm@e.com', '9105551234');


-- Insert Reservables table test record.

INSERT INTO Reservables (locationName, capacity, startDate, endDate, startTime, endTime, cost)
VALUES ('Cabin 01', 15, '02-23-2017', '02-23-2017', '08:30', '18:30', 350);


-- Insert reservations table test record.

INSERT INTO reservations (reservableID, reserverID, eventType, numberAttending)
VALUES (1, 1, 'General Purpose', 12);