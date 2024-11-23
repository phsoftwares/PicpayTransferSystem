package com.PicpayTransferSystem.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.PicpayTransferSystem.externalDTOS.PicPayAuthorizationDTO;
import com.PicpayTransferSystem.interfaces.IAuthorizationService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AuthorizationService implements IAuthorizationService {

    @Override
    public Boolean getAuthorizationTransaction() {
        var url = "https://util.devi.tools/api/v2/authorize";
        var restTemplate = new RestTemplate();
        var objectMapper = new ObjectMapper();
        try {
            var authorizationResponse = restTemplate.getForEntity(url, String.class);
            var response = objectMapper.readValue(authorizationResponse.getBody(), PicPayAuthorizationDTO.class);
            return response.getData().getAuthorization();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }     
    }

}
