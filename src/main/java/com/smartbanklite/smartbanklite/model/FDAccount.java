package com.smartbanklite.smartbanklite.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class FDAccount {
    @Id
    private Long fdId;
    @ManyToOne
    @JoinColumn(name="customerId")
    private Customer customer;
    private Double principalAmount;
    private Double interestRate=6.5;
    private Integer tenureMonths;
    private LocalDate startDate;
    private LocalDate maturityDate;
    private Double maturityAmount;


}
