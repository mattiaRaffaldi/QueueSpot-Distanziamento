package com.dev.Main;

import com.dev.Main.Model.Distanziamento;
import com.dev.Main.Model.MyMessage;
import com.dev.Main.RabbitMQ.Publisher;
import com.dev.Main.RabbitMQ.RabbitConfig;
import com.dev.Main.Service.DistanziamentoConfig;
import com.dev.Main.Service.DistanziamentoService;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping (path = "/posizioni")
public class ApiLayer {

    private final DistanziamentoService distService;
    private final DistanziamentoConfig adder;
    private final Publisher pub;
    private RabbitConfig template ;
   // @Autowired
    //private RabbitTemplate rabbitTemplate;

    //dist sarà autoinstanziata
    @Autowired
    public ApiLayer(DistanziamentoService dist, DistanziamentoConfig adder, Publisher t) {
        //this.template = (RabbitConfig) template.template();
        this.distService = dist;
        this.adder = adder;
        this.pub = t;
    }

    @GetMapping(path="/all")
    public List<Distanziamento> getPosizioniAll(){

        return distService.getDistanziamento();
    }

    @GetMapping(path="/{id}")
    public List<Distanziamento> getPosizioneUser(@PathVariable(value = "id") Long userId){

        return distService.getDistanziamentoByUser(userId);
    }

    @PostMapping("/new")
    public String aggiungiPosizione(@RequestBody Distanziamento newDist) {

            distService.insertItem(newDist);
            
            System.out.println(newDist + " saved");
            return "200";
    }

    //{"msg":"allarme!!"}

    @PostMapping("/add")
    public String entrataCliente(@RequestParam("name") String nome,@RequestParam("channel") String channel) {

        try {
            MyMessage nuovo = new MyMessage("1",nome,"prova@gmail.com","azione","token");

            pub.send(nuovo, channel);
            return "200";
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "200";
    }
}
