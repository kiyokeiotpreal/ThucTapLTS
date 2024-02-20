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

import static jakarta.persistence.FetchType.EAGER;
import static jakarta.persistence.FetchType.LAZY;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Bill")
@Builder
public class Bill extends BaseEntity{

    private long totalMoney;

    private String tradingCode;

    private LocalDate createTime;

    @ManyToOne(cascade = CascadeType.ALL, fetch = EAGER)
    @JoinColumn(name = "customerID", foreignKey = @ForeignKey(name = "fk_Bill_User"), nullable = false)
    @JsonManagedReference
    private User user;

    private String name;

    private LocalDate createAt;

    @ManyToOne(cascade = CascadeType.ALL, fetch = EAGER)
    @JoinColumn(name = "promotionID", foreignKey = @ForeignKey(name = "fk_Bill_Promotion"), nullable = false)
    @JsonManagedReference
    private Promotion promotion;

    @ManyToOne(cascade = CascadeType.ALL, fetch = EAGER)
    @JoinColumn(name = "billStatusID", foreignKey = @ForeignKey(name = "fk_Bill_BillStatus"), nullable = false)
    @JsonManagedReference
    private BillStatus billStatus;

    @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL, fetch = LAZY)
    @JsonBackReference
    private Set<BillFood> billFoods;

    @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL, fetch = LAZY)
    @JsonBackReference
    private Set<BillTicket> billTickets;


}
