package com.PicpayTransferSystem.enums;

public enum TransactionCodeEnum {
    SuccessfulTransaction(0),
    UnauthorizedTransaction(1),
    InsufficientBalance(2),
    ProblemWhenMakingTheTransaction(3);

    private final int code;

    TransactionCodeEnum(int code) {
        this.code = code;
    }
    
    public int getCode() {
        return code;
    }
}