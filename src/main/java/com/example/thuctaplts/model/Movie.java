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
@Table(name = "Movie")
@Builder
public class Movie extends BaseEntity{

    private int movieDuration;

    private LocalDateTime endTime;

    private LocalDateTime premiereTime;

    private String description;

    private String director;

    private String image;

    private String HeroImage;

    private String language;

    private String name;

    private String trailer;

    private boolean isActive;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<Schedule> schedules;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "movieTypeID", foreignKey = @ForeignKey(name = "fk_Movie_MovieType"), nullable = false)
    @JsonManagedReference
    private MovieType movieType;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "rateID", foreignKey = @ForeignKey(name = "fk_Movie_Rate"), nullable = false)
    @JsonManagedReference
    private Rate rate;


}
