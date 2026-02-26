package com.example.springlesson.bowling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/bowling")
public class BowlingController {

    private final BowlingService bowlingService;

    @Autowired
    public BowlingController(BowlingService bowlingService) {
        this.bowlingService = bowlingService;
    }

    @GetMapping
    public List<Bowling> getBowling() {
        return bowlingService.getBowling();
    }

    @PostMapping
    public void registerNewBowling(@RequestBody Bowling bowling) {
        bowlingService.addNewBowling(bowling);
    }
}
