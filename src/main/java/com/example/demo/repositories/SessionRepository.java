package com.example.demo.repositories;

import com.example.demo.entities.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public interface SessionRepository extends JpaRepository<Session, Integer> {

    @Query("FROM Session s WHERE s.startTime>=:startTime AND s.endTime<=:endTime ORDER BY s.film.title, s.startTime")
    List<Session> getFilms(@Param("startTime")LocalDateTime startTime,
                           @Param("endTime")LocalDateTime endTime);

    @Query("FROM Session s WHERE s.startTime>=:startTime AND s.endTime<=:endTime AND s.film.title = :title")
    Session getSessionByTitleAndDate(@Param("title") String title, @Param("startTime") Timestamp startTime,
                           @Param("endTime")Timestamp endTime);


}
