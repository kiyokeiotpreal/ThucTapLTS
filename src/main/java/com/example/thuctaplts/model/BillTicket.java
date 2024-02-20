package com.example.thuctaplts.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "BillTicket")
@Builder
public class BillTicket extends BaseEntity{
    private int quantity;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "billID", foreignKey = @ForeignKey(name = "fk_BillTicket_Bill"), nullable = false)
    @JsonManagedReference
    private Bill bill;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ticketID", foreignKey = @ForeignKey(name = "fk_BillTicket_Ticket"), nullable = false)
    @JsonManagedReference
    private Ticket ticket;

}
