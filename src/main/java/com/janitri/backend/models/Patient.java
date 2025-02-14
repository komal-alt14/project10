package com.janitri.backend.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnore; // ✅ Add this

@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Patient name is required")
    private String name;

    @Min(value = 0, message = "Age must be a positive number")
    private int age;

    @NotBlank(message = "Disease description is required")
    private String disease;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    @JsonIgnore // ✅ Prevents infinite recursion when serializing
    private User doctor;

    // Constructors
    public Patient() {}

    public Patient(String name, int age, String disease, User doctor) {
        this.name = name;
        this.age = age;
        this.disease = disease;
        this.doctor = doctor;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getDisease() { return disease; }
    public void setDisease(String disease) { this.disease = disease; }

    public User getDoctor() { return doctor; }
    public void setDoctor(User doctor) { this.doctor = doctor; }
}