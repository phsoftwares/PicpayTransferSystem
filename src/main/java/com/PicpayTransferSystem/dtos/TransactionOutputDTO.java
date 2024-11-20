package com.PicpayTransferSystem.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TransactionOutputDTO {
    private Boolean success;
    private String message;
    private int transactionCode;

    public TransactionOutputDTO(Boolean success, String message, int transactionCode) {
        this.message = message;
        this.transactionCode = transactionCode;
        this.success = success;
    }
}