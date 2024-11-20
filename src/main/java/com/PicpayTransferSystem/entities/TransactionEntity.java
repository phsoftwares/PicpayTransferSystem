package com.PicpayTransferSystem.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
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

    @Column(name = "transaction_value", nullable = false) 
    private BigDecimal transactionValue; 
    
    @ManyToOne
    @JoinColumn(name="id_payee")
    private PersonEntity payee;

    @ManyToOne
    @JoinColumn(name="id_payer")
    private PersonEntity payer;

    @Column(name = "creation_datetime", nullable = false) 
    private LocalDateTime creationDateTime;

    public TransactionEntity(BigDecimal transactionValue, PersonEntity payee, PersonEntity payer) {
        this.transactionValue = transactionValue;
        this.payee = payee;
        this.payer = payer;
        this.creationDateTime = LocalDateTime.now();
    }
}
