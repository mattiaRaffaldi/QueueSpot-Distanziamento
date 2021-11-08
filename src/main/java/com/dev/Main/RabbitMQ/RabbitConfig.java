package com.dev.Main.RabbitMQ;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//classe per configurare la coda rabbit
public class RabbitConfig {

    public static final String LISTEN_QUEUE = "distanziamento";
    public static final String EXCHANGE = "pissir-topics";

    public static String getEXCHANGE() {
        return EXCHANGE;
    }

    //creazione queue
    @Bean
    public Queue contatoreQueue() {
        return new Queue(LISTEN_QUEUE,false);
    }

    //inizializzaione topic
    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange(EXCHANGE);
    }
    @Bean
    Binding contatoreBinding(Queue contatoreQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(contatoreQueue)
                .to(topicExchange)
                .with("distanziamento.*");
    }
       @Bean
    public com.dev.Main.RabbitMQ.Subscriber receiver() {
        return new Subscriber();
    }


}
