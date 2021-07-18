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

    public static final String QUEUE = "posizioni";

    //creazione queue
    @Bean
    public Queue posizioni() {
        return new Queue(QUEUE);
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