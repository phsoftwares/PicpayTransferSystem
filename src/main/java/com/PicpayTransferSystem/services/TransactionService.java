package com.PicpayTransferSystem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.PicpayTransferSystem.dtos.TransactionInputDTO;
import com.PicpayTransferSystem.dtos.TransactionOutputDTO;
import com.PicpayTransferSystem.enums.TransactionCodeConst;
import com.PicpayTransferSystem.externalDTOS.PicPayAuthorizationDTO;
import com.PicpayTransferSystem.interfaces.ITransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class TransactionService implements ITransactionService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public TransactionOutputDTO createTransaction(TransactionInputDTO transactionDTO) {
        var response = new TransactionOutputDTO();
        if (getAuthorizationTransaction()) {

        } else {
            response.setTransactionCode(TransactionCodeConst.UnauthorizedTransaction);
            response.setMessage("Unauthorized transaction.");
            response.setSuccess(false);
        }
        return response;
    }

    private Boolean getAuthorizationTransaction() {
        var url = "https://util.devi.tools/api/v2/authorize";
        var authorizationResponse = restTemplate.getForEntity(url, String.class);

        var objectMapper = new ObjectMapper();
        PicPayAuthorizationDTO response;
        try {
            response = objectMapper.readValue(authorizationResponse.getBody(), PicPayAuthorizationDTO.class);
            return response.getData().getAuthorization();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }     
    }

    
}