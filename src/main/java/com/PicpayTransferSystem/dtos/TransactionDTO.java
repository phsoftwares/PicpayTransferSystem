package com.PicpayTransferSystem.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TransactionDTO {
    private Long idPayee;
    private Long idPayer;
    private Double value;
}