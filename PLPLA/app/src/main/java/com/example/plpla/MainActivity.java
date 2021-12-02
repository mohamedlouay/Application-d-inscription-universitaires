package com.example.plpla;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import events.EVENT;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import matière.UE;
import user.User;

public class MainActivity extends AppCompatActivity {
    /**
     * Splash screen, le message d'acceuil au lancement de l'app
     * 1000 millisecondes par défaut (1 seconde)
     * */
    private static int TEMPS_MESSAGE_ACCEUIL = 600;
    private String ipAddress;
    private Button nextActivity;
    private EditText nomUser;
    private EditText prenomUser;
    private SharedPreferences prefs;


    /*L'objet client qui existe pendant toute l'application*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nextActivity = findViewById(R.id.button);
        nomUser = findViewById(R.id.editTextNom);
        prenomUser = findViewById(R.id.editTextPrenom);

        /**
         * Le nom et le prénom ont un maximum de 20 caractères
         */
        nomUser.setFilters(new InputFilter[] {new InputFilter.LengthFilter(20)});
        prenomUser.setFilters(new InputFilter[] {new InputFilter.LengthFilter(20)});


        prefs = getSharedPreferences("MY_DATA", MODE_PRIVATE);
        String name = prefs.getString("MY_NAME", "");
        String username = prefs.getString("MY_USERNAME", "");

        nomUser.setText(name);
        prenomUser.setText(username);
        //-------------------------------------------------------------------


        /*Passe au Fragment Home (L'activity MainNavigation) */
        passeAHome();
    }


    private void passeAHome(){

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent homeIntent = new Intent(MainActivity.this, HomeActivity.class);
//                startActivity(homeIntent);
//                finish();
//            }
//        }, TEMPS_MESSAGE_ACCEUIL);



        nextActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ipAddress = "http://10.0.2.2:4444";
                /**
                 * Initialisation des identifiants, Nom et prénom, dans l'objet User créé dans la classe Client
                 */
                ((Client)getApplicationContext()).getUser().setNom(nomUser.getText().toString());
                ((Client)getApplicationContext()).getUser().setPrenom(prenomUser.getText().toString());
                ((Client)getApplicationContext()).getUser().setListe_choix(new ArrayList<UE>());

                ((Client)getApplicationContext()).getUniqueConnexion().setServerAddress(ipAddress);
                ((Client)getApplicationContext()).getUniqueConnexion().initConnexion();
                ((Client)getApplicationContext()).getUniqueConnexion().connecte();
                /**
                 * Affiche un Toast si on recçoit l'event CONNECT
                 */
                ((Client)getApplicationContext()).getUniqueConnexion().getmSocket().on(Socket.EVENT_CONNECT, new Emitter.Listener() {
                    @Override
                    public void call(Object... args) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), R.string.connect, Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });
                String name = nomUser.getText().toString();
                String username = prenomUser.getText().toString();
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("MY_NAME", name);
                editor.putString("MY_USERNAME", username);
                editor.apply();
                Intent homeIntent = new Intent(MainActivity.this, MainNavigation.class);
                /*Passer la variable ipAddress au Fragment Home (L'activity MainNavigation) */
                homeIntent.putExtra("url", ipAddress);
                startActivity(homeIntent);
                finish();

            }
        });

    }

//    public void saveData(View view) {
//        String name = nomUser.getText().toString();
//        String username = prenomUser.getText().toString();
//        SharedPreferences.Editor editor = prefs.edit();
//        editor.putString("MY_NAME", name);
//        editor.putString("MY_USERNAME", username);
//        editor.apply();
//        startActivity(new Intent(getApplicationContext(), MainNavigation.class));
//
//
//    }



}
