package com.dev.Main.Model;

import javax.persistence.*;

@Entity
@Table
public class Distanziamento {
    @Id
    public String userID;
    public Double lat;
    public Double lon;

    public Distanziamento(String userID, Double lat, Double lon) {
        this.userID = userID;
        this.lat = lat;
        this.lon = lon;
    }

    public Distanziamento() {
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Double getxLat() {
        return lat;
    }

    public void setxLat(Double xCoord) {
        this.lat = xCoord;
    }

    public Double getyLon() {
        return lon;
    }

    public void setyLon(Double yCoord) {
        this.lon = yCoord;
    }

    @Override
    public String toString() {
        return "Distanziamento{userID=" + userID + '\'' +
                ", xCoord=" + lat +
                ", yCoord=" + lon +
                '}';
    }

}
