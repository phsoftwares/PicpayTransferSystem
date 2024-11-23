package com.PicpayTransferSystem.services;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class AuthorizationServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private AuthorizationService authorizationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should get transaction authorization successfully")
    public void testGetAuthorizationTransactionSuccess() throws Exception {
        var jsonResponse = "{\"status\": \"success\", \"data\": {\"authorization\": true}}";

        var responseEntity = ResponseEntity.ok(jsonResponse);
        when(restTemplate.getForEntity(anyString(), eq(String.class))).thenReturn(responseEntity);

        var authorization = authorizationService.getAuthorizationTransaction();

        assertTrue(authorization);
    }

    @Test
    @DisplayName("Should get transaction authorization failed")
    public void testGetAuthorizationTransactionFailure() throws Exception {
        var jsonResponse = "{\"status\": \"success\", \"data\": {\"authorization\": false}}";

        var responseEntity = ResponseEntity.ok(jsonResponse);
        when(restTemplate.getForEntity(anyString(), eq(String.class))).thenReturn(responseEntity);

        var authorization = authorizationService.getAuthorizationTransaction();

        assertFalse(authorization);
    }
}
