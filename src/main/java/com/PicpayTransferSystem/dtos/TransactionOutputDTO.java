package com.PicpayTransferSystem.dtos;

import com.PicpayTransferSystem.enums.TransactionCodeEnum;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TransactionOutputDTO {
    private Boolean success;
    private String message;
    private TransactionCodeEnum transactionCode;

    public TransactionOutputDTO(Boolean success, String message, TransactionCodeEnum transactionCode) {
        this.message = message;
        this.transactionCode = transactionCode;
        this.success = success;
    }
}