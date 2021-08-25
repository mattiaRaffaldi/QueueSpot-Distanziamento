package com.dev.Main.RabbitMQ;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
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

            String msg = "{\n" +
                    "\"distID\":\"1\",\n" +
                    "\"userID\":\"matteo\",\n" +
                    "\"xCoord\":\"123\",\n" +
                    "\"yCoord\":\"542\"\n" +
                    "}";

        JSONParser parser = new JSONParser();
        try {
            JSONObject json = (JSONObject) parser.parse(msg);
            //rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE, RabbitConfig.QUEUE, json);
            rabbitTemplate.convertAndSend(RabbitConfig.PUBLISH_QUEUE, tosend);
            System.out.println("Messaggio Inviato!");
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}