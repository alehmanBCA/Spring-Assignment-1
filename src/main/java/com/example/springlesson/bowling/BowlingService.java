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

    public Bowling addNewBowling(Bowling bowling) {
        Optional<Bowling> bowlingOptional = bowlingRepository
                .findBowlingByEmail(bowling.getEmail());
        if (bowlingOptional.isPresent()) {
            throw new ConflictException("email taken");
        }
        return bowlingRepository.save(bowling);
    }

    public Bowling getBowlingById(Long id) {
        return bowlingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("bowling with id " + id + " does not exist"));
    }

    public void deleteBowling(Long id) {
        boolean exists = bowlingRepository.existsById(id);
        if (!exists) {
            throw new ResourceNotFoundException("bowling with id " + id + " does not exist");
        }
        bowlingRepository.deleteById(id);
    }

    public Bowling updateBowling(Long id, Bowling updated) {
        Bowling existing = bowlingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("bowling with id " + id + " does not exist"));

        // Check email uniqueness if changed
        if (updated.getEmail() != null && !updated.getEmail().equals(existing.getEmail())) {
            Optional<Bowling> byEmail = bowlingRepository.findBowlingByEmail(updated.getEmail());
            if (byEmail.isPresent() && !byEmail.get().getId().equals(id)) {
                throw new ConflictException("email taken");
            }
        }

        // Replace fields (PUT semantics). If clients send nulls, we allow overwriting.
        existing.setName(updated.getName());
        existing.setEmail(updated.getEmail());
        existing.setAverage(updated.getAverage());

        return bowlingRepository.save(existing);
    }
}
