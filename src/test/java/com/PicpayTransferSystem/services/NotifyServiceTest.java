package com.PicpayTransferSystem.services;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.PicpayTransferSystem.consts.RabbitMQConst;
import com.PicpayTransferSystem.interfaces.IRabbitMQService;

public class NotifyServiceTest {

    @Mock
    private IRabbitMQService rabbitMQService;

    @InjectMocks
    private NotifyService notifyService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should send notification successfully")
    public void testSendNotification() {
        notifyService.sendNotification();

        verify(rabbitMQService, times(1)).sendMessage(eq(RabbitMQConst.queueTransactionNotificationsName), eq("Successful transaction."));
    }
}
