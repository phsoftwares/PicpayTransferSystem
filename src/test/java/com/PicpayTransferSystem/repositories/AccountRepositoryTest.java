package com.PicpayTransferSystem.repositories;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.PicpayTransferSystem.dtos.PersonDTO;
import com.PicpayTransferSystem.entities.AccountEntity;
import com.PicpayTransferSystem.entities.PersonEntity;

import jakarta.persistence.EntityManager;

@DataJpaTest
@ActiveProfiles("test") 
public class AccountRepositoryTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    private AccountRepository accountRepository;

    @BeforeEach
    public void setUp() {
        var data = new PersonDTO("Pedro Barros", "pedro.barros@terra.com", "111.111.111-11", BigDecimal.ZERO);
        var personEntity = createPerson(data);
        createAccount(personEntity);
    }

    @Test
    @DisplayName("Should get account successfully from database")
    public void testFindByPersonId() {
        var personId = 1L;

        var account = accountRepository.findByPersonId(personId);

        assertNotNull(account, "The account cannot be null");
        assertEquals(personId, account.getPerson().getId(), "Person ID must be the same as in the past");
    }

    private PersonEntity createPerson(PersonDTO data){
        var personEntity = new PersonEntity(data);
        this.entityManager.persist(personEntity);
        return personEntity;
    }

    private AccountEntity createAccount(PersonEntity data){
        var accountEntity = new AccountEntity(data, BigDecimal.ZERO);
        this.entityManager.persist(accountEntity);
        return accountEntity;
    }
}