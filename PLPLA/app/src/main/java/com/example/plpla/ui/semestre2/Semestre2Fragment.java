package com.example.plpla.ui.semestre2;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.plpla.Client;
import com.example.plpla.Expansion.ExpansionView;
import com.example.plpla.R;
import com.example.plpla.vue.Vue;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import events.EVENT;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import matière.UE;

import static android.content.Context.MODE_PRIVATE;

public class Semestre2Fragment extends Fragment {

    //COMPOSANT DU LAYOUT


    private ExpansionView expansionView;
    private Button enregistrer;
    private Socket mSocket;
    private String serverAdress;
    private ArrayList<ArrayList<UE>> blocEtSaMatiere;
    private ArrayList<UE> listeUEBlocFondement;
    private ArrayList<UE> listeUEBlocMethode;
    private Vue mListener;
    private Button semestre3;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_semestre2, container, false);


        final Client client = (Client) getActivity().getApplication();
        mSocket = client.getUniqueConnexion().getmSocket();
        listeUEBlocFondement = client.getListeUEBlocFondementS2();
        listeUEBlocMethode = client.getListeUEBlocMethodeS2();
        blocEtSaMatiere = client.getBlocEtSaMatiereS2();
        enregistrer = root.findViewById(R.id.boutonEnregistrer2);
        semestre3 = root.findViewById(R.id.boutonSemestre3);



        mSocket.on(EVENT.SAVE, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("EVENT_SOCKET", "Socket event SAVE received");
                        Toast.makeText(getActivity().getApplicationContext(), R.string.saved_on_server, Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        expansionView = new ExpansionView(root, mSocket, getActivity(), enregistrer,client.getSelectionUE(), client.getSelectionCode(),
                listeUEBlocFondement, listeUEBlocMethode, blocEtSaMatiere);
        this.expansionView.setDynamicLayoutContainer((ViewGroup) root.findViewById(R.id.dynamicLayoutContainer));

        expansionView.createExpansion();

        semestre3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.changeFragment(3);
            }
        });
        enregistrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enregistrement(client);
            }
        });



        return root;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (Vue) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;

    }

    public void enregistrement(Client client){
        Log.d("Bouton enregistrer", "Parcours enregistre");
        //activity.getSelectionItem().add(activity.getTextEnjeux().getText().toString());
        //activity.getSelectionItem().add(activity.getTextCompetence().getText().toString());
        String fileName = "mon_parcours_S2";
        client.getNomFichier().add(fileName);
        String final_selection = "SEMESTRE 2\n";
        for (String selections : client.getSelectionUE()){
            Log.d("WRITEFILE", "ecriture de "+ client.getSelectionUE().toString());
            final_selection += selections + "\n";
            Log.d("WRITEFILE", "Valeur de final_selection "+final_selection);

        }
        try {
            FileOutputStream ecriture = getActivity().openFileOutput(fileName, MODE_PRIVATE);
            ecriture.write(final_selection.getBytes());
            ecriture.close();
            client.getSelectionUE().clear();
            Toast.makeText(getActivity(), "Parcours enregistré", Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String code_ue: client.getSelectionCode()) {
            Log.d("SAVE_SERVER", "Envoie de la matière de code "+code_ue+ " au serveur pour enregistrement");
            ((Client)getActivity().getApplicationContext()).getUniqueConnexion().envoyerEvent("Save", code_ue);
        }
    }
}