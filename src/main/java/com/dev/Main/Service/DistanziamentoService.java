package com.dev.Main.Service;

import com.dev.Main.Model.Distanziamento;
import com.dev.Main.Repository.DistanziamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//simile a component ma più utile a livello di semantica--> è una service class
@Service
public class DistanziamentoService {

    private final DistanziamentoRepository dist;

    @Autowired
    public DistanziamentoService(DistanziamentoRepository dist){
        this.dist = dist;
    }

    public List<Distanziamento> getDistanziamento(){
        //metodi che vanno a recuperare i dati dal db
        //sono tutti metodi da implementare!!
        return dist.findAll();
    }

    public List<Distanziamento> getDistanziamentoByUser(Long id){
        //metodi che vanno a recuperare i dati dal db
        //sono tutti metodi da implementare!!
        return dist.findPosizioneUser(id);
    }

    public void insertItem(Distanziamento d){
            dist.save(d);
    }

     //metodo che analizza le posizioni e restituisce quelli con la posizione troppo vicina

     //o o o        0       o o
}
