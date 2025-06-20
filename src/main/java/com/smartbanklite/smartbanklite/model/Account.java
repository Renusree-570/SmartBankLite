package com.smartbanklite.smartbanklite.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Account {
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name="customerId")
    private Customer customer;
    private String accountType;
    private Double balance;
}
