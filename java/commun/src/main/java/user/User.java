package user;

import matière.UE;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 */
public class User implements ToJSON {
    private String Nom;
    private String prenom;
    private int id = 0;


    private ArrayList<UE> liste_choix;

    public ArrayList<UE> getListe_choix() {
        return liste_choix;
    }

    public void setListe_choix(ArrayList<UE> liste_choix) {
        this.liste_choix = liste_choix;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }


    public int getId() {
        return id;
    }


    public User(){
//        Nom = "Baroudi";
//        prenom ="Ibrahim";
//        address_ip = "none";
//        liste_choix = new ArrayList<UE>();
        this("defaultName", "defaultSurname", new ArrayList<UE>());
    }


    public User(String nom, String prenom, ArrayList<UE> liste_choix) {
        Nom = nom;
        this.prenom = prenom;
        this.liste_choix = liste_choix;
        this.id+=1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return getId() == user.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "User{" +
                "Nom='" + Nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", Id= " + id +'\''+
                '}';
    }

    @Override
    public JSONObject toJSON() {
        JSONObject user = new JSONObject();
        try {
            user.put("nom", getNom());
            user.put("prenom", getPrenom());
            user.put("Id", getId());
            //user.put("liste_choix", getListe_choix());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return user;
    }

}
