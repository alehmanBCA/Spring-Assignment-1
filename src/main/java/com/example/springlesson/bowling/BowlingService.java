package com.example.springlesson.bowling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BowlingService {

    private final BowlingRepository bowlingRepository;

    @Autowired
    public BowlingService(BowlingRepository bowlingRepository) {
        this.bowlingRepository = bowlingRepository;
    }

    public List<Bowling> getBowling() {
        return bowlingRepository.findAll();
    }

    public void addNewBowling(Bowling bowling) {
        Optional<Bowling> bowlingOptional = bowlingRepository
                .findBowlingByEmail(bowling.getEmail());
        if (bowlingOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        bowlingRepository.save(bowling);
    }
}
