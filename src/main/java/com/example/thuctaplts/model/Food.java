package com.example.thuctaplts.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Food")
@Builder
public class Food extends BaseEntity{

    private double price;

    private String description;

    private String image;

    private String nameOfFood;

    private boolean isActive;


}
