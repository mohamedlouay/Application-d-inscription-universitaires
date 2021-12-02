package com.example.plpla.Expansion;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;

import com.example.plpla.R;
import com.example.plpla.controleur.ListenerButton;
import com.example.plpla.ui.home.PortailFragment;
import com.github.florent37.expansionpanel.ExpansionLayout;
import com.github.florent37.expansionpanel.viewgroup.ExpansionLayoutCollection;

import java.util.ArrayList;
import java.util.Arrays;

import io.socket.client.Socket;
import matière.UE;

import static com.example.plpla.Expansion.Utils.dpToPx;

public class ExpansionView {

    public void setDynamicLayoutContainer(ViewGroup dynamicLayoutContainer) {
        this.dynamicLayoutContainer = dynamicLayoutContainer;
    }

    private ViewGroup dynamicLayoutContainer;
    private int compteur = 0;
    private ArrayList<ExpansionHeader> listeHeaderTotal =  new ArrayList<>();
    private Button enregistrer;
    private ArrayList<String> selectionUE;
    private ArrayList<String> selectionCode;
    private Context activity;
    private View root;
    private Socket mSocket;
    private ArrayList<UE> listeUeBloc2;
    private ArrayList<UE> listeUeBloc1;
    private ArrayList<ArrayList<UE>> blocEtSaMatiere;
    private ArrayList<UE> blocSeul;
    private int nbBloc = 0;
    private ArrayList<ArrayList<ExpansionHeader>> UeAvecCategories = new ArrayList<>();
    private ArrayList<ExpansionHeader> UeSansCategories = new ArrayList<>();
    private ArrayList<ExpansionHeader> UeConfondus = new ArrayList<>();

    public ExpansionView(View root, Socket mSocket, Context activity,
                         Button enregistrer, ArrayList<String> selectionUE, ArrayList<String> selectionCode,
                         ArrayList<UE> listeUeBloc1, ArrayList<UE> listeUeBloc2, ArrayList<ArrayList<UE>> blocEtSaMatiere){
        this.root = root;
        this.mSocket = mSocket;
        this.activity = activity;
        this.enregistrer = enregistrer;
        this.selectionUE = selectionUE;
        this.selectionCode = selectionCode;
        this.listeUeBloc1 = listeUeBloc1;
        this.listeUeBloc2 = listeUeBloc2;
        this.blocEtSaMatiere = blocEtSaMatiere;
    }

    public ExpansionView(View root, Socket mSocket, Context activity,
                         Button enregistrer, ArrayList<String> selectionUE, ArrayList<String> selectionCode,
                         ArrayList<UE> listeUeBloc1, ArrayList<UE> blocSeul, int nbBloc){
        this.root = root;
        this.mSocket = mSocket;
        this.activity = activity;
        this.enregistrer = enregistrer;
        this.selectionUE = selectionUE;
        this.selectionCode = selectionCode;
        this.listeUeBloc1 = listeUeBloc1;
        this.blocSeul = blocSeul;
        this.nbBloc = nbBloc;
    }

    public void createExpansion(){

        if (nbBloc == 1) {
            final ArrayList<ArrayList> listeHeader = new ArrayList(Arrays.asList(addDynamicLayout(blocSeul.get(0), listeUeBloc1)));
            listeHeaderTotal.add((ExpansionHeader) listeHeader.get(0).get(0));

            final ExpansionLayout blocLayout = (ExpansionLayout) listeHeader.get(0).get(1);
            blocLayout.addListener(new ExpansionLayout.Listener() {
                @Override
                public void onExpansionChanged(ExpansionLayout expansionLayout, boolean expanded) {
                    actionOnSelection((ExpansionHeader) listeHeader.get(0).get(0), listeHeader, blocSeul.get(0));
                }
            });

            final ExpansionLayoutCollection expansionLayoutCollection = new ExpansionLayoutCollection();
            expansionLayoutCollection.add((ExpansionLayout) listeHeader.get(0).get(1));
            expansionLayoutCollection.openOnlyOne(true);

        }
        else {
            final ArrayList<ArrayList> listeHeader = new ArrayList(Arrays.asList(addDynamicLayout(blocEtSaMatiere.get(0).get(0), listeUeBloc1),addDynamicLayout(blocEtSaMatiere.get(1).get(0), listeUeBloc2)));
            listeHeaderTotal.add((ExpansionHeader) listeHeader.get(0).get(0));
            listeHeaderTotal.add((ExpansionHeader) listeHeader.get(1).get(0));
            //example of how to add a listener
            final ExpansionLayout fondementLayout = (ExpansionLayout) listeHeader.get(0).get(1);
            fondementLayout.addListener(new ExpansionLayout.Listener() {
                @Override
                public void onExpansionChanged(ExpansionLayout expansionLayout, boolean expanded) {
                    actionOnSelection((ExpansionHeader) listeHeader.get(0).get(0), listeHeader, blocEtSaMatiere.get(0).get(1));
                }
            });

            final ExpansionLayout methodeLayout = (ExpansionLayout) listeHeader.get(1).get(1);
            methodeLayout.addListener(new ExpansionLayout.Listener() {
                @Override
                public void onExpansionChanged(ExpansionLayout expansionLayout, boolean expanded) {
                    actionOnSelection((ExpansionHeader) listeHeader.get(1).get(0), listeHeader, blocEtSaMatiere.get(1).get(1));
                }
            });

            final ExpansionLayoutCollection expansionLayoutCollection = new ExpansionLayoutCollection();
            expansionLayoutCollection.add((ExpansionLayout) listeHeader.get(0).get(1)).add((ExpansionLayout) listeHeader.get(1).get(1));
            expansionLayoutCollection.openOnlyOne(true);
        }



        //BOUTON ENREGISTRER
        //enregistrer = root.findViewById(R.id.boutonEnregistrer);



        /*Par défaut Enregistrer n'est pas cliquable*/
        enregistrer.setClickable(false);
        enregistrer.setEnabled(false);
    }

    public ArrayList addDynamicLayout(UE bloc, ArrayList<UE> listeBloc) {

        final ExpansionHeader expansionHeader = createExpansionHeader(bloc);
        dynamicLayoutContainer.addView(expansionHeader, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        final ExpansionLayout expansionLayout = createExpansionLayout(listeBloc);
        dynamicLayoutContainer.addView(expansionLayout, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        expansionHeader.setExpansionLayout(expansionLayout);

        return new ArrayList(Arrays.asList(expansionHeader,expansionLayout));

    }



    @NonNull
    private ExpansionLayout createExpansionLayout(ArrayList<UE> listeBloc) {
        final ExpansionLayout expansionLayout = new ExpansionLayout(activity);
        final ArrayList<ArrayList> listeHeader2emeNiveau = new ArrayList<>();

        final LinearLayout layout = new LinearLayout(activity);
        layout.setOrientation(LinearLayout.VERTICAL);
        expansionLayout.addView(layout, ViewGroup.LayoutParams.MATCH_PARENT, dpToPx(activity, 48)); //equivalent to addView(linearLayout)


        final ArrayList<UE> listeDiscipline = new ArrayList<>();
        ArrayList<String> listeNomDiscipline = new ArrayList<>();

        for (int i = 0; i < listeBloc.size(); i++) {
            if ((listeBloc.get(i).getSansSousMatieres() == true)) {
                listeDiscipline.add(listeBloc.get(i));
                listeNomDiscipline.add(listeBloc.get(i).getDiscipline());
            } else if (!(listeBloc.get(i).getSansSousMatieres() == true) && !listeNomDiscipline.contains(listeBloc.get(i).getDiscipline())) {
                listeDiscipline.add(listeBloc.get(i));
                listeNomDiscipline.add(listeBloc.get(i).getDiscipline());
            }
        }

        for (int i = 0; i < listeDiscipline.size(); i++) {
            final ExpansionHeader headerchoix  = choixHeader(listeDiscipline.get(i));
            final ExpansionLayout layoutchoix = choixLayout(listeDiscipline.get(i),listeBloc);
            headerchoix.setExpansionLayout(layoutchoix);
            layout.addView(headerchoix);
            layout.addView(layoutchoix);
            if (!(headerchoix.getWithoutCheck()) && !(headerchoix.getCheckboxHeader().isChecked())) {
                listeHeader2emeNiveau.add(new ArrayList<>(Arrays.asList(headerchoix, layoutchoix, listeDiscipline.get(i))));
                listeHeaderTotal.add(headerchoix);
                UeConfondus.add(headerchoix);
                UeSansCategories.add(headerchoix);
            }
        }

        for (int i = 0; i < listeHeader2emeNiveau.size(); i++) {
            ExpansionLayout layoutDeListe = (ExpansionLayout) listeHeader2emeNiveau.get(i).get(1);
            final ExpansionHeader headerDeListe = (ExpansionHeader) listeHeader2emeNiveau.get(i).get(0);
            final int finalI = i;
            layoutDeListe.addListener(new ExpansionLayout.Listener() {
                @Override
                public void onExpansionChanged(ExpansionLayout expansionLayout, boolean expanded) {
                    actionOnSelection(headerDeListe,listeHeader2emeNiveau, listeDiscipline.get(finalI));
                }
            });
        }


        return expansionLayout;
    }

    @SuppressLint("ResourceType")
    @NonNull
    private ExpansionHeader createExpansionHeader(UE bloc) {
        final ExpansionHeader expansionHeader = new ExpansionHeader(activity);
        expansionHeader.setId(1);
        expansionHeader.setBlock(true);
        expansionHeader.setBackgroundColor(Color.parseColor("#145795"));
        expansionHeader.setPadding(dpToPx(activity, 16), dpToPx(activity, 16), dpToPx(activity, 16), dpToPx(activity, 16));

        final RelativeLayout layout = new RelativeLayout(activity);
        expansionHeader.addView(layout, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT); //equivalent to addView(linearLayout)
        expansionHeader.setText(bloc.getDiscipline());
        //checkbox
        //final CheckBox checkboxHeader = new CheckBox(this);
        final RelativeLayout.LayoutParams imageLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        imageLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        imageLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
        layout.addView(expansionHeader.getCheckboxHeader(), imageLayoutParams);
        /*bloc.setId(id);
        checkboxHeader.setId(bloc.getId());
        id++;*/


        /*//image
        final ImageView expansionIndicator = new AppCompatImageView(this);
        expansionIndicator.setImageResource(R.drawable.ic_expansion_header_indicator_grey_24dp);
        final RelativeLayout.LayoutParams imageLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        imageLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        imageLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
        layout.addView(expansionIndicator, imageLayoutParams);*/

        //label
        final TextView text = new TextView(activity);
        text.setText(bloc.getDiscipline());
        text.setTextColor(Color.WHITE);

        final RelativeLayout.LayoutParams textLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        textLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL);

        layout.addView(text, textLayoutParams);

        //expansionHeader.setExpansionHeaderIndicator(expansionIndicator);
        return expansionHeader;
    }




    @NonNull
    private ExpansionHeader choixHeader(UE matiere) {
        final ExpansionHeader expansionHeader = new ExpansionHeader(activity);
        if (matiere.getSansSousMatieres() == true) {
            expansionHeader.setBackgroundColor(Color.WHITE);
        }
        else expansionHeader.setBackgroundColor(Color.parseColor("#B6B8B9"));
        expansionHeader.setPadding(dpToPx(activity, 22), dpToPx(activity, 16), dpToPx(activity, 16), dpToPx(activity, 16));

        final RelativeLayout layout = new RelativeLayout(activity);
        expansionHeader.addView(layout, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT); //equivalent to addView(linearLayout)

        if (matiere.getSansSousMatieres() == true){
            //checkbox
            //final CheckBox checkboxHeader = new CheckBox(this);
            final RelativeLayout.LayoutParams imageLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            imageLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            imageLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
            layout.addView(expansionHeader.getCheckboxHeader(), imageLayoutParams);
            expansionHeader.setNoChoix(true);
            expansionHeader.setText(matiere.getNomUE());
            if (matiere.getPreChecked()) {
                expansionHeader.getCheckboxHeader().setChecked(true);
                expansionHeader.getCheckboxHeader().setEnabled(false);
                expansionHeader.setEnabled(false);
                expansionHeader.setPreChecked(true);
            }
        }
        else {
            //image
            expansionHeader.setWithoutCheck(true);
            final ImageView expansionIndicator = new AppCompatImageView(activity);
            expansionIndicator.setImageResource(R.drawable.ic_expansion_header_indicator_grey_24dp);
            final RelativeLayout.LayoutParams imageLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            imageLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            imageLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
            layout.addView(expansionIndicator, imageLayoutParams);
            expansionHeader.setExpansionHeaderIndicator(expansionIndicator);
        }



        //label
        final TextView text = new TextView(activity);
        if (matiere.getSansSousMatieres() == true) {
            text.setText(matiere.getNomUE());
        } else text.setText(matiere.getDiscipline());
        text.setTextColor(Color.parseColor("#3E3E3E"));

        final RelativeLayout.LayoutParams textLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        textLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL);

        layout.addView(text, textLayoutParams);

        return expansionHeader;
    }

    @NonNull
    private ExpansionLayout choixLayout(UE matiere, final ArrayList<UE> listeBloc) {
        final ExpansionLayout expansionLayout = new ExpansionLayout(activity);
        final ArrayList<ArrayList> listeHeader3emeNiveau = new ArrayList<>();

        final LinearLayout layout = new LinearLayout(activity);
        layout.setOrientation(LinearLayout.VERTICAL);
        expansionLayout.addView(layout, ViewGroup.LayoutParams.MATCH_PARENT, dpToPx(activity, 48)); //equivalent to addView(linearLayout)

        /*final TextView text = new TextView(this);
        text.setText("Content");
        text.setGravity(Gravity.CENTER);
        text.setTextColor(Color.parseColor("#3E3E3E"));
        text.setBackgroundColor(Color.parseColor("#EEEEEE"));
        layout.addView(text, ViewGroup.LayoutParams.MATCH_PARENT, dpToPx(this, 200));

        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final View child = new View(ExpansionPanelSampleActivityProgrammatically.this);
                child.setBackgroundColor(Color.BLACK);
                layout.addView(child, ViewGroup.LayoutParams.MATCH_PARENT, 100);
            }
        });*/

        for (int i = 0; i < listeBloc.size(); i++) {
            if ((listeBloc.get(i).getDiscipline() == matiere.getDiscipline()) && !(listeBloc.get(i).getSansSousMatieres() == true)) {
                ExpansionHeader headerue  = matiereHeader(listeBloc.get(i));
                ExpansionLayout layoutue = matiereLayout();
                headerue.setExpansionLayout(layoutue);
                layout.addView(headerue);
                layout.addView(layoutue);
                listeHeader3emeNiveau.add(new ArrayList<>(Arrays.asList(headerue, layoutue, listeBloc.get(i))));
                listeHeaderTotal.add(headerue);
            }

        }

        for (int i = 0; i < listeHeader3emeNiveau.size(); i++) {
            ExpansionLayout layoutDeListe = (ExpansionLayout) listeHeader3emeNiveau.get(i).get(1);
            final ExpansionHeader headerDeListe = (ExpansionHeader) listeHeader3emeNiveau.get(i).get(0);
            final int finalI = i;
            layoutDeListe.addListener(new ExpansionLayout.Listener() {
                @Override
                public void onExpansionChanged(ExpansionLayout expansionLayout, boolean expanded) {
                    actionOnSelection(headerDeListe,listeHeader3emeNiveau, listeBloc.get(finalI));
                }
            });
        }


        return expansionLayout;
    }


    @NonNull
    private ExpansionHeader matiereHeader(UE matiere) {
        final ExpansionHeader expansionHeader = new ExpansionHeader(activity);
        expansionHeader.setBackgroundColor(Color.WHITE);
        expansionHeader.setPadding(dpToPx(activity, 28), dpToPx(activity, 16), dpToPx(activity, 16), dpToPx(activity, 16));

        final RelativeLayout layout = new RelativeLayout(activity);
        expansionHeader.addView(layout, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT); //equivalent to addView(linearLayout)
        expansionHeader.setText(matiere.getNomUE());

        //checkbox
        //final CheckBox checkboxHeader = new CheckBox(this);
        final RelativeLayout.LayoutParams imageLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        imageLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        imageLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
        layout.addView(expansionHeader.getCheckboxHeader(), imageLayoutParams);

        if (matiere.getPreChecked()) {
            expansionHeader.getCheckboxHeader().setChecked(true);
            expansionHeader.getCheckboxHeader().setEnabled(false);
            expansionHeader.setEnabled(false);
            expansionHeader.setPreChecked(true);
        }

        //label
        final TextView text = new TextView(activity);
        text.setText(matiere.getNomUE());
        text.setTextColor(Color.parseColor("#3E3E3E"));

        final RelativeLayout.LayoutParams textLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        textLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL);

        layout.addView(text, textLayoutParams);

        return expansionHeader;
    }

    @NonNull
    private ExpansionLayout matiereLayout() {
        final ExpansionLayout expansionLayout = new ExpansionLayout(activity);

        final LinearLayout layout = new LinearLayout(activity);
        layout.setOrientation(LinearLayout.VERTICAL);
        expansionLayout.addView(layout, ViewGroup.LayoutParams.MATCH_PARENT, dpToPx(activity, 48)); //equivalent to addView(linearLayout)


        return expansionLayout;
    }

    public void actionOnSelection(ExpansionHeader header, ArrayList<ArrayList> header2, UE matiere){
        if ((header.getCheckboxHeader().isChecked())){
            compteur++;
            if (compteur >= getNbMatiere()){
                for (int i = 0; i < listeHeaderTotal.size(); i++) {
                    if ((header != listeHeaderTotal.get(i)) && !(listeHeaderTotal.get(i).getCheckboxHeader().isChecked())) {
                        listeHeaderTotal.get(i).getCheckboxHeader().setEnabled(false);
                        listeHeaderTotal.get(i).setEnabled(false);
                        enregistrer.setEnabled(true);
                    }
                }

                for (int i = 0; i < header2.size(); i++) {
                    ExpansionHeader headerf = (ExpansionHeader) header2.get(i).get(0);
                    if ((header != headerf) && !(headerf.getCheckboxHeader().isChecked()) && (header.getNoChoix() == false)) {
                        headerf.getCheckboxHeader().setEnabled(false);
                        headerf.setEnabled(false);
                        headerf.setProcheChecked(true);

                    }

                }
            }
            else {
                for (int i = 0; i < header2.size(); i++) {
                    ExpansionHeader headerf = (ExpansionHeader) header2.get(i).get(0);
                    if ((header != headerf) && !(headerf.getCheckboxHeader().isChecked()) && (header.getNoChoix() == false)) {
                        headerf.getCheckboxHeader().setEnabled(false);
                        headerf.setEnabled(false);
                        headerf.setProcheChecked(true);

                    }

                }
            }

            selectionUE.add(header.getText());
            selectionCode.add(matiere.getCode());

        }
        else {
            compteur--;

            if (header.getNoChoix()==false){
                for (int i = 0; i < listeHeaderTotal.size(); i++) {
                    if ((header != listeHeaderTotal.get(i)) && !(listeHeaderTotal.get(i).getCheckboxHeader().isChecked()) && listeHeaderTotal.get(i).getProcheChecked()==false) {
                        listeHeaderTotal.get(i).getCheckboxHeader().setEnabled(true);
                        listeHeaderTotal.get(i).setEnabled(true);
                    }
                }
                for (int i = 0; i < header2.size(); i++) {
                    ExpansionHeader headerf = (ExpansionHeader) header2.get(i).get(0);
                    if ((header != headerf) && !(headerf.getCheckboxHeader().isChecked()) && (header.getNoChoix() == false)) {
                        headerf.getCheckboxHeader().setEnabled(true);
                        headerf.setEnabled(true);
                        headerf.setProcheChecked(false);

                    }

                }

            }
            else if (header.getBlock()==true) {
                for (int i = 0; i < listeHeaderTotal.size(); i++) {
                    if ((header != listeHeaderTotal.get(i)) && !(listeHeaderTotal.get(i).getCheckboxHeader().isChecked())) {
                        listeHeaderTotal.get(i).getCheckboxHeader().setEnabled(true);
                        listeHeaderTotal.get(i).setEnabled(true);
                    }
                }
            }
            else {
                for (int i = 0; i < listeHeaderTotal.size(); i++) {
                    if ((header != listeHeaderTotal.get(i)) && !(listeHeaderTotal.get(i).getCheckboxHeader().isChecked()) && listeHeaderTotal.get(i).getProcheChecked()==false) {
                        listeHeaderTotal.get(i).getCheckboxHeader().setEnabled(true);
                        listeHeaderTotal.get(i).setEnabled(true);
                    }
                }
            }

            enregistrer.setEnabled(false);
            selectionUE.remove(header.getText());
            selectionCode.remove(matiere.getCode());
        }
    }

    public int getNbMatiere() {
        if (nbBloc==1) return 5;
        else return 4;
    }
}
