package com.PicpayTransferSystem.interfaces;

import com.PicpayTransferSystem.dtos.TransactionDTO;

public interface ITransactionService {
    void createTransaction(TransactionDTO transactionDTO);
}