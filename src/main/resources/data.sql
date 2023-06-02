INSERT INTO Room (id_room) VALUES (1),(2),(3);

INSERT INTO Seat (row_number, seat_number, id_room) VALUES
                                               (1, 1, 1),
                                               (1, 2, 1),
                                               (1, 3, 1),
                                               (1, 4, 1),
                                               (2, 1, 1),
                                               (2, 2, 1),
                                               (2, 3, 1),
                                               (2, 4, 1),

                                               (1, 1, 2),
                                               (1, 2, 2),
                                               (1, 3, 2),
                                               (1, 4, 2),
                                               (2, 1, 2),
                                               (2, 2, 2),
                                               (2, 3, 2),
                                               (2, 4, 2),

                                               (1, 1, 3),
                                               (1, 2, 3),
                                               (1, 3, 3),
                                               (1, 4, 3),
                                               (2, 1, 3),
                                               (2, 2, 3),
                                               (2, 3, 3),
                                               (2, 4, 3);

INSERT INTO Ticket_Type (title, Price)VALUES ('Adult', 25),('Student', 18),('Child', 12.50);

INSERT INTO Film (title)VALUES ('The Avengers'),('Inception'),('The Lion King');

INSERT INTO Movie_Session (start_time, end_time, id_film, id_room) VALUES ('2023-07-25 10:00:00+00:00', '2023-07-25 12:00:00+00:00', 1,1);
INSERT INTO Movie_Session (start_time, end_time, id_film, id_room) VALUES ('2023-07-25 18:00:00+00:00', '2023-07-25 20:00:00+00:00', 1,1);
INSERT INTO Movie_Session (start_time, end_time, id_film, id_room) VALUES ('2023-07-25 11:15:00+00:00', '2023-07-25 13:15:00+00:00', 2,3);
INSERT INTO Movie_Session (start_time, end_time, id_film, id_room) VALUES ('2023-07-25 13:30:00+00:00', '2023-07-25 16:30:00+00:00', 2,3);
INSERT INTO Movie_Session (start_time, end_time, id_film, id_room) VALUES ('2023-07-25 14:30:00+00:00', '2023-07-25 16:30:00+00:00', 3,2);
INSERT INTO Movie_Session (start_time, end_time, id_film, id_room) VALUES ('2023-07-25 12:30:00+00:00', '2023-07-25 14:15:00+00:00', 3,2);