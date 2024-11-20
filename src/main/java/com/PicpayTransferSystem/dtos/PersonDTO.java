package com.PicpayTransferSystem.dtos;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonDTO {
    private String fullName;
    private String email; 
    private String document;
    private BigDecimal saldoinicial;
}