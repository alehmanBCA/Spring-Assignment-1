package com.example.springlesson.bowling;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table
public class Bowling {
    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    private Long id;
    private String name;
    private String email;
    private Integer average; // Date of Birth

    public Bowling() {

    } // Bowling

    public Bowling(Long id,
                   String name,
                   String email,
                   Integer average) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.average = average;
    } // Bowling

    public Bowling(String name,
                   String email,
                   Integer average) {

        this.name = name;
        this.email = email;
        this.average = average;
    }

    // Create the getters/setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Integer getAverage() { return average; }
    public void setAverage(Integer average) { this.average = average; }


    @Override
    public String toString() {
        return "Bowling{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", average='" + average +
                '}';
    } // String toString()

} // Big bowling
