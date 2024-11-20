package com.PicpayTransferSystem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PicpayTransferSystem.consts.RabbitMQConst;
import com.PicpayTransferSystem.interfaces.INotifyService;
import com.PicpayTransferSystem.interfaces.IRabbitMQService;

@Service
public class NotifyService implements INotifyService{

    @Autowired
    private IRabbitMQService rabbitMQService;
    
    @Override
    public void sendNotification() {
        rabbitMQService.sendMessage(RabbitMQConst.queueTransactionNotificationsName, "Successful transaction.");
    }

}
