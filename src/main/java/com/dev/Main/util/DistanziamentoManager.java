package com.dev.Main.util;

import com.dev.Main.Model.Distanziamento;
import com.dev.Main.Model.MyMessage;
import com.dev.Main.RabbitMQ.Publisher;
import com.dev.Main.Service.DistanziamentoService;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DistanziamentoManager {

    @Autowired
    Publisher pub;

    @Autowired
    DistanziamentoService serv;

    public DistanziamentoManager() {
    }
	
       public void myPos(MyMessage message) throws ParseException {
				String[] pos = message.getContenuto().split(",");
				String lat = pos[0].split("=")[1];
				String lon = pos[1].split("=")[1];
			    Double newLat = Double.parseDouble(lat);
			    Double newLon =  Double.parseDouble(lon);
				Distanziamento me = new Distanziamento(message.getEmail(),newLat,newLon);
				serv.update(me);
				if(CovidZone(newLat,newLon, message.getEmail())){
					MyMessage resp = new MyMessage();
					resp.setId("3");
					resp.setEmail(message.getEmail());
					resp.setAzione("notify");
					resp.setContenuto("true");
					System.out.println("------- COVID ZONE INFRANTA! MANTIENI LA DISTANZA! ----------");
					pub.send(resp,"notifica.distanziamento");
			   }
					
		}
		public boolean CovidZone(Double lat, Double lon, String mail) {

			for (Distanziamento pos : serv.getDistanziamento()){
				System.out.println("COVID ZONE METHOD --- IOT POS : " + lat + " " + lon );
				System.out.println("COVID ZONE METHOD --- OTHER POS : " + pos.getxLat() + " " + pos.getyLon() );
				System.out.println("COVID ZONE METHOD --- DIFF LAT : " + Math.abs(pos.getxLat() - lat) );
				System.out.println("COVID ZONE METHOD --- DIFF LON : " + Math.abs(pos.getyLon() - lon) );
				if(!pos.getUserID().equals(mail) && Math.abs(pos.getxLat() - lat) <=5.0 && Math.abs(pos.getyLon() - lon) <=5.0){
					System.out.println("COVID ZONE METHOD --- TROPPO VICINI!");
					return true;
				}
			}
		   return false;
		}
		public void otherPos(MyMessage message) throws ParseException {
						System.out.println("Mymessage ricevuto nel manager vale " + message);
						StringBuilder cont = new StringBuilder();
						for (Distanziamento pos : serv.getDistanziamento()){
							if(!pos.getUserID().equals(message.getEmail()))
								cont.append("lat = ").append(pos.getxLat()).append("lon = ").append(pos.getyLon()).append(" <-> ");
						}

						MyMessage resp = new MyMessage();
						resp.setId("3");
						resp.setContenuto(cont.toString());
						//pub.send(resp, "m."+message.getEmail()+".distanziamento");
						pub.send(resp, "m_inside."+message.getEmail()+".distanziamento");
		}


	public void deleteDistanziamento(String email) {

		serv.deleteUser(email);
	}
}