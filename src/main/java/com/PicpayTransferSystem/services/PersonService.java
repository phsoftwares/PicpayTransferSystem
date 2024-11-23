package com.PicpayTransferSystem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PicpayTransferSystem.dtos.PersonDTO;
import com.PicpayTransferSystem.entities.PersonEntity;
import com.PicpayTransferSystem.interfaces.IAccountService;
import com.PicpayTransferSystem.interfaces.IPersonService;
import com.PicpayTransferSystem.repositories.PersonRepository;

@Service
public class PersonService implements  IPersonService {
    @Autowired
    private PersonRepository personRepository;
    
    @Autowired
    private IAccountService accountService;

    @Override
    public PersonEntity createNewPerson(PersonDTO personDTO) {
        var personEntity = new PersonEntity(personDTO);
        personRepository.save(personEntity);

        accountService.createNewAccount(personEntity, personDTO.getInitialBalance());
        return personEntity;
    }

    @Override
    public PersonEntity getById(Long personId) {
        var personEntity = personRepository.findById(personId);
        return personEntity.get();
    }
    
}