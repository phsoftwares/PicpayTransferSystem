package com.PicpayTransferSystem.externalDTOS;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PicPayAuthorizationDTO {
    private String status;
    private Data data;
    
    @Setter
    @Getter
    public static class Data {
        private Boolean authorization;    
    }
}