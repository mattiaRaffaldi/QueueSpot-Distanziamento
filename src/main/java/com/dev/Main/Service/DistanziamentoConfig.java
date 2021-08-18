package com.dev.Main.Service;

import com.dev.Main.Model.Distanziamento;
import com.dev.Main.Repository.DistanziamentoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DistanziamentoConfig {

    //classe per inserire gli elementi dentro il db
    CommandLineRunner insertItem(DistanziamentoRepository dist){
        return args -> {

        Distanziamento d1 = new Distanziamento(
                    "miriferra@gmail",
                    653,
                    654);

        //dist.saveAll(List.of(d1));
        dist.save(d1);
        };
    }
}
