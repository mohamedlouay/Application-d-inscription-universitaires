package com.example.plpla.ui.profil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.Intent;
import android.content.SharedPreferences;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.widget.Button;

import androidx.lifecycle.ViewModelProviders;

import com.example.plpla.R;
import com.example.plpla.edit;

import java.util.Objects;


public class ProfilFragment extends Fragment {

    private Button Edit ;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_tools, container, false);


        SharedPreferences prefs = getActivity().getSharedPreferences("MY_DATA", getActivity().MODE_PRIVATE);

        String name = prefs.getString("MY_NAME", "no name");
        String username = prefs.getString("MY_USERNAME", "no username");
        int tel = prefs.getInt("MY_TEL", 0);
        String email = prefs.getString("MY_EMAIL", "no email");

        // Set values
        ((TextView) root.findViewById(R.id.nameLabel)).setText(name);
        ((TextView) root.findViewById(R.id.usernameLabel)).setText(username);
        ((TextView) root.findViewById(R.id.Profil)).setText(name+ "  "+username);
        ((TextView) root.findViewById(R.id.Email)).setText(email);

        //((TextView) root.findViewById(R.id.Profil)).setText(username);


        ((TextView) root.findViewById(R.id.telLabel)).setText(tel + "");
        ((TextView) root.findViewById(R.id.emailLabel)).setText(email);
        //showEdit();

        Edit =  root.findViewById(R.id.Edit );
        Edit.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                startActivity(new Intent(getActivity().getApplicationContext(), edit.class));
                ;
            }

        });

        return root ;
    }

    public void showEdit(View view) {
        startActivity(new Intent(getActivity().getApplicationContext(), edit.class));
   }
}
