package com.example.thuctaplts.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "BillFood")
@Builder
public class BillFood extends BaseEntity{

    private int quantity;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "billID", foreignKey = @ForeignKey(name = "fk_BillFood_Bill"))
    @JsonManagedReference
    private Bill bill;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "foodID", foreignKey = @ForeignKey(name = "fk_BillFood_Food"))
    @JsonManagedReference
    private Food food;
}
