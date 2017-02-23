INSERT INTO reservers (reserverID,firstname,lastname,email,phone)
VALUES (seq_reserver.nextval,'Shane','McCann','m@e.com','9105551234');

INSERT INTO timeframes (timeframeID,startdate,enddate,starttime,endtime)
VALUES (seq_timeframe.nextval,TO_DATE('02-23-2017','mm-dd-yyyy'),TO_DATE('02-23-2017','mm-dd-yyyy'),
        '10:00','12:00');

INSERT INTO locations (locationname,capacity)
VALUES ('Barn 01', 50);

INSERT INTO locationtimeframes (locationname,timeframeid,cost)
VALUES ('Barn 01', 1, 400.25);

INSERT INTO reservations (locationname,timeframeID,eventtype,numberattending,reserverID)
VALUES ('Barn 01', 1, 'Gen Purpose', 45, 1);