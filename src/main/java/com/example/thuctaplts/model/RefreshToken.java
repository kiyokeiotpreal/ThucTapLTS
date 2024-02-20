package com.example.thuctaplts.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "RefreshToken")
@Builder
public class RefreshToken extends BaseEntity{
    private String token;

    private LocalDate expiredTime;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "userID", foreignKey = @ForeignKey(name = "fk_RefreshToken_User"), nullable = false)
    @JsonManagedReference
    private User user;

}
