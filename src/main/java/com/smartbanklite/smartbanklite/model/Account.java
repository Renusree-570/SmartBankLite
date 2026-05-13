package com.smartbanklite.smartbanklite.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;

    @NotBlank(message = "Account type is required")
    private String accountType;

    @PositiveOrZero(message = "Balance cannot be negative")
    private Double balance;
}
