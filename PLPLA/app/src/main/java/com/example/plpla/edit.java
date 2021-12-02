package com.example.plpla;

import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


public class edit extends AppCompatActivity {
    private EditText nameInput;
    private EditText telInput;
    private EditText emailInput;
    private EditText  usernameInput ;
    private SharedPreferences prefs;
    private Button Save ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        prefs = getSharedPreferences("MY_DATA", MODE_PRIVATE);
        String name = prefs.getString("MY_NAME", "");
        String username = prefs.getString("MY_USERNAME", "");
        int tel = prefs.getInt("MY_TEL", 0);
        String email = prefs.getString("MY_EMAIL", "");

        nameInput = findViewById(R.id.nameInput);
        usernameInput = findViewById(R.id.usernameInput);
        telInput = findViewById(R.id.telInput);
        emailInput = findViewById(R.id.emailInput);

        // Set default value.
        nameInput.setText(name);
        usernameInput.setText(username);
        telInput.setText(tel+"");
        emailInput.setText(email);


//        Save = findViewById(R.id.Save);
//        Save.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public  void onClick(View v){
//                startActivity(new Intent(getApplicationContext(), ProfilFragment.class));
//
//            }
//
//        });

    }

    public void saveData(View view) {
        // Get input text.
        String name = nameInput.getText().toString();
        String username = usernameInput.getText().toString();
        int tel = Integer.parseInt(telInput.getText().toString());
        String email = emailInput.getText().toString();

        // Save data.
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("MY_NAME", name);
        editor.putString("MY_USERNAME", username);
        editor.putInt("MY_TEL", tel);
        editor.putString("MY_EMAIL", email);
        editor.apply();

        // Return to profilfragment activity.
        startActivity(new Intent(getApplicationContext(), MainNavigation.class));

    }
}
