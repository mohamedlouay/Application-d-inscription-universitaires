package com.example.plpla;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import matière.UE;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> implements Filterable {
    private static final String TAG = "RecyclerAdapter";


    private List<UE> donnes;
    private List<UE> toutdonnes;
    private SelectedMatiere selectedMatiere;

    public RecyclerAdapter(List<UE> donnes,SelectedMatiere selectedMatiere) {
        this.donnes = donnes;
        this.toutdonnes=new ArrayList<>(donnes);
        this.selectedMatiere = selectedMatiere;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i(TAG,"onCreateViewHolder:");
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_item, parent, false);


        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }


    /**
     * fonction d'initialiqtaion de l'interface graphique des matieres lors de leur creation dans la recherche
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,int position) {

        /////////////////////////////////////////
        UE matieres= donnes.get(position);
        holder.name.setText(matieres.getNomUE());
        ///////////////////////////////////////////
            switch (matieres.getDiscipline()) {
                case ("Informatique"):
                    holder.imageView.setImageResource(R.drawable.ic_info);
                    break;

                case ("Geographie"):
                    holder.imageView.setImageResource(R.drawable.ic_geo);
                    break;
                case ("Mathematique"):
                    holder.imageView.setImageResource(R.drawable.ic_maths);
                    break;
                case ("SV"):
                    holder.imageView.setImageResource(R.drawable.ic_sv);
                    break;
                case ("CHIMIE"):
                    holder.imageView.setImageResource(R.drawable.ic_chimie);
                    break;
                case ("MIASHS"):
                    holder.imageView.setImageResource(R.drawable.ic_miash);
                    break;
                case("Physique"):
                    holder.imageView.setImageResource(R.drawable.ic_physique);
                    break;
                case("Electronique"):
                    holder.imageView.setImageResource(R.drawable.ic_electro);
                    break;
                case("Chimie"):
                    holder.imageView.setImageResource(R.drawable.ic_chimie);
                    break;
                case("Sciences de la terre"):
                    holder.imageView.setImageResource(R.drawable.ic_sc_terre);
                    break;
            }

//pour afficher l'index dans la grille des matieres:
        //holder.rowCountTextView.setText(String.valueOf(position));
// pour afficher la discipline dans la grille des matieres:
        holder.rowCountTextView.setText(matieres.getDiscipline());


        //holder.textView.setText(donnes.get(position));

    }


    @Override
    public int getItemCount() {
        return donnes.size();
    }

    /**
     * fonction de filtrage lors de recherche d'une matiere dans la barre de recherche
     * la recherche se fait par nom de matiere ou par discipline
     * @return les resultats apres filtrages
     */
    @Override
    public Filter getFilter(){
        return filter;
    }

    Filter filter=new Filter() {

        @Override
        protected FilterResults performFiltering(CharSequence charSaquence) {


            List<UE> filteredList = new ArrayList<>();

            if (charSaquence.toString().isEmpty())
            {
                filteredList.addAll(toutdonnes);
            }
            else
            {
                for (UE matiere : toutdonnes) {
                    if (matiere.getNomUE().toLowerCase().contains(charSaquence.toString().toLowerCase()) | (matiere.getDiscipline()!=null && matiere.getDiscipline().toLowerCase().contains(charSaquence.toString().toLowerCase()))) {
                        filteredList.add(matiere);
                    }
                }

            }
            FilterResults filterResults=new FilterResults();
            filterResults.values=filteredList;
            return filterResults;
        }


        @Override
        protected void publishResults(CharSequence constraint, FilterResults filterResults) {
            donnes.clear();
            donnes.addAll((Collection<? extends UE>)filterResults.values);
            notifyDataSetChanged();
        }
    };




    public interface SelectedMatiere{

        void selectedMatiere(UE userModel);

    }

    /**
     * fonction d'affichage des infos de la matieres une fois on clique dessus
     */
    class ViewHolder extends RecyclerView.ViewHolder /*implements View.OnClickListener*/ {


        TextView name;
        ImageView imageView;
        TextView rowCountTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            name=itemView.findViewById(R.id.textView);
            imageView = itemView.findViewById(R.id.imageView);
            rowCountTextView = itemView.findViewById(R.id.rowCountTextView);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectedMatiere.selectedMatiere(donnes.get(getAdapterPosition()));
                }
            });



            //itemView.setOnClickListener(this);
        }
//        @Override
//        public void onClick(View view){
//            //Toast.makeText(view.getContext(),donnes.get(getAdapterPosition()),Toast.LENGTH_SHORT).show();
//            /////////////////////////////////////
//            Toast.makeText(view.getContext(), (CharSequence) donnes.get(getAdapterPosition()),Toast.LENGTH_SHORT).show();
//            ///////////////////////////////////////
//
//        }
    }
}
