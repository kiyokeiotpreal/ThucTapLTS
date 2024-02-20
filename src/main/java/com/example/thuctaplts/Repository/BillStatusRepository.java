package com.example.thuctaplts.Repository;

import com.example.thuctaplts.model.BillStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillStatusRepository extends JpaRepository<BillStatus, Integer> {
}
