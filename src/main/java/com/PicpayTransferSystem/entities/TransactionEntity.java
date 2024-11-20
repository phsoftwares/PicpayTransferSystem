package com.PicpayTransferSystem.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    

    private Double transactionValue; 
    
    @ManyToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private PersonEntity payee;

    @ManyToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private PersonEntity payer;
}
