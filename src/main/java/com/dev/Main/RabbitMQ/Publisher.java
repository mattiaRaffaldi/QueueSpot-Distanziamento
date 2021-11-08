package com.dev.Main.RabbitMQ;


import com.dev.Main.Model.MyMessage;
import org.json.simple.parser.ParseException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Publisher {

    @Autowired
    private RabbitTemplate template;

    public void send(MyMessage mess, String channelName) throws ParseException {
            template.convertAndSend(RabbitConfig.getEXCHANGE(),channelName,mess);
            System.out.println(" [x] Sent '" + mess + "'" + "channelName: " + channelName );
    }

    @Bean
    public MessageConverter jsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }


}