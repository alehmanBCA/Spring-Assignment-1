package com.example.springlesson.bowling;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BowlingRepository
        extends JpaRepository<Bowling, Long> {

    @Query("SELECT b FROM Bowling b WHERE b.email = ?1")
    Optional<Bowling> findBowlingByEmail(String email);
}
