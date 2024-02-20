package com.example.thuctaplts.Repository;

import com.example.thuctaplts.model.BillFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillFoodRepository extends JpaRepository<BillFood, Integer> {
}
