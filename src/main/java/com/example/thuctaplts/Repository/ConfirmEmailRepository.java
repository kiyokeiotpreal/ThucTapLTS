package com.example.thuctaplts.Repository;

import com.example.thuctaplts.model.ConfirmEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmEmailRepository extends JpaRepository<ConfirmEmail, Integer> {
}
