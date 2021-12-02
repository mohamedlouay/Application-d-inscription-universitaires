package com.example.plpla.ui.parcours;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.plpla.Client;
import com.example.plpla.Expansion.ExpansionParcours;
import com.example.plpla.R;
import com.example.plpla.ui.home.PortailFragment;
import com.example.plpla.ui.parcours.GalleryViewModel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

import events.EVENT;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

import static android.content.Context.MODE_PRIVATE;

public class ParcoursFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    private TextView monParcours;
    private Button reinitialiser;
    private static final String FILE_NAME = "parcours.txt";
    private TextView finalText;
    private TextView parcoursVide;
    private Socket mSocket;
    private ExpansionParcours expansionParcours;




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.activity_course, container, false);

        monParcours = root.findViewById(R.id.textParcours);
        parcoursVide = root.findViewById(R.id.parcours_vide);
        //finalText = root.findViewById(R.id.parcours_final);
        reinitialiser = root.findViewById(R.id.reinitialiser_button);

        //VISIBILITE PAR DEFAUT
        //finalText.setVisibility(View.INVISIBLE);
        parcoursVide.setVisibility(View.VISIBLE);
        reinitialiser.setEnabled(false);

        final Client client = (Client) getActivity().getApplication();
        mSocket = client.getUniqueConnexion().getmSocket();

        mSocket.on(EVENT.INIT_PARCOURS, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("EVENT_SOCKET", "Socket event INIT_PARCOURS received");
                        Toast.makeText(getActivity().getApplicationContext(), R.string.reinitialiser, Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        if (!(client.getNomFichier().isEmpty())){
            try {
                //Afficher le parcours enregistré


                for (int i = 0; i < client.getNomFichier().size(); i++) {

                    reinitialiser.setEnabled(true);
                    parcoursVide.setVisibility(View.INVISIBLE);
                    ArrayList<String> listeUE = new ArrayList<>();

                    //Lecture du fichier enregistré
                    String parcours;
                /*FileInputStream fichierLecture = getActivity().openFileInput(nomFichier.get(i));
                InputStreamReader lecteur = new InputStreamReader(fichierLecture);*/
                    FileInputStream fichierLecture = getActivity().openFileInput(client.getNomFichier().get(i));
                    final InputStreamReader lecteur = new InputStreamReader(fichierLecture);
                    BufferedReader bfr = new BufferedReader(lecteur);
                    StringBuffer stringBuffer = new StringBuffer();
                    while ((parcours = bfr.readLine()) != null) {
                        listeUE.add(parcours);
                        stringBuffer.append(parcours + "\n");
                    }

                    //On affiche la lecture
                    //finalText.setText(stringBuffer.toString());
                    //finalText.setVisibility(View.VISIBLE);
                    expansionParcours = new ExpansionParcours(root, mSocket, getActivity(), listeUE);
                    this.expansionParcours.setDynamicLayoutContainer((ViewGroup) root.findViewById(R.id.dynamicLayoutContainer));
                    expansionParcours.createExpansion();

                    //LORSQUE JE CLIQUE SUR REINITIALISER
                    reinitialiser.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            //finalText.setVisibility(View.INVISIBLE);

                            View namebar = root.findViewById(R.id.dynamicLayoutContainer);
                            ((ViewGroup) namebar.getParent()).removeView(namebar);

                            parcoursVide.setVisibility(View.VISIBLE);
                            //finalText.setText("");
                            for (int j = 0; j < client.getNomFichier().size(); j++) {
                                FileOutputStream ecriture = null;
                                try {
                                    String vide = "";
                                    ecriture = getActivity().openFileOutput(client.getNomFichier().get(j), MODE_PRIVATE);
                                    ecriture.write(vide.getBytes());
                                    ecriture.close();
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                            }
                            client.getSelectionUE().clear();
                            client.getNomFichier().clear();
                            Toast.makeText(getActivity(), "Parcours réinitialisé", Toast.LENGTH_LONG).show();
                            Log.d("DELETE_PARCOURS_SERVER", "Envoie du message de Réinitialisation au serveur");
                            ((Client) getActivity().getApplicationContext()).getUniqueConnexion().envoyerEvent("INIT_PARCOURS");
                            reinitialiser.setEnabled(false);
                        }
                    });
                }


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return root;
    }
}