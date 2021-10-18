package com.dev.Main.Model;

import lombok.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@ToString

public class MyMessage {

    @NonNull
    private String id;
    //1 telefono
    //2 contatore
    //3 distanziamento
    //4 QR
    //5 notifica
    @NonNull
    private String contenuto;
    @NonNull
    private String mail;
    @NonNull
    private String azione;
    @NonNull
    private String token;
}
