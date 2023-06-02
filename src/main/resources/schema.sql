-- Table: Film
CREATE TABLE Film (
                      id_film integer  NOT NULL AUTO_INCREMENT,
                      title varchar(100)  NOT NULL,
                      CONSTRAINT Film_pk PRIMARY KEY (id_film)
) ;

-- Table: Reservation
CREATE TABLE Reservation (
                             id_reservation integer  NOT NULL AUTO_INCREMENT,
                             first_name varchar(100)  NOT NULL,
                             surname varchar(100)  NOT NULL,
                             id_ticket_type integer  NOT NULL,
                             id_session integer  NOT NULL,
                             id_seat integer  NOT NULL,
                             CONSTRAINT Reservation_pk PRIMARY KEY (id_reservation)
) ;

-- Table: Room
CREATE TABLE Room (
                      id_room integer  NOT NULL AUTO_INCREMENT,
                      CONSTRAINT RoomId PRIMARY KEY (id_room)
) ;


-- Table: Seat
CREATE TABLE Seat (
                      id_seat integer  NOT NULL AUTO_INCREMENT,
                      row_number integer  NOT NULL,
                      seat_number integer  NOT NULL,
                      id_room integer  NOT NULL,
                      CONSTRAINT Seat_pk PRIMARY KEY (id_seat)
) ;

-- Table: Session
CREATE TABLE Movie_Session (
                           id_session integer  NOT NULL AUTO_INCREMENT,
                           start_time datetime  NOT NULL,
                           end_time datetime  NOT NULL,
                           id_film integer  NOT NULL,
                           id_room integer  NOT NULL,
                           CONSTRAINT Session_pk PRIMARY KEY (id_session)
) ;

-- Table: Ticket_Type
CREATE TABLE Ticket_Type (
                             id_ticket_type integer  NOT NULL AUTO_INCREMENT,
                             title varchar(100)  NOT NULL,
                             price float  NOT NULL,
                             CONSTRAINT Ticket_Type_pk PRIMARY KEY (id_ticket_type)
) ;


ALTER TABLE Reservation ADD CONSTRAINT Reservation_MovieSession
    FOREIGN KEY (id_session)
        REFERENCES Movie_Session (id_session);

ALTER TABLE Reservation ADD CONSTRAINT Reservation_Seat
    FOREIGN KEY (id_seat)
        REFERENCES Seat (id_seat);

ALTER TABLE Reservation ADD CONSTRAINT Reservation_TicketType
    FOREIGN KEY (id_ticket_type)
        REFERENCES Ticket_Type (id_ticket_type);

ALTER TABLE Movie_Session ADD CONSTRAINT MovieSession_Room
    FOREIGN KEY (id_room)
        REFERENCES Room (id_room);

ALTER TABLE Seat ADD CONSTRAINT Seat_Room
    FOREIGN KEY (id_room)
        REFERENCES Room (id_room);

ALTER TABLE Movie_Session ADD CONSTRAINT Session_Film
    FOREIGN KEY (id_film)
        REFERENCES Film (id_film);