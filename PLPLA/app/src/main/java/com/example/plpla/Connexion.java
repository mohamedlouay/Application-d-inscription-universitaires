package com.example.plpla;

import android.util.Log;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class Connexion {

    private Socket mSocket;
    private String serverAddress;

    public String getServerAddress() {
        return serverAddress;
    }

    public Socket getmSocket() {
        return mSocket;
    }


    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public Connexion(){
    }

    public void initConnexion(){
        try {
            //Si vous utilisez l'emulateur, utilisez la ligne suivante
            // serverAddress = "http://10.0.2.2:4444";
            //Sinon remplacez par l'addresse IP de votre serveur (votre pc normalement)
            //serverAddress = "http://192.168.0.23:4444";
            /*Il y des problèmes de connection lorsqu'on utilise pas un émulateur*/
            /*Pour l'instant on redéfinit serverAddress*/
            //serverAddress = "http://10.0.2.2:4444";
            mSocket = IO.socket(serverAddress);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void connecte(){
        Log.d("CONNECT", "Connexion au serveur d'addresse : "+getServerAddress());
        mSocket.connect();
    }

    public void envoyerEvent(String evenement){
        mSocket.emit(evenement);
    }
    public void envoyerEvent(String evenement, Object object){
        mSocket.emit(evenement,object);
    }

    public void deconnecte(){
        if (mSocket != null) {
            Log.d("DISCONNECT", "déconnexion du serveur d'addresse : "+getServerAddress());
            mSocket.disconnect();
        }
    }

}
