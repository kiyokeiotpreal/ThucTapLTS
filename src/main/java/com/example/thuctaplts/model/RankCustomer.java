package com.example.thuctaplts.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "RankCustomer")
@Builder
public class RankCustomer extends BaseEntity{

    private long point;

    private String description;

    private String name;

    private boolean isActive = true;

    @OneToMany(mappedBy = "rankCustomer",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<User> users;

    @OneToMany(mappedBy = "rankCustomer",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<Promotion> promotions;
}
