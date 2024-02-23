package com.example.thuctaplts.Repository;

import com.example.thuctaplts.model.ConfirmEmail;
import com.example.thuctaplts.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);

    User findByConfirmEmails(ConfirmEmail confirmEmail);
}
