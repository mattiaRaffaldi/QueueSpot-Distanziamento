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

        Distanziamento d1 = new Distanziamento("mail@gmail.com", 653.0, 654.0);

        //dist.saveAll(List.of(d1));
        dist.save(d1);
        };
    }
}
