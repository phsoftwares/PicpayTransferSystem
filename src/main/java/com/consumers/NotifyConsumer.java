package com.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.consts.RabbitMQConst;

@Component
public class NotifyConsumer {

    @RabbitListener(queues = RabbitMQConst.queueTransactionNotificationsName)
    private void createNotification(String message) {
        var maxAttempts = 1000;
        var attempts = 0;
        System.out.println(message);       

        while (attempts < maxAttempts) {
            var success = sendNotification();
            if (success) {
                return;
            } else {
                attempts++;
                System.out.println("Attempt " + attempts + " failed. Retrying...");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("Thread interrupted. Exiting the loop.");
                    return;
                }
            }
        }

        System.out.println("Maximum number of attempts reached. Notification not sent.");
    }

    private Boolean sendNotification() {
        var url = "https://util.devi.tools/api/v1/notify";
        var restTemplate = new RestTemplate();
        var notificationResponse = restTemplate.postForEntity(url, String.class, String.class);

        return notificationResponse.getStatusCode().is2xxSuccessful();
    }
}