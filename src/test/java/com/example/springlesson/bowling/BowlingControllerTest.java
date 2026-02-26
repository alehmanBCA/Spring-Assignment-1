package com.example.springlesson.bowling;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class BowlingControllerTest {

    @Autowired
    private BowlingController bowlingController;

    @Autowired
    private BowlingRepository bowlingRepository;

    @Test
    public void registerBowler() {
        BowlingDTO bowling = new BowlingDTO("John Doe", "john@example.com", 200);
        bowlingController.registerNewBowling(bowling);
        
        assertTrue(bowlingRepository.findAll().stream()
                .anyMatch(b -> b.getEmail().equals("john@example.com")));
    }

    @Test
    public void emailError() {
        BowlingDTO bowling1 = new BowlingDTO("John Doe", "unique@example.com", 200);
        bowlingController.registerNewBowling(bowling1);

        BowlingDTO bowling2 = new BowlingDTO("Jane Doe", "unique@example.com", 210);

        assertThrows(ConflictException.class, () -> {
            bowlingController.registerNewBowling(bowling2);
        });
    }
}
