package com.PicpayTransferSystem.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity(name="accounts")
@Table(name="accounts")
@AllArgsConstructor
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    

    @Column(name = "input_value", nullable = false)
    private BigDecimal inputValue; 

    @Column(name = "output_value", nullable = false)
    private BigDecimal outputValue;    

    @Column(name = "initial_balance", nullable = false)
    private BigDecimal initialBalance;  

    @Column(name = "creation_datetime", nullable = false) 
    private LocalDateTime creationDateTime;
    
    @OneToOne
    @JoinColumn(name="id_person")
    private PersonEntity person;

    public AccountEntity(PersonEntity personEntity, BigDecimal initialBalance) {
        this.inputValue = BigDecimal.ZERO;
        this.outputValue = BigDecimal.ZERO;
        this.initialBalance = initialBalance;
        this.person = personEntity;
        this.creationDateTime = LocalDateTime.now();
    }
    
    public AccountEntity() {

    }

    public BigDecimal getBalance() {
        var balance = this.initialBalance.add(this.inputValue);
        balance = balance.subtract(this.outputValue);
        return balance;
    }
}
