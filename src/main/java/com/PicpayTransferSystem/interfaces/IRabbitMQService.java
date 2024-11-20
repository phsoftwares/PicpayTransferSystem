package com.PicpayTransferSystem.interfaces;

public interface IRabbitMQService {
    void sendMessage(String queueName, String message);
}