package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class Category {

    public static final String TYPE_INCOME = "INCOME";
    public static final String TYPE_EXPENSE = "EXPENSE";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;
    private String type;

    public Category() {}

    public Category(Long id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public String getType() { return type; }

    public void validateType() {
        if (!TYPE_INCOME.equals(type) && !TYPE_EXPENSE.equals(type)) {
            throw new RuntimeException("Invalid category type");
        }
    }
}
