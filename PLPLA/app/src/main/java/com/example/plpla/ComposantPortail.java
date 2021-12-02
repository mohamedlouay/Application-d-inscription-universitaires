package com.example.plpla;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.lifecycle.AndroidViewModel;

import com.github.florent37.expansionpanel.ExpansionHeader;
import com.github.florent37.expansionpanel.ExpansionLayout;

public class ComposantPortail {

    private TextView nomMatiere;
    private ExpansionHeader boutonMatiere;
    private CheckBox checkboxMatiere;
    private ExpansionLayout expansionMatiere;
    private ImageView imageGroupeMatiere;

    public ComposantPortail(View bouton , View expansion , View text , View check , View img ){
        boutonMatiere = (ExpansionHeader) bouton;
        expansionMatiere = (ExpansionLayout) expansion;
        nomMatiere = (TextView) text;
        checkboxMatiere = (CheckBox) check;
        imageGroupeMatiere = (ImageView) img;
    }

    public TextView getNomMatiere() {
        return nomMatiere;
    }

    public ExpansionHeader getBoutonMatiere() {
        return boutonMatiere;
    }

    public CheckBox getCheckboxMatiere() {
        return checkboxMatiere;
    }

    public ExpansionLayout getExpansionMatiere() {
        return expansionMatiere;
    }

    public ImageView getImageGroupeMatiere() {
        return imageGroupeMatiere;
    }

}
