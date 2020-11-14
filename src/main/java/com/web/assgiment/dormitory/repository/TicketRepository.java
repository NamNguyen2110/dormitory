package com.web.assgiment.dormitory.repository;

import com.web.assgiment.dormitory.domain.entity.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    @Query("SELECT DISTINCT t FROM Ticket t WHERE t.student.studentCode LIKE CONCAT('%',:studentCode,'%') AND t.status = 1")
    Page<Ticket> filterByCode(Pageable pageable, @Param("studentCode") String studentCode);

    @Query("SELECT DISTINCT t FROM Ticket t WHERE t.status = 1")
    Page<Ticket> getAllTicket(Pageable pageable);
}
