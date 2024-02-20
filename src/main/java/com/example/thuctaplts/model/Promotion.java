package com.example.thuctaplts.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Promotion")
@Builder
public class Promotion extends BaseEntity{

    private int percent;

    private long quantity;

    private String type;

    private LocalDate startTime;

    private LocalDate EndTime;

    private String description;

    private String name;

    private boolean isActive;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "rankCustomerId", foreignKey = @ForeignKey(name = "fk_Promotion_RankCustomer"), nullable = false)
    @JsonManagedReference
    private RankCustomer rankCustomer;

    @OneToMany(mappedBy = "promotion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<Bill> bills;

}
