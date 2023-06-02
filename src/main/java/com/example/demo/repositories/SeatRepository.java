package com.example.demo.repositories;

import com.example.demo.entities.Seat;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Integer> {

    @Query("SELECT s FROM Seat s WHERE s.idSeat NOT IN (SELECT res.seat.idSeat FROM Reservation res WHERE res.session.idSession = :sessionId) " +
            "AND s.room.idRoom IN (SELECT room.idRoom FROM Room room WHERE room.idRoom = :roomId)")
    List<Seat> getAvailableSeats(@Param("roomId") Integer roomId, @Param("sessionId") Integer sessionId);

    @Query("FROM Seat s WHERE s.idSeat NOT IN (SELECT res.seat.idSeat FROM Reservation res WHERE res.session.idSession = :sessionId) " +
            "AND s.room.idRoom IN (SELECT room.idRoom FROM Room room WHERE room.idRoom = :roomId) AND s.rowNumber = :rowNumber")
    List<Seat> getAvailableSeatsInRow(@Param("roomId") Integer roomId, @Param("sessionId") Integer sessionId, @Param("rowNumber") Integer rowNumber);

    @Query(value = "SELECT min(seat.seatNumber) FROM Seat seat WHERE seat.room.idRoom = :roomId AND seat.rowNumber =:rowNumber")
    Integer minSeatNumberByRoomId(@Param("roomId") Integer roomId, @Param("rowNumber") Integer rowNumber);

    @Query(value = "SELECT max(seat.seatNumber) FROM Seat seat WHERE seat.room.idRoom = :roomId AND seat.rowNumber =:rowNumber")
    Integer maxSeatNumberByRoomId(@Param("roomId") Integer roomId, @Param("rowNumber") Integer rowNumber);
}
