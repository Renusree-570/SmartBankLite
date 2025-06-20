package com.smartbanklite.smartbanklite.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Transfer {
    @Id
    private Long id;
    @ManyToOne
    @JoinColumn(name="toAccountId")
    private Account toAccount;
    @ManyToOne
    @JoinColumn(name="fromAccountId")
    private Account fromAccount;
    private Double amount;
    private String transactionType;
    private String description;
    private LocalDateTime timestamp;

}
