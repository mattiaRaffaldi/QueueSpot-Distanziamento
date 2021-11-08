package com.dev.Main.RabbitMQ;


import com.dev.Main.Model.MyMessage;
import com.dev.Main.Service.DistanziamentoService;
import com.dev.Main.util.DistanziamentoManager;
import com.rabbitmq.client.Channel;
import org.json.simple.parser.ParseException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Subscriber {
    @Autowired
    Publisher prod;
    @Autowired DistanziamentoService serv;
	
	@Autowired DistanziamentoManager manager;

    @RabbitListener(queues = {"distanziamento"})
    public void receive(MyMessage message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException, ParseException {
        System.out.println("Ottengo message : " + message.toString());
        switch (message.getId()) {
            case "1":
                //telefono
				switch(message.getAzione()){
					case "other_pos":
					    System.out.println("Dentro other_pos");
						manager.otherPos(message);
						break;
					case "my_pos":
						manager.myPos(message);
						break;
				}
                break;
            case "2":
                //contatore

                break;
            case "3":
                //dist

                break;
            case "4":
                //qr

                break;
            case "5":
                //notifica

                break;
        }
        try{
            channel.basicAck(tag, false);
        }catch (Exception e) {
            channel.basicNack(tag, false, true);
        }
    }


}
