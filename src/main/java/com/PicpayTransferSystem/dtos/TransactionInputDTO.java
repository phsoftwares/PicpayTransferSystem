package com.PicpayTransferSystem.dtos;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TransactionInputDTO {
    private Long idPayee;
    private Long idPayer;
    private BigDecimal value;
}