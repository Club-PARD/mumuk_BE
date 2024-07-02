package com.pard.preferences.entity;

import jakarta.persistence.*;

@Entity
public class Allergy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    // Constructors, getters and setters
}
