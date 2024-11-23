package com.PicpayTransferSystem.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.PicpayTransferSystem.dtos.TransactionInputDTO;
import com.PicpayTransferSystem.entities.AccountEntity;
import com.PicpayTransferSystem.entities.PersonEntity;
import com.PicpayTransferSystem.repositories.AccountRepository;

@SpringBootTest
public class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    private PersonEntity payeeEntity;
    private AccountEntity accountPayeeEntity;
    private PersonEntity payerEntity;
    private AccountEntity accountPayerEntity;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        payeeEntity = new PersonEntity("111.111.111-11", "Mariana Nunes", "mariana.nunes@uol.com.br");   
        payeeEntity.setId(1L);     
        accountPayeeEntity = new AccountEntity(payeeEntity, new BigDecimal("1000.00"));

        payerEntity = new PersonEntity("111.111.111-22", "Janina Maria", "janina.maria@uol.com.br");
        payerEntity.setId(2L);
        accountPayerEntity = new AccountEntity(payerEntity, new BigDecimal("123.00"));
    }

    @Test
    @DisplayName("Should create the account successfully")
    public void testCreateNewAccount() {
        when(accountRepository.save(any(AccountEntity.class))).thenReturn(accountPayeeEntity);
        accountService.createNewAccount(payeeEntity, new BigDecimal("1000.00"));

        verify(accountRepository, times(1)).save(any(AccountEntity.class));
    }

    @Test
    @DisplayName("Should get balance by person id successfully")
    public void testGetBalanceByPersonId() {
        when(accountRepository.findByPersonId(payeeEntity.getId())).thenReturn(accountPayeeEntity);

        var balance = accountService.getBalanceByPersonId(payeeEntity.getId());

        assertEquals(new BigDecimal("1000.00"), balance);
    }

    @Test
    @DisplayName("Should get account by person id successfully")
    public void testGetAccountByPersonId() {
        when(accountRepository.findByPersonId(payeeEntity.getId())).thenReturn(accountPayeeEntity);

        var accountEntityResponse = accountService.getAccountByPersonId(payeeEntity.getId());

        assertNotNull(accountEntityResponse);
        assertEquals(accountPayeeEntity, accountEntityResponse);
    }

    @Test
    @DisplayName("Should execute transaction successfully")
    public void testExecuteTransactionSuccessful() {
        when(accountRepository.findByPersonId(payeeEntity.getId())).thenReturn(accountPayeeEntity);
        when(accountRepository.findByPersonId(payerEntity.getId())).thenReturn(accountPayerEntity);
        when(accountRepository.save(any(AccountEntity.class))).thenReturn(accountPayeeEntity);

        var transactionInputDTO = getTransactionInputDTO(new BigDecimal("100.00"));

        var result = accountService.executeTransaction(transactionInputDTO);

        assertTrue(result);

        verify(accountRepository, times(2)).save(any(AccountEntity.class));
    }

    private TransactionInputDTO getTransactionInputDTO(BigDecimal value) {
        return new TransactionInputDTO(1L, 2L, value);
    }
    
}