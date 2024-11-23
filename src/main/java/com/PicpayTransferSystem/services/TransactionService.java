package com.PicpayTransferSystem.services;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.PicpayTransferSystem.dtos.TransactionInputDTO;
import com.PicpayTransferSystem.dtos.TransactionOutputDTO;
import com.PicpayTransferSystem.entities.TransactionEntity;
import com.PicpayTransferSystem.enums.TransactionCodeEnum;
import com.PicpayTransferSystem.interfaces.IAccountService;
import com.PicpayTransferSystem.interfaces.IAuthorizationService;
import com.PicpayTransferSystem.interfaces.INotifyService;
import com.PicpayTransferSystem.interfaces.IPersonService;
import com.PicpayTransferSystem.interfaces.ITransactionService;
import com.PicpayTransferSystem.repositories.TransactionRepository;

@Service
public class TransactionService implements ITransactionService {

    @Autowired  
    private IAccountService accountService;

    @Autowired  
    private IPersonService personService;

    @Autowired  
    private INotifyService notifyService;

    @Autowired  
    private IAuthorizationService authorizationService;

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public TransactionOutputDTO createTransaction(TransactionInputDTO transactionDTO) {
        if (authorizationService.getAuthorizationTransaction()) {
           var accountBalance = accountService.getBalanceByPersonId(transactionDTO.getIdPayer());
           if (checkBalance(accountBalance, transactionDTO.getValue())) {
            return new TransactionOutputDTO(false, "Insufficient balance.", TransactionCodeEnum.InsufficientBalance.getCode());
           } 

           if (!accountService.executeTransaction(transactionDTO)) {
            return new TransactionOutputDTO(false, "Problem when making the transaction.", TransactionCodeEnum.ProblemWhenMakingTheTransaction.getCode());
           }
           saveTransaction(transactionDTO);

           notifyService.sendNotification();

           return new TransactionOutputDTO(true, "Successful transaction.", TransactionCodeEnum.SuccessfulTransaction.getCode());
        }
        return new TransactionOutputDTO(false, "Unauthorized transaction.", TransactionCodeEnum.UnauthorizedTransaction.getCode());
    }

    private Boolean checkBalance(BigDecimal balance, BigDecimal transactionValue) {
        return (balance.compareTo(transactionValue) <= 0);
    }

    private void saveTransaction(TransactionInputDTO transactionDTO) {
        var payeeEntity = personService.getById(transactionDTO.getIdPayee());
        var payerEntity = personService.getById(transactionDTO.getIdPayer());
        var transactionEntity = new TransactionEntity(transactionDTO.getValue(), payeeEntity, payerEntity);

        transactionRepository.save(transactionEntity);
    }
    
}