package com.example.thuctaplts.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Roles")
@Builder
public class Roles extends BaseEntity{

    private boolean code;

    private String roleName;
}
