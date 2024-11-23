package com.PicpayTransferSystem.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.PicpayTransferSystem.dtos.TransactionInputDTO;
import com.PicpayTransferSystem.dtos.TransactionOutputDTO;
import com.PicpayTransferSystem.entities.PersonEntity;
import com.PicpayTransferSystem.entities.TransactionEntity;
import com.PicpayTransferSystem.interfaces.IAccountService;
import com.PicpayTransferSystem.interfaces.IAuthorizationService;
import com.PicpayTransferSystem.interfaces.INotifyService;
import com.PicpayTransferSystem.interfaces.IPersonService;
import com.PicpayTransferSystem.repositories.TransactionRepository;

import java.math.BigDecimal;

@SpringBootTest
public class TransactionServiceTest {

    @Mock
    private IAccountService accountService;

    @Mock
    private IPersonService personService;

    @Mock
    private INotifyService notifyService;

    @Mock
    private IAuthorizationService authorizationService;

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionService transactionService;

    private TransactionInputDTO transactionInputDTO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        transactionInputDTO = new TransactionInputDTO(1L, 2L, new BigDecimal("100.00"));
    }

    @Test
    @DisplayName("Should create the transaction successfully")
    public void testCreateTransactionSuccessfulTransaction() {
        var payerEntity = new PersonEntity("111.111.111-11", "Mariana Nunes", "mariana.nunes@uol.com.br");
        var payeeEntity = new PersonEntity("111.111.111-22", "Julia Sousa", "julia.soua@terra.com.br");

        when(authorizationService.getAuthorizationTransaction()).thenReturn(true);
        when(accountService.getBalanceByPersonId(transactionInputDTO.getIdPayer())).thenReturn(new BigDecimal("200.00"));
        when(accountService.executeTransaction(transactionInputDTO)).thenReturn(true);
        when(personService.getById(transactionInputDTO.getIdPayer())).thenReturn(payerEntity);
        when(personService.getById(transactionInputDTO.getIdPayee())).thenReturn(payeeEntity);

        var response = transactionService.createTransaction(transactionInputDTO);

        assertTrue(response.getSuccess());
        assertEquals("Successful transaction.", response.getMessage());

        verify(notifyService, times(1)).sendNotification();

        verify(transactionRepository, times(1)).save(any(TransactionEntity.class));
    }

    @Test
    @DisplayName("Should return an error when trying to create the transaction due to insufficient balance")
    public void testCreateTransactionInsufficientBalance() {
        when(authorizationService.getAuthorizationTransaction()).thenReturn(true);
        when(accountService.getBalanceByPersonId(transactionInputDTO.getIdPayer())).thenReturn(new BigDecimal("50.00"));

        var response = transactionService.createTransaction(transactionInputDTO);

        assertFalse(response.getSuccess());
        assertEquals("Insufficient balance.", response.getMessage());
    }

    @Test
    @DisplayName("Should return an error when creating the transaction due to authorization failure")
    public void testCreateTransactionAuthorizationFailed() {
        when(authorizationService.getAuthorizationTransaction()).thenReturn(false);

        var response = transactionService.createTransaction(transactionInputDTO);

        assertFalse(response.getSuccess());
        assertEquals("Unauthorized transaction.", response.getMessage());
    }

    @Test
    @DisplayName("Should return an error when creating the transaction due to problem persisting in the database")
    public void testCreateTransactionProblemWhenMakingTransaction() {
        when(authorizationService.getAuthorizationTransaction()).thenReturn(true);
        when(accountService.getBalanceByPersonId(transactionInputDTO.getIdPayer())).thenReturn(new BigDecimal("200.00"));
        when(accountService.executeTransaction(transactionInputDTO)).thenReturn(false);

        TransactionOutputDTO response = transactionService.createTransaction(transactionInputDTO);

        assertFalse(response.getSuccess());
        assertEquals("Problem when making the transaction.", response.getMessage());
    }
}
