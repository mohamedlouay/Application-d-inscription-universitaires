package serveur;

import com.corundumstudio.socketio.*;
import matière.UE;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import user.User;


import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ServeurTest {

    Serveur serveur;
    Configuration configuration = new Configuration();
    User user1, user2;
    String code_ue1, code_ue2;
    UE infoShell;
    UE geo3;


    @BeforeEach
    void setUp() {
        configuration.setHostname("localhost");
        configuration.setPort(4444);
        SocketIOServer socketIOServer = new SocketIOServer(configuration);
        serveur = new Serveur(socketIOServer);
        user1 = new User("USER1", "TEST1", new ArrayList<>());
        user2 = new User("USER2", "TEST2", new ArrayList<>());
        code_ue1 = "SPUGDE20";
        code_ue2 = "SPUF20";
        infoShell = new UE("SPUF20","Informatique S2","UE INFO S2 : Systeme 1 unix et programmation shell",2,6,111,false,true);
        geo3 = new UE("SPUGDE20","Geographie S2","UE GEOGRAPHIE : Decouverte 3",2,6,3);
        serveur.setListUsers(new ArrayList<>(Arrays.asList(user1,user2)));
        serveur.setDict_UE(UtilServeur.initDictUE(new ArrayList<>(Arrays.asList(geo3,infoShell))));

    }

    @Test
    void save_code() {
        /**
         * 1er cas : On vérifie que la matière avec le code passé en paramètre a été ajouté dans
         * la liste des choix de l'user, assé en paramètre dans la liste des Users du serveur
         */
        ArrayList<UE> listeChoixAttendu = new ArrayList<>(Arrays.asList(geo3));
        System.out.println(serveur.getDict_UE());
        serveur.save_code(user1, code_ue1);
        System.out.println(serveur.getListUsers());
        assertEquals(listeChoixAttendu, serveur.getListUsers().get(0).getListe_choix());

        listeChoixAttendu.add(infoShell);
        serveur.save_code(user1, code_ue2);
        assertEquals(listeChoixAttendu, serveur.getListUsers().get(0).getListe_choix());


    }

    @Test
    void ajouteUser() {

        User user3 = new User();
        ArrayList<User> listeattendu = new ArrayList<>();
        listeattendu.addAll(serveur.getListUsers());
        listeattendu.add(user3);
        serveur.ajouteUser(user3);
        assertEquals(listeattendu, serveur.getListUsers());

        serveur.ajouteUser(user3);
        assertEquals(listeattendu, serveur.getListUsers());


    }

    @Test
    void initParcoursUser() {

        ArrayList<UE> listeattendu = new ArrayList<>();
        /** On verifie que la liste des choix du client est bien cleared
         * Pour une liste non-vide et une liste deja vide
         **/
        serveur.getListUsers().get(0).setListe_choix(new ArrayList<>(Arrays.asList(infoShell,geo3)));
        serveur.getListUsers().get(1).setListe_choix(new ArrayList<>());

        serveur.initParcoursUser(user1);
        serveur.initParcoursUser(user2);

        assertEquals(listeattendu, user1.getListe_choix());
        assertEquals(listeattendu, user2.getListe_choix());

    }
}