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
        Bowling bowling = new Bowling("John Doe", "john@example.com", 200);
        bowlingController.registerNewBowling(bowling);
        
        assertTrue(bowlingRepository.findAll().stream()
                .anyMatch(b -> b.getEmail().equals("john@example.com")));
    }

    @Test
    public void emailError() {
        Bowling bowling1 = new Bowling("John Doe", "unique@example.com", 200);
        bowlingController.registerNewBowling(bowling1);

        Bowling bowling2 = new Bowling("Jane Doe", "unique@example.com", 210);
        
        assertThrows(IllegalStateException.class, () -> {
            bowlingController.registerNewBowling(bowling2);
        });
    }
}
