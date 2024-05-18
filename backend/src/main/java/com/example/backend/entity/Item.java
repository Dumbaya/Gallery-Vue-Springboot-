package com.example.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name="items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length=50, nullable = false)
    private String name;

    @Column(length=100)
    private String imgPath;

    @Column
    private int price;

    @Column
    private int discountPer;
}
