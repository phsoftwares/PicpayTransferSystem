package com.PicpayTransferSystem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.PicpayTransferSystem.dtos.TransactionDTO;
import com.PicpayTransferSystem.interfaces.ITransactionService;

@Service
public class TransactionService implements ITransactionService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void createTransaction(TransactionDTO transactionDTO) {

        if (getAuthorizationTransaction()) {
            
        } else {
            throw new UnsupportedOperationException("Transaction not authorized.'");
        }
    }

    private Boolean getAuthorizationTransaction() {
        var url = "https://util.devi.tools/api/v2/authorize";
        var authorizationReponse = restTemplate.getForEntity(url, String.class);

        return authorizationReponse.getStatusCode() == HttpStatus.OK;
    }

    
}