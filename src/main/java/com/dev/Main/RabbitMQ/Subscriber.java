package com.dev.Main.RabbitMQ;


import com.dev.Main.Model.Distanziamento;
import com.dev.Main.Model.MyMessage;
import com.dev.Main.Service.DistanziamentoService;
import com.rabbitmq.client.Channel;
import org.json.simple.parser.ParseException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Random;

@Component
public class Subscriber {
    @Autowired
    Publisher prod;
    @Autowired DistanziamentoService serv;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @RabbitListener(queues = {"distanziamento"})
    public void receive(MyMessage message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException, ParseException {
        System.out.println("Ottengo message : " + message.toString());
        switch (message.getId()) {
            case "1":
                //telefono
                StringBuilder cont = new StringBuilder();
                /*for (Distanziamento pos : serv.getDistanziamento()){
                    cont.append("lat = ").append(pos.getxCoord()).append("lon = ").append(pos.getyCoord()).append(" - ");
                }*/
                int i = 0;
                while(i < 5){
                    Distanziamento pos = new Distanziamento();
                    pos.setxCoord(new Random().nextInt());
                    pos.setyCoord(new Random().nextInt());
                    i++;
                    cont.append("lat = ").append(pos.getxCoord()).append("lon = ").append(pos.getyCoord()).append(" <-> ");
                }
                MyMessage x = new MyMessage();
                x.setId("3");
                x.setContenuto(cont.toString());
                prod.send(x, "m."+message.getEmail()+".distanziamento");
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
