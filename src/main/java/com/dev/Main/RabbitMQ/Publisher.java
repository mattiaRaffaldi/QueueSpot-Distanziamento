package com.dev.Main.RabbitMQ;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.json.simple.JSONObject;

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


    public void pushMessage(JSONObject tosend) {
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE, RabbitConfig.QUEUE, tosend);
        System.out.println("Messaggio Inviato!");
    }
}