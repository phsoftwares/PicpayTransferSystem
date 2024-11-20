package com.PicpayTransferSystem.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity(name="transactions")
@Table(name="transactions")
@AllArgsConstructor
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    

    private BigDecimal transactionValue; 
    
    @ManyToOne
    @JoinColumn(name="idPayee")
    private PersonEntity payee;

    @ManyToOne
    @JoinColumn(name="idPayer")
    private PersonEntity payer;

    private LocalDateTime creationDateTime;
}
