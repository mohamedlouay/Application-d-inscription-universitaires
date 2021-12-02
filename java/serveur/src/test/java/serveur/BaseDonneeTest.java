package serveur;

import init.InitBD;
import matière.UE;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import user.User;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BaseDonneeTest {
    String PATH = "./donneesTest/";
    String PATHUE = PATH + "ue/";
    String PATHUSER = PATH + "user/";
    BaseDonnee baseDonnee;
    User user1, user2;
    UE infoShell;
    UE geo3;
    File fileUE;
    File fileU;

    @BeforeEach
    void setUp() {
        fileUE = new File(PATHUE);
        fileUE.mkdirs();
        fileU = new File(PATHUSER);
        fileU.mkdirs();

        baseDonnee = new BaseDonnee();
        infoShell = new UE("SPUF20", "Informatique S2", "UE INFO S2 : Systeme 1 unix et programmation shell", 2, 6, 111, false, true);
        geo3 = new UE("SPUGDE20", "Geographie S2", "UE GEOGRAPHIE : Decouverte 3", 2, 6, 3);
        user1 = new User("USER1", "TEST1", new ArrayList<>(Arrays.asList(infoShell)));
        user2 = new User("USER2", "TEST2", new ArrayList<>(Arrays.asList(geo3)));
        UtilServeur.writeToJSON(PATHUSER + "u1.json", user1);
        UtilServeur.writeToJSON(PATHUSER + "u2.json", user2);
        UtilServeur.writeToJSON(PATHUE + "s1.json", new ArrayList<>(Arrays.asList(infoShell)));
        UtilServeur.writeToJSON(PATHUE + "s2.json", new ArrayList<>(Arrays.asList(geo3)));

    }

    @AfterEach
    void tearDown() {
        File file = new File(PATHUE);
        for (File f : file.listFiles()) {
            f.delete();
        }
        file.delete();
        file = new File(PATHUSER);
        for (File f : file.listFiles()) {
            f.delete();
        }
        file.delete();
        file = new File(PATH);
        file.delete();

    }

    @Test
    void loadingUE() {
        /**
         * On s'attend à ce que la méthode loadingUE nous renvoie une arraylist avec toutes les ues dans les fichiers .json
         * Les fichiers sont écrit dans le setup, on écrit l'ue infoshell et geo3 (resp. dans s1.json et s2.json)
         */
        ArrayList<UE> listeattendu = new ArrayList<>(Arrays.asList(geo3, infoShell));
        ArrayList<UE> list = baseDonnee.loadingUE(fileUE);

        assertEquals(listeattendu, list);
    }

    @Test
    void loadingUsers() {

        ArrayList<User> listeattendu = new ArrayList<>(Arrays.asList(user2, user1));
        ArrayList<User> list = baseDonnee.loadingUsers(fileU);

        assertEquals(listeattendu, list);

    }

}