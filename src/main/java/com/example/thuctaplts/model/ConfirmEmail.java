package com.example.thuctaplts.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ConfirmEmail")
@Builder
public class ConfirmEmail extends BaseEntity{
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "userID", foreignKey = @ForeignKey(name = "fk_ConfirmEmail_User"), nullable = false)
    @JsonManagedReference
    private User user;

    private Timestamp requiredTime;

    private Timestamp expiredTime;

    private String confirmCode;

    private boolean isConfirm;
}
