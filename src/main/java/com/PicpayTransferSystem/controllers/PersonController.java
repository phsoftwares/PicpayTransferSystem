package com.PicpayTransferSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PicpayTransferSystem.dtos.PersonDTO;
import com.PicpayTransferSystem.interfaces.IPersonService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private IPersonService personService;

    @PostMapping
    public ResponseEntity<Long> createNewPerson(@RequestBody PersonDTO personDTO) {
        if (personDTO == null) {
            return ResponseEntity.badRequest().body(null);
        }
        var personEntity = personService.createNewPerson(personDTO);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(personEntity);
    }

}