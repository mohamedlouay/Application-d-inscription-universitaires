package com.example.plpla;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

import matière.UE;

public class PageDeRecherche extends AppCompatActivity implements RecyclerAdapter.SelectedMatiere  {

    RecyclerView recyclerView;
    RecyclerAdapter recyclerAapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        //////////////
        ArrayList<UE> okokokok=listesdesmatieres();
        ////////////////

        recyclerView = findViewById(R.id.recyclerView);
        ///////////////////////////////////////////////
        recyclerAapter=new RecyclerAdapter(okokokok,this);
        ///////////////////////////////////////////////


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerAapter);

        DividerItemDecoration dividerItemDecoration= new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

    }

    //////////////////////////////
    private ArrayList<UE> listesdesmatieres() {
        ArrayList<UE> list = new ArrayList<>();


        list.add(new UE("MATHS","MS145","mathematique",1,6,100));
        list.add(new UE("HISTOIRE","HS085","histoire ancienne",1,6,100));
        list.add(new UE("SCIENCE","SC065","science de la vie",1,6,100));
        list.add(new UE("ELECTRONIQUE","EL025","electronique",1,6,100));
        list.add(new UE("AUTOMATE","AU130","Informatique",2,4,80));
        list.add(new UE("ANGLAIS","AN478","Langue vivante",1,2,300));
        list.add(new UE("PROBABILITE","PR116","Mathematique",3,6,150));
        list.add(new UE("ANALYSE","AN056","Mathematique",1,6,120));
        list.add(new UE("SYSTEME","SY144","Informatique",1,6,80));
        list.add(new UE("RESEAU","RE149","Informatique",2,4,150));

        return list;
    }
    //////////////////////////////////////////////////


    @Override
    public void selectedMatiere(UE matiere) {

        startActivity(new Intent(PageDeRecherche.this, SelectedMatiereActivity.class).putExtra("data", matiere));



    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_navigation, menu);
        MenuItem item  = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setIconifiedByDefault(false);
        implementSearch(menu);

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
        return super.onCreateOptionsMenu(menu);
    }


    private void implementSearch(final Menu menu) {
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final MenuItem searchMenuItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) searchMenuItem.getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);

        searchMenuItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener(){
            @Override
            public boolean onMenuItemActionExpand(MenuItem item){
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        searchView.requestFocus();
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        if (imm != null) {
                            imm.showSoftInput(searchView.findFocus(), 0);
                        }


                    }
                });
                return true;
            }


            @Override
            public boolean onMenuItemActionCollapse(MenuItem item){
                // the search view is closing. add your logic if you want
                return true;
            }

        });

    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.nav_slideshow){
            return  true;
        }

        return super.onOptionsItemSelected(item);
    }
}
