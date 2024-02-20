package com.example.thuctaplts.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "Seat")
@Builder
public class Seat extends BaseEntity{

    private int number;

    private int line;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "seatTypeID", foreignKey = @ForeignKey(name = "fk_Seat_SeatType"), nullable = false)
    @JsonManagedReference
    private SeatType seatType;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "seatStatusID", foreignKey = @ForeignKey(name = "fk_Seat_SeatStatus"), nullable = false)
    @JsonManagedReference
    private SeatStatus seatStatus;

    @OneToMany(mappedBy = "seat", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<Ticket> tickets;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "roomID", foreignKey = @ForeignKey(name = "fk_Seat_Room"), nullable = false)
    @JsonManagedReference
    private Room room;
}
