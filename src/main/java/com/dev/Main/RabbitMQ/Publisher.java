package com.dev.Main.RabbitMQ;

import java.util.concurrent.TimeUnit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Publisher {

    @Autowired
    private final RabbitTemplate rabbitTemplate;
    private final Listener listener;

    public Publisher(Listener Listener, RabbitTemplate rabbitTemplate) {
        this.listener = Listener;
        this.rabbitTemplate = rabbitTemplate;
    }


    public void produceMsg(String msg){
        rabbitTemplate.convertAndSend("posizioni", msg);
    }

}