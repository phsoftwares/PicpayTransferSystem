package com.PicpayTransferSystem.services;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.PicpayTransferSystem.dtos.TransactionInputDTO;
import com.PicpayTransferSystem.dtos.TransactionOutputDTO;
import com.PicpayTransferSystem.entities.TransactionEntity;
import com.PicpayTransferSystem.enums.TransactionCodeEnum;
import com.PicpayTransferSystem.externalDTOS.PicPayAuthorizationDTO;
import com.PicpayTransferSystem.interfaces.IAccountService;
import com.PicpayTransferSystem.interfaces.INotifyService;
import com.PicpayTransferSystem.interfaces.IPersonService;
import com.PicpayTransferSystem.interfaces.ITransactionService;
import com.PicpayTransferSystem.repositories.TransactionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class TransactionService implements ITransactionService {

    @Autowired  
    private IAccountService accountService;

    @Autowired  
    private IPersonService personService;

    @Autowired  
    private INotifyService notifyService;

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public TransactionOutputDTO createTransaction(TransactionInputDTO transactionDTO) {
        if (getAuthorizationTransaction()) {
           var accountBalance = accountService.getBalanceByPersonId(transactionDTO.getIdPayer());
           if (checkBalance(accountBalance)) {
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

    private Boolean getAuthorizationTransaction() {
        var url = "https://util.devi.tools/api/v2/authorize";
        var restTemplate = new RestTemplate();
        var objectMapper = new ObjectMapper();
        try {
            var authorizationResponse = restTemplate.getForEntity(url, String.class);
            var response = objectMapper.readValue(authorizationResponse.getBody(), PicPayAuthorizationDTO.class);
            return response.getData().getAuthorization();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }     
    }

    private Boolean checkBalance(BigDecimal balance) {
        return (balance.compareTo(BigDecimal.ZERO) <= 0);
    }

    private void saveTransaction(TransactionInputDTO transactionDTO) {
        var payeeEntity = personService.getById(transactionDTO.getIdPayee());
        var payerEntity = personService.getById(transactionDTO.getIdPayer());
        var transactionEntity = new TransactionEntity(transactionDTO.getValue(), payeeEntity, payerEntity);

        transactionRepository.save(transactionEntity);
    }
    
}