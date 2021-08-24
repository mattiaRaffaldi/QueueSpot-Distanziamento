package com.dev.Main.RabbitMQ;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//classe per configurare la coda rabbit
public class RabbitConfig {


    public static final String LISTEN_QUEUE = "Distanziamento/Contatore";
    public static final String LISTEN_QUEUE1 = "Distanziamento/Notifiche";
    public static final String PUBLISH_QUEUE = "Contatore/Distanziamento";
    public static final String PUBLISH_QUEUE1 = "Notifiche/Distanziamento";
    public static final String EXCHANGE = "Pissir";

    //creazione queue
    @Bean
    public Queue createQueue() {
        return new Queue(LISTEN_QUEUE);
    }
    @Bean
    public Queue createQueue1() {
        return new Queue(LISTEN_QUEUE);
    }

   @Bean
    TopicExchange exchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public MessageConverter converter(){
     return new Jackson2JsonMessageConverter();
    }

    //WIP
    public AmqpTemplate template(ConnectionFactory conn){
        final RabbitTemplate rabbTemp = new RabbitTemplate(conn);
        rabbTemp.setMessageConverter(converter());
        return rabbTemp;
    }
}
