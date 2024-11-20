package com.PicpayTransferSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PicpayTransferSystem.dtos.TransactionInputDTO;
import com.PicpayTransferSystem.dtos.TransactionOutputDTO;
import com.PicpayTransferSystem.interfaces.ITransactionService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private ITransactionService transactionService;

    @PostMapping
    public ResponseEntity<TransactionOutputDTO> createTransaction(@RequestBody TransactionInputDTO transactionInputDTO) {
        if (transactionInputDTO == null) {
            return ResponseEntity.badRequest().body(null);
        }
        var transactionOutputDTO = transactionService.createTransaction(transactionInputDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionOutputDTO);
    }

}