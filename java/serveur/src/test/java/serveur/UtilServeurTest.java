package serveur;

import matière.UE;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import user.User;


import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class UtilServeurTest {

    String PATH = "./donneesTest/";
    UE geo1;
    UE geo2;
    UE base;

    ArrayList<UE> choix;
    ArrayList<UE> choix2;
    ArrayList<UE> choixVide;
    ArrayList<UE> listUE;

    User user1;
    User user2;
    User user3 ;
    ArrayList<User> listUsers;


    @BeforeEach
    void setUp() {
        geo1 = new UE("SPUGDE10","Geographie S1","UE GEOGRAPHIE : Decouverte 1",1,6,4);
        geo2 = new UE("SPUGDC10","Geographie S1","UE GEOGRAPHIE : Decouverte 2",1,6,3);
        base = new UE("SPUF10","Informatique S1","UE INFO S1 : Bases de l'informatique",1,6,164);

        choix = new ArrayList<>(Arrays.asList(geo1, base));
        choix2 = new ArrayList<>(Arrays.asList(base));
        choixVide = new ArrayList<>();
        listUE = new ArrayList<>(Arrays.asList(geo1, geo2, base));

        user1 = new User("Name1", "prenom1", choix);
        user2 = new User("Name2", "prenom2", choix2);
        user3 = new User("Name3", "prenom3", choixVide);

        listUsers = new ArrayList<>(Arrays.asList(user1,user2,user3));

        File file = new File(PATH);
        file.mkdir();
    }

    @AfterEach
    void tearsDown(){
        File file = new File(PATH);
        file.delete();
    }


    @Test
    void initDictUE() {

        HashMap<String, UE> dico_UE_attendu;
        HashMap<String, UE> dico_UE;

        /**
         * 1er cas : On verifie qu'on a bien le dictionnaire des UE avec :
         * key = code de l'UE et valeur = UE (l'objet)
         * pour les UEs du setUp et la liste listeUE du setUp
         */
        dico_UE_attendu = new HashMap<>();
        dico_UE_attendu.put(geo1.getCode(), geo1);
        dico_UE_attendu.put(geo2.getCode(), geo2);
        dico_UE_attendu.put(base.getCode(), base);


        dico_UE = UtilServeur.initDictUE(this.listUE);
        assertEquals(dico_UE_attendu,dico_UE);

        /**
         * 2ème cas : On verifie qu'on a bien le dictionnaire des UE avec :
         * key = code de l'UE et valeur = UE (l'objet)
         * pour les UEs du setUp et la liste listeUE du setUp
         * AVEC plusieurs fois la même UE dans listeUE
         */
        dico_UE_attendu = new HashMap<>();
        dico_UE_attendu.put(geo1.getCode(), geo1);
        dico_UE_attendu.put(geo2.getCode(), geo2);
        dico_UE_attendu.put(base.getCode(), base);

        this.listUE.addAll(listUE);
        dico_UE = UtilServeur.initDictUE(listUE);
        assertEquals(dico_UE_attendu,dico_UE);

        /**
         * 3ème cas : On verifie qu'on a bien le dictionnaire vide avec :
         * lorsque la liste est null
         */
        dico_UE = UtilServeur.initDictUE(null);
        dico_UE_attendu = new HashMap<>();
        assertEquals(dico_UE_attendu,dico_UE);
    }

    @Test
    void initListeUE() {
        ArrayList<UE> listeUE_attendu;
        ArrayList<UE> listdesUE;

        /**
         * 1er cas : On vérifie que dans la liste attendu on a bien
         * toutes nos UEs
         * ici : geo1, geo2
         */
        ArrayList<UE> list1 = new ArrayList<>(Arrays.asList(geo1,base));
        ArrayList<UE> list2 = new ArrayList<>(Arrays.asList(geo2));
        ArrayList<UE> list3 = new ArrayList<>(Arrays.asList(geo1));
        listeUE_attendu = new ArrayList<>(Arrays.asList(geo1,base,geo2));
        listdesUE = UtilServeur.initListeUE(list1,list2);

        assertEquals(listeUE_attendu, listdesUE);

        /**
         * 2eme cas : On vérifie que la liste attendu les objets null sont ignorés
         *
         */
        listeUE_attendu = UtilServeur.initListeUE(list2, null, list3);
        listdesUE = new ArrayList<>(Arrays.asList(geo2,geo1));

        assertEquals(listeUE_attendu, listdesUE);

        /**
         * 3ème cas : On vérifie que la liste attendu est vide lorsque qu'il n'y pas d'UE passé en paramètre
         */
        listeUE_attendu = UtilServeur.initListeUE();
        listdesUE = new ArrayList<>();


        assertEquals(listeUE_attendu, listdesUE);

    }

    @Test
    void userExist() {
        /**
         * 1er cas : On donne 3 user user1 2 et 3 (du setUp)
         * on donne une liste listUsers (du setUp)
         * ici : user1 appartient à la liste.
         * On vérifie que la méthode renvoie vrai
         */
        boolean exists1 = UtilServeur.userExist(listUsers, user1);
        boolean exists2 = UtilServeur.userExist(listUsers, user2);
        boolean exists3 = UtilServeur.userExist(listUsers, user3);
        assertTrue(exists1);
        assertTrue(exists2);
        assertTrue(exists3);

        /**
         * 2eme cas : On donne 1 user null
         * on donne la liste listUsers (du setUp)
         * ici : user est null
         * On vérifie que la méthode renvoie false
         */
        exists1 = UtilServeur.userExist(listUsers, null);
        assertFalse(exists1);

        /**
         * 3eme cas : On donne 1 user user1
         * on donne une liste d'users null (du setUp)
         * On vérifie que la méthode renvoie false
         */
        exists1 = UtilServeur.userExist(null, user1);
        assertFalse(exists1);
    }

    @Test
    void writeToJSON() {
        File fileUsers = new File(PATH+"utilisateurs.json");
        File fileUE = new File(PATH+"UEs.json");


        assertFalse(fileUsers.exists());
        assertFalse(fileUE.exists());

        /**
         * 1èr cas les fichiers n'existe pas :
         * On vérifie que les fichier existent .json
         * Et qu'on a bien écrit les données
         */
        fileUsers = UtilServeur.writeToJSON(PATH+"utilisateurs.json", listUsers);
        fileUE = UtilServeur.writeToJSON(PATH+"UEs.json", listUE);

        assertTrue(fileUsers.exists());
        assertTrue(fileUE.exists());
        assertTrue(fileUsers.length()>0);
        assertTrue(fileUE.length()>0);

        /**
         * On supprime les fichiers
         */
        fileUE.delete();
        fileUsers.delete();


    }

    @Test
    void JSONFileToListUE() {
        File fileUE = UtilServeur.writeToJSON(PATH+"UEs.json", listUE);
        ArrayList<UE> list_attendu;
        ArrayList<UE> listdesUE;

        /**
         * On vérifie que la liste d'UEs écrit dans UEs.json
         * correspond à la liste listUE après sa conversion en objet java
         */
        listdesUE = UtilServeur.JSONFileToListUE(fileUE);
        list_attendu = listUE;
        assertEquals(list_attendu,listdesUE);

        /**
         * On vérifie que la liste est vide si le fichier n'existe pas ou est vide
         */
        fileUE.delete();
        listdesUE = UtilServeur.JSONFileToListUE(fileUE);
        list_attendu = new ArrayList<>();
        assertEquals(list_attendu,listdesUE);
    }

    @Test
    void JSONFileToListUsers() {
        File fileUser = UtilServeur.writeToJSON(PATH+"utilisateurs.json", user1);
        User userJson;

        /**
         * On vérifie que l'user user1 écrit dans utilisateurs.json
         * correspond à l'user user1 après sa conversion en objet java
         */
        userJson = UtilServeur.JSONFileToUsers(fileUser);
        assertEquals(user1,userJson);

        /**
         * On vérifie que la liste est vide si le fichier n'existe pas ou est vide
         * correspond à la liste listUE après sa conversion en objet java
         */
        fileUser.delete();
        userJson = UtilServeur.JSONFileToUsers(fileUser);
        assertEquals(null,userJson);
    }

    @Test
    void getIndexUser() {
        int index;

        /**
         * 1er cas : l'ip (passée en paramètre) est bien dans la liste d'user
         * user1 est d'index 0, user2 -> 1 et user3 -> 2
         */
        index = UtilServeur.getIndexUser(user1,listUsers);
        assertEquals(0,index);
        index = UtilServeur.getIndexUser(user2,listUsers);
        assertEquals(1,index);
        index = UtilServeur.getIndexUser(user3,listUsers);
        assertEquals(2,index);

        /**
         * 2ème cas : l'user n'est pas dans la liste des users
         * la fonction renvoie -1 si l'ip n'est pas dans la liste (ou si l'ip ou la liste passée en paramètre est null)
         */
        User user4 = new User("Name4", "prenom4", choixVide);
        index = UtilServeur.getIndexUser(user4, listUsers);
        assertEquals(-1,index);

        /**
         * 3ème cas : l'ip est null, la list est null
         */
        index = UtilServeur.getIndexUser(user1, null);
        assertEquals(-1,index);
        index = UtilServeur.getIndexUser(null , listUsers);
        assertEquals(-1,index);
    }
}