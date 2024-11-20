package com.PicpayTransferSystem.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonDTO {
    private String fullName;
    private String email; 
    private String document;
    private double saldoinicial;
}