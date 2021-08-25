package com.dev.Main.Model;

import javax.persistence.*;

@Entity
@Table
public class Distanziamento {
    @Id
    @SequenceGenerator(
            //devono avere lo stesso nome!!!
            name = "distanziamentoID",
            sequenceName = "distanziamentoID",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "distanziamentoID"
    )
    public Long distID;
    public String userID;

    @Override
    public String toString() {
        return "Distanziamento{" +
                "distID=" + distID +
                ", userID='" + userID + '\'' +
                ", xCoord=" + xCoord +
                ", yCoord=" + yCoord +
                '}';
    }

    public Integer xCoord;
    public Integer yCoord;

    public Distanziamento(Long distID, String userID, Integer xCoord, Integer yCoord) {
        this.distID= distID;
        this.userID = userID;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

    public Distanziamento(Long distId, Integer xCoord, Integer yCoord) {
        this.distID= distId;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

    //per far funzionare api--> serve un costruttore vuoto
    public Distanziamento() {
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Integer getxCoord() {
        return xCoord;
    }

    public void setxCoord(Integer xCoord) {
        this.xCoord = xCoord;
    }

    public Integer getyCoord() {
        return yCoord;
    }

    public void setyCoord(Integer yCoord) {
        this.yCoord = yCoord;
    }
}
