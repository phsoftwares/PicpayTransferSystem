package com.PicpayTransferSystem.entities;

import java.time.LocalDateTime;

import com.PicpayTransferSystem.dtos.PersonDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity(name="persons")
@Table(name="persons")
@AllArgsConstructor
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "email", unique=true)
    private String email;

    @Column(name = "document", unique=true)
    private String document;

    @Column(name = "creation_datetime", nullable = false) 
    private LocalDateTime creationDateTime;

    public PersonEntity(PersonDTO data){
        this.document = data.getDocument();
        this.fullName = data.getFullName();
        this.email = data.getEmail();
        this.creationDateTime = LocalDateTime.now();
    }

    public PersonEntity(){
        
    }
}