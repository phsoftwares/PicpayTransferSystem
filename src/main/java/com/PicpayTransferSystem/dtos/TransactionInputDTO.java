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

    public TransactionInputDTO(Long idPayee, Long idPayer, BigDecimal value){
        this.idPayee = idPayee;
        this.idPayer = idPayer;
        this.value = value;
    }
}