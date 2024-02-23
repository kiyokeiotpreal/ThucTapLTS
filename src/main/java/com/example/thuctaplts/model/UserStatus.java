package com.example.thuctaplts.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "UserStatus")
@Builder
public class UserStatus extends BaseEntity{

    private boolean code;

    private String name;

    @OneToMany( mappedBy = "userStatus",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<User> users;
}
