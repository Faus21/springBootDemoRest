package com.example.demo.repositories;

import com.example.demo.entities.TicketType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TicketTypeRepository extends JpaRepository<TicketType, Integer> {

    @Query("FROM TicketType t WHERE t.title = :ticketType")
    TicketType findByType(@Param("ticketType") String type);
}
