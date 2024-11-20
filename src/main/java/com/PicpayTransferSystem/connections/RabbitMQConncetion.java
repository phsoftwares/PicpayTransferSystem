package com.PicpayTransferSystem.connections;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.consts.RabbitMQConst;

import jakarta.annotation.PostConstruct;

@Component
public class RabbitMQConncetion {

    @Autowired
    private AmqpAdmin amqpAdmin;

    @PostConstruct
    private void add() {
        var queueTransactionNotifcations = queue(RabbitMQConst.queueTransactionNotificationsName);

        var directExchange = directExchange();

        var binding = binding(queueTransactionNotifcations, directExchange);

        amqpAdmin.declareQueue(queueTransactionNotifcations);
        amqpAdmin.declareExchange(directExchange);
        amqpAdmin.declareBinding(binding);
    }

    private Queue queue(String queueName) {
        return new Queue(queueName, true, false, false);
    }

    private DirectExchange directExchange() {
        return new DirectExchange(RabbitMQConst.exchangeName);
    }

    private Binding binding(Queue queue, DirectExchange directExchange) {
        return new Binding(queue.getName(), Binding.DestinationType.QUEUE, directExchange.getName(), queue.getName(), null);
    }
}
