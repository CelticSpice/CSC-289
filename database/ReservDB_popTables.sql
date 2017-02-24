-- Insert Reservers table test record.

INSERT INTO Reservers (firstName, lastName, email, phone)
VALUES ('Shane', 'McCann', 'm@e.com', '9105551234');


-- Insert Reservables table test record.

INSERT INTO Reservables (locationName, capacity, startDate, endDate, startTime, endTime, cost)
VALUES ('Cabin 01', 15, '2017-02-23', '2017-02-23', '08:30', '18:30', 350);


-- Insert reservations table test record.

INSERT INTO Reservations (reservableID, reserverID, eventType, numberAttending)
VALUES (1, 1, 'General Purpose', 12);


-- Insert Reservers table test record.

INSERT INTO Reservers (firstName, lastName, email, phone)
VALUES ('Joe', 'Random', 'joeRandom@e.com', '0123456789');


-- Insert Reservables table test record.

INSERT INTO Reservables (locationName, capacity, startDate, endDate, startTime, endTime, cost)
VALUES ('Bar 01', 35, '2017-03-30', '2017-03-31', '17:00', '03:00', 200);


-- Insert reservations table test record.

INSERT INTO Reservations (reservableID, reserverID, eventType, numberAttending)
VALUES (2, 2, 'General Purpose', 32);


-- Insert Reservers table test record.

INSERT INTO Reservers (firstName, lastName, email, phone)
VALUES ('Pee Wee', 'Diddy', 'peeWeeDiddy@e.com', '5550981234');


-- Insert Reservables table test record.

INSERT INTO Reservables (locationName, capacity, startDate, endDate, startTime, endTime, cost)
VALUES ('Lake', 125, '2018-01-12', '2018-02-12', '09:00', '20:30', 600);


-- Insert reservations table test record.

INSERT INTO Reservations (reservableID, reserverID, eventType, numberAttending)
VALUES (3, 3, 'General Purpose', 113);


-- Insert Reservers table test record.

INSERT INTO Reservers (firstName, lastName, email, phone)
VALUES ('Tim', 'Burns', 'celticspice@openmailbox.org', '4561237890');


-- Insert Reservables table test record.

INSERT INTO Reservables (locationName, capacity, startDate, endDate, startTime, endTime, cost)
VALUES ('Theatre', 75, '2017-05-02', '2017-05-02', '17:00', '18:30', 285.26);


-- Insert reservations table test record.

INSERT INTO Reservations (reservableID, reserverID, eventType, numberAttending)
VALUES (4, 4, 'General Purpose', 68);
