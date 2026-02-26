package com.example.springlesson.bowling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;
import java.net.URI;
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
    public ResponseEntity<List<Bowling>> getBowling() {
        return ResponseEntity.ok(bowlingService.getBowling());
    }

    @PostMapping
    public ResponseEntity<Bowling> registerNewBowling(@Valid @RequestBody BowlingDTO bowlingDto) {
        Bowling toSave = new Bowling(bowlingDto.getName(), bowlingDto.getEmail(), bowlingDto.getAverage());
        Bowling saved = bowlingService.addNewBowling(toSave);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();

        return ResponseEntity.created(location).body(saved);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Bowling> getBowlingById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(bowlingService.getBowlingById(id));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> deleteBowling(@PathVariable("id") Long id) {
        bowlingService.deleteBowling(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<Bowling> updateBowling(@PathVariable("id") Long id,
                                 @Valid @RequestBody BowlingDTO bowlingDto) {
        Bowling toUpdate = new Bowling(bowlingDto.getName(), bowlingDto.getEmail(), bowlingDto.getAverage());
        Bowling updated = bowlingService.updateBowling(id, toUpdate);
        return ResponseEntity.ok(updated);
    }
}
