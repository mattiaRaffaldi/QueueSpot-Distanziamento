package com.dev.Main.Repository;


import com.dev.Main.Model.Distanziamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface DistanziamentoRepository extends JpaRepository<Distanziamento,Long> {
    //data access

    //Metodo per ottenere tutte le coordinate degli utenti all'interno del db.
    @Query("SELECT d.lat,d.lon FROM Distanziamento d")
    List<Distanziamento> findCoordinates();

    @Query("SELECT d FROM Distanziamento d WHERE d.userID= ?1")
    Distanziamento getByUser(String mail);





}
