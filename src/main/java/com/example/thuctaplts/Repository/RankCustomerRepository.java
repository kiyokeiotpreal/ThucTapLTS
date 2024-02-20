package com.example.thuctaplts.Repository;

import com.example.thuctaplts.model.RankCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RankCustomerRepository extends JpaRepository<RankCustomer, Integer> {
}
