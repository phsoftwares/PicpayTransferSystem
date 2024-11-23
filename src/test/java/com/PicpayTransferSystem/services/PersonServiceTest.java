package com.PicpayTransferSystem.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.PicpayTransferSystem.dtos.PersonDTO;
import com.PicpayTransferSystem.entities.PersonEntity;
import com.PicpayTransferSystem.interfaces.IAccountService;
import com.PicpayTransferSystem.repositories.PersonRepository;

@SpringBootTest
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @Mock
    private IAccountService accountService;

    @InjectMocks
    private PersonService personService;

    private PersonDTO personDTO;
    private PersonEntity personEntity;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        personDTO = new PersonDTO("Pedro Barros", "pedro.barros@terra.com", "111.111.111-11", BigDecimal.ZERO);

        personEntity = new PersonEntity(personDTO);
        personEntity.setId(1L);
    }

    @Test
    @DisplayName("Should create the person successfully")
    public void testCreateNewPerson() {
        when(personRepository.save(personEntity)).thenReturn(personEntity);

        var personEntityResponse = personService.createNewPerson(personDTO);

        assertNotNull(personEntityResponse.getFullName());
        assertEquals(personEntity.getFullName(), personEntityResponse.getFullName());
        verify(personRepository, times(1)).save(any(PersonEntity.class));
        verify(accountService, times(1)).createNewAccount(any(PersonEntity.class), eq(personDTO.getInitialBalance()));
    }

    @Test
    @DisplayName("Should get person by id successfully")
    public void testGetById() {
        when(personRepository.findById(personEntity.getId())).thenReturn(Optional.of(personEntity));

        var returnedPerson = personService.getById(personEntity.getId());

        assertNotNull(returnedPerson);
        assertEquals(personEntity, returnedPerson);
    }
}