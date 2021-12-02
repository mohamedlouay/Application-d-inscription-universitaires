package com.example.plpla.ui.home;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.plpla.Client;
import com.example.plpla.Expansion.ExpansionView;
import com.example.plpla.R;
import com.example.plpla.controleur.ListenerButton;
import com.example.plpla.vue.Vue;

import java.util.ArrayList;

import events.EVENT;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import matière.UE;

public class PortailFragment extends Fragment {

    //COMPOSANT DU LAYOUT


    public ExpansionView getExpansionView() {
        return expansionView;
    }

    private ExpansionView expansionView;
    private Button enregistrer;
    private Button semestre2;
    private Socket mSocket;
    private String serverAdress;
    private ArrayList<ArrayList<UE>> blocEtSaMatiere;
    private ArrayList<UE> listeUEBlocFondement;
    private ArrayList<UE> listeUEBlocMethode;
    private Vue mListener;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_semestre1, container, false);

        Client client = (Client) getActivity().getApplication();
        mSocket = client.getUniqueConnexion().getmSocket();
        listeUEBlocFondement = client.getListeUEBlocFondement();
        listeUEBlocMethode = client.getListeUEBlocMethode();
        blocEtSaMatiere = client.getBlocEtSaMatiere();
        enregistrer = root.findViewById(R.id.boutonEnregistrer);
        semestre2 = root.findViewById(R.id.boutonSemestre2);


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

        ListenerButton listenerButton = new ListenerButton(mSocket, getActivity(), client);
        enregistrer.setOnClickListener(listenerButton);

        semestre2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.changeFragment(2);
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

    /*@Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        getActivity().finish();

    }*/


}