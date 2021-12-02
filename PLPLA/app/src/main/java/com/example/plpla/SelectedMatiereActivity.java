package com.example.plpla;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import matière.UE;

public class SelectedMatiereActivity extends AppCompatActivity {

    TextView tvMatiere,tvCode,tvSemestre,tvDiscipline,tvECTS,tvNbPlace;
    private AppBarConfiguration mAppBarConfiguration;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_matiere);

        tvMatiere = findViewById(R.id.selectedMatiere);
        tvCode = findViewById(R.id.code);
        tvSemestre = findViewById(R.id.semestre);
        tvDiscipline = findViewById(R.id.discipline);
        tvECTS = findViewById(R.id.ECTS);
        tvNbPlace = findViewById(R.id.nb_place);

        Intent intent =getIntent();

        if(intent.getExtras() != null){
            UE laMatiere = (UE) intent.getSerializableExtra("data");

            tvMatiere.setText(laMatiere.getNomUE());
            tvCode.setText("Code matiere : " + laMatiere.getCode());
            tvSemestre.setText("Semestre : " + laMatiere.getSemestre());
            tvDiscipline.setText("Discipline : " + laMatiere.getDiscipline());
            tvECTS.setText("ECTS : " + laMatiere.getECTS());
            tvNbPlace.setText("Nombre de place : " + laMatiere.getNbPlaces());
        }


    }
}
