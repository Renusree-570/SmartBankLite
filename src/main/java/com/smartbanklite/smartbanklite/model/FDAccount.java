package com.smartbanklite.smartbanklite.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class FDAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fdId;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;

    @NotNull(message = "Principal amount is required")
    @Positive(message = "Principal amount must be positive")
    private Double principalAmount;

    private Double interestRate = 6.5;

    @NotNull(message = "Tenure in months is required")
    @Positive(message = "Tenure must be positive")
    private Integer tenureMonths;

    private LocalDate startDate;
    private LocalDate maturityDate;
    private Double maturityAmount;
}
