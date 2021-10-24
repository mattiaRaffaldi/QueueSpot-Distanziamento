package com.dev.Main.Service;

import com.dev.Main.Model.Distanziamento;
import com.dev.Main.Repository.DistanziamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
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
    public Distanziamento getDistanziamentoUser(String mail){
        //metodi che vanno a recuperare i dati dal db
        //sono tutti metodi da implementare!!
        return dist.getByUser(mail);
    }

    public void insertItem(Distanziamento d){
        dist.save(d);
    }

    public void update(Distanziamento d){
        Distanziamento db = dist.getByUser(d.userID);
        if (db != null) {
            db.setxLat(d.lat);
            db.setyLon(d.lon);
        }
        dist.save(d);
    }

}
