package serveur;


import matière.UE;
import user.User;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;

/**
 * Classe qui s'occupe du chargements et de l'écritures des données
 */
public class BaseDonnee {

    private static final String PATH_FILES_SEMESTRE = "./serveur/donnees/semestre/";
    private static final String PATH_FILES_USER = "./serveur/donnees/users/";

    File dirSemestre = new File(PATH_FILES_SEMESTRE);
    File dirUsers = new File(PATH_FILES_USER);

    public static String getPathFilesSemestre() {
        return PATH_FILES_SEMESTRE;
    }

    public static String getPathFilesUser() {
        return PATH_FILES_USER;
    }

    /**
     * Charge les UE des fichiers semestreN.json, les met dans une ArrayList
     * @return ArrayList des UEs
     */
    public ArrayList<UE> loadingUE(){
        ArrayList<UE> listUE = new ArrayList<>();
        if (dirSemestre.exists()){
            System.out.println("Chargement des UE de la base de donnée...");
            for (File f : dirSemestre.listFiles()) {
                listUE.addAll(UtilServeur.JSONFileToListUE(f));
            }
            return listUE;
        }
        else{
            System.out.println("Erreur lors du chargement :" +
                    " Le dossier "+PATH_FILES_SEMESTRE+" n'existe pas !");
        }
        return null;
    }

    /**
     * Effectue le chargement avec comme dossier source des UEs dir
     * @param dir Répartoire source pour le chargement des UE
     * @return ArrayList des UEs
     */
    public ArrayList<UE> loadingUE(File dir){
        ArrayList<UE> listUE = new ArrayList<>();
        if (dir.exists()){
            System.out.println("Chargement des UE de la base de donnée... ");
            for (File f : dir.listFiles()) {
                listUE.addAll(UtilServeur.JSONFileToListUE(f));
            }
            return listUE;
        }
        else{
            System.out.println("Erreur lors du chargement :" +
                    " Le dossier "+PATH_FILES_SEMESTRE+" n'existe pas !");
        }
        return null;
    }

    /**
     * Effectue le chargement avec comme dossier source des UEs dir
     * @param utilisateurs Répartoire source pour le chargement des UE
     * @return ArrayList des Users
     */
    public ArrayList<User> loadingUsers(File utilisateurs) {
        ArrayList<User> listUsers = new ArrayList<>();
        if (utilisateurs.exists()){
            System.out.println("Chargement des Users de la base de donnée...");
            for (File f : utilisateurs.listFiles()) {
                listUsers.add(UtilServeur.JSONFileToUsers(f));
            }
            return listUsers;
        }
        else{
            System.out.println("Erreur lors du chargement : " +
                    " Le dossier "+PATH_FILES_USER+" n'existe pas !");
        }
        return null;
    }

    /**
     * Charge les infos des clients, précédemment connectés, des fichiers [NOM CLIENT].json, les met dans une ArrayList
     * @return ArrayList des clients (User)
     */
    public ArrayList<User> loadingUsers(){
        ArrayList<User> listUsers = new ArrayList<>();
        if (dirUsers.exists()){
            System.out.println("Chargement des Users de la base de donnée...");
            for (File f : dirUsers.listFiles()) {
                listUsers.add(UtilServeur.JSONFileToUsers(f));
            }
            return listUsers;
        }
        else{
            System.out.println("Erreur lors du chargement : " +
                    " Le dossier "+PATH_FILES_USER+" n'existe pas !");
        }
        return null;
    }


    public void saveUser(User user) {
        UtilServeur.writeToJSON(PATH_FILES_USER+user.getNom()+".json", user);
    }
}
