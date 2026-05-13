package com.smartbanklite.smartbanklite.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "toAccountId")
    private Account toAccount;

    @ManyToOne
    @JoinColumn(name = "fromAccountId")
    private Account fromAccount;

    private Double amount;
    private String transactionType;
    private String description;
    private LocalDateTime timestamp;
}
