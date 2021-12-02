package com.example.plpla.ui.slideshow;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.SearchView;



import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plpla.R;
import com.example.plpla.RecyclerAdapter;
import com.example.plpla.SelectedMatiereActivity;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

import matière.UE;

/**
 * classe representant la recherche dans l'app
 */
public class SlideshowFragment extends Fragment implements RecyclerAdapter.SelectedMatiere {

    private RecyclerView recyclerView;

    public static RecyclerAdapter getRecyclerAapter() {
        return recyclerAapter;
    }

    static RecyclerAdapter recyclerAapter;

    /**
     * onCreateView est appelé ,on assigne notre vue, c'est la vue principale.
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return une vue view root
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_recyclerview, container, false);
        setHasOptionsMenu(true);

        ArrayList<UE> okokokok = listesdesmatieres();


        recyclerView = root.findViewById(R.id.recyclerView);


        recyclerAapter = new RecyclerAdapter(okokokok, this);


        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerAapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        return root;
    }


    /**
     * fonction de creation de matiere dans la liste de recherche
     * @return list , la liste des matieres de tout les semestres
     */
    private ArrayList<UE> listesdesmatieres() {
        ArrayList<UE> list = new ArrayList<>();

        InputStream is = getResources().openRawResource(R.raw.matieres);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, Charset.defaultCharset()));
        String readLine = "";
        String cvsSplitBy = ",";

        try {

            reader.readLine();

            while ((readLine = reader.readLine()) != null) {

                String[] parts = readLine.split(cvsSplitBy);
                list.add(new UE(parts[0], parts[1], parts[2], Integer.parseInt(parts[3]), Integer.parseInt(parts[4]), Integer.parseInt(parts[5])));

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return list;
    }


    @Override
    public void selectedMatiere(UE matiere) {
        Intent intent = new Intent(getActivity(), SelectedMatiereActivity.class).putExtra("data", matiere);
        startActivity(intent);
    }

    /**
     * fonction qui gere la barre de recherche
     * @param menu un menu
     * @param inflater transformer un objet qui n'est décrit qu'en XML en véritable objet qu'on peut manipuler.
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_navigation, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        item.setVisible(true);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setQueryHint("chercher une matiere");
        // geré le double click de recherche //

        searchView.setIconifiedByDefault(false);
        implementSearch(menu);
        //                                   //

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                recyclerAapter.getFilter().filter(newText);
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }



    /**
     * fonction pour gerer le bug
     * @param menu un menu
     */
    private void implementSearch(final Menu menu) {
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);

        final MenuItem searchMenuItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) searchMenuItem.getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setIconifiedByDefault(false);

        searchMenuItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        searchView.requestFocus();
                        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                        if (imm != null) { // juste pour plaire au compilateur
                            imm.showSoftInput(searchView.findFocus(), 0);
                        }


                    }
                });
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                return true;
            }
        });
    }
    //                                                                  //


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_slideshow) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}