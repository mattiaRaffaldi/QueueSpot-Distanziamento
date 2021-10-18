package com.dev.Main.Repository;


import com.dev.Main.Model.Distanziamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface DistanziamentoRepository extends JpaRepository<Distanziamento,Long> {
    //data access


    @Query("SELECT d FROM Distanziamento d WHERE d.distID = ?1")
    List<Distanziamento> findPosizioneUser(Long id);

    //Metodo per ottenere tutte le coordinate degli utenti all'interno del db.
    @Query("SELECT d.distID,d.xCoord,d.yCoord FROM Distanziamento d")
    List<Distanziamento> findCoordinates();





}
