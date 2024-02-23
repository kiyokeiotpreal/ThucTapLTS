package com.example.thuctaplts.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "Roles")
@Builder
public class Roles extends BaseEntity{

    private String code;

    private String roleName;

    @OneToMany( mappedBy = "roles",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<User> users;
}
