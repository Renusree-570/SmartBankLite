package com.smartbanklite.smartbanklite.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Customer {
    @Id
    private Long id;
    private String fullName;
    private String email;
    private String address;
    private String phoneNumber;
}
