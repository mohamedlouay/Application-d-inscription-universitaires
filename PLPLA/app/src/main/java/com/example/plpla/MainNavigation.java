package com.example.plpla;

import android.content.Intent;
import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.plpla.ui.home.PortailFragment;

import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.plpla.ui.semestre2.Semestre2Fragment;
import com.example.plpla.ui.semestre3.Semestre3Fragment;
import com.example.plpla.ui.semestre4.Semestre4Fragment;
import com.example.plpla.vue.Vue;
import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

import events.EVENT;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import matière.UE;
import user.User;

public class MainNavigation extends AppCompatActivity implements Vue {

    private AppBarConfiguration mAppBarConfiguration;



    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_navigation);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        PortailFragment portailFragment = new PortailFragment();
        portailFragment.setArguments(extras);


        JSONObject user = ((Client)getApplicationContext()).getUser().toJSON();

        Log.d("EVENT_SOCKET", "Envoie de l'utilisateur "+ ((Client)getApplicationContext()).getUser().getNom() +" au serveur");
        ((Client)getApplicationContext()).getUniqueConnexion().envoyerEvent(EVENT.ADD_USER, user);

        /**
         * Réception de l'adresse ip du client dans le serveur
         */
        ((Client)getApplicationContext()).getUniqueConnexion().getmSocket().on(EVENT.ADD_USER, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                //TODO la réception de la liste UE et set la liste choix du client avec liste reçu
                Log.d("EVENT_SOCKET", "ADD_USER : Reception du server de la liste des choix sauvegardés "+ ((Client)getApplicationContext()).getUser().getNom() +
                        " --> " + ((Client)getApplicationContext()).getUser().getListe_choix());
            }
        });

        /**
         * Réception de la déconnexion
         */
        ((Client)getApplicationContext()).getUniqueConnexion().getmSocket().on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Log.d("EVENT_SOCKET", "DISCONNECT :" + ((Client)getApplicationContext()).getUser().getNom() + " a été déconnecté du serveur");
                Toast.makeText(getApplicationContext(), R.string.disconnect, Toast.LENGTH_LONG).show();
            }
        });



        /*FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

         */

        // Pour afficher le nom et prenom de l'utilisateur dans le menu //
        SharedPreferences prefs;
        prefs = getSharedPreferences("MY_DATA", MODE_PRIVATE);
        String nameU = prefs.getString("MY_NAME", "");
        String username = prefs.getString("MY_USERNAME", "");

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);


        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.nom_utilisateur_menu);
        navUsername.setText(nameU +"  "+ username);




        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery,R.id.nav_share, R.id.nav_slideshow,R.id.nav_tools)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        
        
        
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_navigation, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void changeFragment(int id) {
        if (id == 1) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction()
                    .addToBackStack("tag");
            ft.replace(R.id.nav_host_fragment, new PortailFragment());
            ft.commit();
        }
        else if (id == 2) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction()
                    .addToBackStack("tag");
            ft.replace(R.id.nav_host_fragment, new Semestre2Fragment());
            ft.commit();
        }
        else if (id == 3) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction()
                    .addToBackStack("tag");
            ft.replace(R.id.nav_host_fragment, new Semestre3Fragment());
            ft.commit();
        }
        else if (id == 4) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction()
                    .addToBackStack("tag");
            ft.replace(R.id.nav_host_fragment, new Semestre4Fragment());
            ft.commit();
        }
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

}
