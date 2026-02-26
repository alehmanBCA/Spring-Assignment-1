package com.example.springlesson.bowling;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class BowlingDTO {

    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "email is required")
    @Email(message = "email must be valid")
    private String email;

    @NotNull(message = "average is required")
    @Min(value = 0, message = "average must be >= 0")
    private Integer average;

    public BowlingDTO() {}

    public BowlingDTO(String name, String email, Integer average) {
        this.name = name;
        this.email = email;
        this.average = average;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Integer getAverage() { return average; }
    public void setAverage(Integer average) { this.average = average; }
}
