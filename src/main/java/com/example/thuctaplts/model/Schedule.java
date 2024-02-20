package com.example.thuctaplts.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Schedule")
@Builder
public class Schedule extends BaseEntity{
    private double price;

    private LocalDateTime startAt;

    private LocalDateTime endAt;

    private String code;

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<Ticket> tickets;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "roomID", foreignKey = @ForeignKey(name = "fk_Schedule_Room"), nullable = false)
    @JsonManagedReference
    private Room room;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "movieID", foreignKey = @ForeignKey(name = "fk_Schedule_Movie"), nullable = false)
    @JsonManagedReference
    private Movie movie;
}
