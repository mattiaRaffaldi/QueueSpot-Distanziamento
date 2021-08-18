package com.dev.Main.RabbitMQ;


import com.dev.Main.Model.Distanziamento;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;


@Component
public class Listener {


//    @RabbitListener(queues = RabbitConfig.QUEUE)
//    public void consumeMessageFromQueue(Distanziamento dist) {
//
//        /**/
//{
////            "distID":"1",
////                "email":"mattia",
////                "xCoord":"123",
////                "yCoord":"542"
////        }
//        System.out.println("Message : " + dist.toString());
//    }

    @RabbitListener(queues = RabbitConfig.QUEUE)
    @SendTo("status")
    public void consumeMessageFromQueue(Distanziamento dist) {
        System.out.println("Message : " + dist.toString());
        //return dist;
    }
}
