package com.example.thuctaplts.Repository;

import com.example.thuctaplts.model.BillTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillTicketRepository extends JpaRepository<BillTicket, Integer> {
}
