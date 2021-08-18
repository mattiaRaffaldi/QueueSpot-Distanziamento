package com.dev.Main.RabbitMQ;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class RabbitMQ_sender {


    @Autowired
    private RabbitTemplate rabbitTemplate;

    public RabbitMQ_sender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        helloQueue();
    }


    @Bean
    public Queue helloQueue() {
        return new Queue("hello");
    }

    @Bean
    public void pushMessage() {
        String messageString = "Hello Rabbit @" + LocalDateTime.now().format(DateTimeFormatter.ISO_TIME);
        System.out.println("Messaggio Inviato!");
        rabbitTemplate.convertAndSend("hello", messageString);
    }
}
