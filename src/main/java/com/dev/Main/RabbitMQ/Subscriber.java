package com.dev.Main.RabbitMQ;


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

@Component
public class Subscriber {

    private final String[] keys = {"pissir-topics.contatore.qr", "pissir-topics.contatore.distanziamento","pissir-topics.contatore.notifiche"};
    /*
        contatore è in ascolto nel topic contatore.qr
        contatore è in ascolto nel topic contatore.distanziamento

        contatore è in ascolto nel topic contatore.notifiche
        qr per scrivere dovrà pubblicare in contatore.qr.sender
    */


    /*
    *
    contatore per scrivere a distanziamento deve scrivere su distanziamento.contatore
    questo perchè distanziamento ascolta su distanziamento.*
    * */
    @Autowired
    Publisher prod;
    @Autowired DistanziamentoService serv;
    //  @RabbitListener(queues = "contatore")
    @Autowired
    private AmqpTemplate amqpTemplate;

    @RabbitListener(queues = RabbitConfig.LISTEN_QUEUE)
    public void receive(MyMessage message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException, ParseException {

        System.out.println("Ottengo message : " + message.toString());
        switch (message.getId()) {
            case "1":
                //telefono
                System.out.print("sono in switch, eseguo azione di invio");
                MyMessage x = new MyMessage();
                x.setId("2");
                x.setContenuto("provaMattia");
                prod.send(x, "distanziamento." + message.getMail());
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

    //  @RabbitListener(queues = "contatore")
    //  public void receive(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
    //     System.out.println("Ottengo message : " + message);

     /*   ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule("CustomCarDeserializer", new Version(1, 0, 0, null, null, null));
        FromTell cp = fromString(message);
        System.out.println("CP splittato vale !: " + cp );
        try {
            module.addSerializer(FromTell.class, new FromTellSerializer());
            mapper.registerModule(module);
            String ris = mapper.writeValueAsString(cp);
            System.out.println("Tentativo 2 Message received!: " + ris );
        }catch(Exception e){
            System.out.println("Eccezione, vale " + e);
        }*/

      /*    module.addDeserializer(FromTell.class, new FromTellDeserializer());
          mapper.registerModule(module);
          FromTell cp = mapper.readValue(message, FromTell.class);
           System.out.println("Message received!: " + cp );*/
   /*     try {
            System.out.println("Tentativo 1 Message received!: " + message );
            module.addDeserializer(ContatorePersone.class, new CustomContatoreDeserializer());
            mapper.registerModule(module);
            ContatorePersone cp = mapper.readValue(message, ContatorePersone.class);
            if(cp!=null)
                System.out.println("Message received!: " + cp );
            else
                System.out.println("Message received!: " + message );

        }catch(Exception ex){
            try{
                System.out.println("Eccezione " + ex );
              //  System.out.println("Tentativo 2 Message received!: " + message );
              //  module.addDeserializer(FromTell.class, new FromTellDeserializer());
              //  mapper.registerModule(module);
              //  FromTell cp = mapper.readValue(message, FromTell.class);
             //   System.out.println("Message received!: " + cp );
                System.out.println("Message received!: " + message );
            }catch(Exception ex2){
                System.out.println("Eccezione 2: " + ex2 );
            }
        }
*/

//}
}
