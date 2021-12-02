package com.example.plpla.controleur;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.plpla.Client;
import com.example.plpla.R;
import com.example.plpla.ui.home.PortailFragment;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import io.socket.client.Socket;

import static android.content.Context.MODE_PRIVATE;

public class ListenerButton implements View.OnClickListener{

    private final Socket socket;
    private Activity activity;
    private Client client;
    private int compteurTouche = 0;

    public ListenerButton(Socket socket, Activity activity, Client client) {
        this.socket = socket;
        this.activity = activity;
        this.client = client;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.boutonEnregistrer:
                Log.d("Bouton enregistrer", "Parcours enregistre");
                //activity.getSelectionItem().add(activity.getTextEnjeux().getText().toString());
                //activity.getSelectionItem().add(activity.getTextCompetence().getText().toString());
                String fileName = "mon_parcours_S1";
                client.getNomFichier().add(fileName);
                String final_selection = "SEMESTRE 1\n";
                for (String selections : client.getSelectionUE()){
                    Log.d("WRITEFILE", "ecriture de "+client.getSelectionUE().toString());
                    final_selection += selections + "\n";
                    Log.d("WRITEFILE", "Valeur de final_selection "+final_selection);

                }
                try {
                    FileOutputStream ecriture = activity.openFileOutput(fileName, MODE_PRIVATE);
                    ecriture.write(final_selection.getBytes());
                    ecriture.close();
                    client.getSelectionUE().clear();
                    Toast.makeText(activity, "Parcours enregistré", Toast.LENGTH_LONG).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                for (String code_ue: client.getSelectionCode()) {
                    Log.d("SAVE_SERVER", "Envoie de la matière de code "+code_ue+ " au serveur pour enregistrement");
                    ((Client)activity.getApplicationContext()).getUniqueConnexion().envoyerEvent("Save", code_ue);
                }
                break;




        }
    }


}