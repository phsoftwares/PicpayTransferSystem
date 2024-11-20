package com.PicpayTransferSystem.dtos;

import com.PicpayTransferSystem.enums.TransactionCodeConst;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TransactionOutputDTO {
    private Boolean success;
    private String message;
    private TransactionCodeConst transactionCode;
}