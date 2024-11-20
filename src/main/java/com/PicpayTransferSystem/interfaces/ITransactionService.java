package com.PicpayTransferSystem.interfaces;

import com.PicpayTransferSystem.dtos.TransactionInputDTO;
import com.PicpayTransferSystem.dtos.TransactionOutputDTO;

public interface ITransactionService {
    TransactionOutputDTO createTransaction(TransactionInputDTO transactionDTO);
}