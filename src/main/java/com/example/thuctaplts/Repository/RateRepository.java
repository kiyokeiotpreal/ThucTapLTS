package com.example.thuctaplts.Repository;

import com.example.thuctaplts.model.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RateRepository extends JpaRepository<Rate, Integer> {
}
