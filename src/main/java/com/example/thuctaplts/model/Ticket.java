package com.example.thuctaplts.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Ticket")
@Builder
public class Ticket extends BaseEntity{
    private String code;

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL, fetch = LAZY)
    @JsonBackReference
    private Set<BillTicket> billTickets;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "scheduleID", foreignKey = @ForeignKey(name = "fk_Ticket_Schedule"), nullable = false)
    @JsonManagedReference
    private Schedule schedule;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "seatID", foreignKey = @ForeignKey(name = "fk_Ticket_Seat"), nullable = false)
    @JsonManagedReference
    private Seat seat;

    private double priceTicket;

    private boolean isActive;
}
