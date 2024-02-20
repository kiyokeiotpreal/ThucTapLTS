package com.example.thuctaplts.Repository;

import com.example.thuctaplts.model.SeatStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatStatusRepository extends JpaRepository<SeatStatus, Integer> {
}
