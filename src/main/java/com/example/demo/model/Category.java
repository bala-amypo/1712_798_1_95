package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;

    public Category() {}

    public String getName() { return name; }
    public String getType() { return type; }
}
