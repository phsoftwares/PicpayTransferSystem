package com.PicpayTransferSystem.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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

    private BigDecimal inputValue; 

    private BigDecimal outputValue;    

    private BigDecimal initialBalance;  

    private LocalDateTime creationDateTime;
    
    @OneToOne
    @JoinColumn(name="idPerson")
    private PersonEntity person;

    public AccountEntity(PersonEntity personEntity, BigDecimal initialBalance) {
        this.inputValue = BigDecimal.ZERO;
        this.outputValue = BigDecimal.ZERO;
        this.initialBalance = initialBalance;
        this.person = personEntity;
    }
}
