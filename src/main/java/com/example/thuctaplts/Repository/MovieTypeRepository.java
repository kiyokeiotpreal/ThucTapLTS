package com.example.thuctaplts.Repository;

import com.example.thuctaplts.model.MovieType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieTypeRepository extends JpaRepository<MovieType, Integer> {
}
