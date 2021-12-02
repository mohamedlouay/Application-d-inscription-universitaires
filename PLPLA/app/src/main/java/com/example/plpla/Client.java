package com.example.plpla;

import android.app.Application;

import java.util.ArrayList;
import java.util.Arrays;

import matière.UE;
import user.User;


public class Client extends Application {

    private Connexion uniqueConnexion;
    private User user;
    public ArrayList<String> getSelectionUE() {
        return selectionUE;
    }
    private ArrayList<String> selectionUE = new ArrayList<>();

    public ArrayList<String> getSelectionCode() {
        return selectionCode;
    }

    private ArrayList<String> selectionCode = new ArrayList<>();

    public ArrayList<String> getNomFichier() {
        return nomFichier;
    }

    private ArrayList<String> nomFichier = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        /*on initialise l'addresse du serveur dans le MainActivity avec le setServerAddress*/
        uniqueConnexion = new Connexion();
        user = new User();
    }



    public Connexion getUniqueConnexion() {
        return uniqueConnexion;
    }

    public User getUser() {
        return user;
    }




    //UE SEMESTRE 1

    UE blocFondement = new UE(null,"BLOC MATH S1 : Fondements 1",null,1,30,0);
    UE blocMethode = new UE(null,"BLOC MATH S1 : Methodes. approche continue",null,1,30,0);

    UE geo1 = new UE("SPUGDE10","Geographie S1","UE GEOGRAPHIE : Decouverte 1",1,6,4);
    UE geo2 = new UE("SPUGDC10","Geographie S1","UE GEOGRAPHIE : Decouverte 2",1,6,3);
    UE disciplinaire1 = new UE("SPUGDI10","Geographie S1","UE GEOGRAPHIE : Disciplinaire 1",1,6,3);
    UE base = new UE("SPUF10","Informatique S1","UE INFO S1 : Bases de l'informatique",1,6,164);
    UE web = new UE("SPUF11","Informatique S1","UE INFO S1 : Introduction a l'informatique par le web",1,6,134);
    UE complement1 = new UE("SPUM13","Math Fondements 1 S1","UE MATHS S1 : Complements 1",1,6,100);
    UE methodeBlocF = new UE("SPUM12","Math Fondements 1 S1","UE MATHS S1 : Methodes - approche continue",1,6,280);
    UE genetique = new UE("SPUV101","Choix SV S1","UE SV-S1 : Genetique. evolution. origine vie & biodiversite",1,6,43);
    UE organique = new UE("SPUV100","Choix SV S1","UE SV-S1 : Org et mecan. moleculaires - cellules eucaryotes",1,6,26);
    UE chimie = new UE("SPUC10","Chimie S1","UE CHIMIE S1 : Structure microscopique de la matiere",1,6,167, false, true);
    UE electronique = new UE("SPUE10","Electronique S1","UE ELECTRONIQUE S1 : Electronique numerique - Bases",1,6,154, false, true);
    UE culture1 = new UE("SPEA11","MIASHS S1","ECUE MIASHS S1 : Culture economie 1",1,3,49);
    UE analyse = new UE("SPEA12","MIASHS S1","ECUE MIASHS S1 : Introduction a l'analyse economique",1,3,61);
    UE macro1 = new UE("SPEA10","MIASHS S1","ECUE MIASHS S1 : Macroeconomie 1",1,3,111, true, false);
    UE physique = new UE("SPUP10","Physique S1","UE PHYSIQUE S1 : Mecanique 1",1,6,203, false, true);
    UE terre = new UE("SPUT10","Sciences de la terre S1","UE TERRE S1 : Decouverte des sciences de la terre",1,6,56, false, true);
    UE fondement1 = new UE("SPEA10","Mathematique S1","UE MATHS S1 : Fondements 1",1,6,322, true, true);
    UE methodeBlocM = new UE("SPUM12","Mathematique S1","UE MATHS S1 : Methodes - approche continue",1,6,280, true, true);


    //LA LISTE DES 2 BLOCS AVEC LEUR MATIERE PRE-CHECKED
    ArrayList<ArrayList<UE>> blocEtSaMatiere = new ArrayList<>(Arrays.asList(new ArrayList<UE>(Arrays.asList(blocFondement,fondement1)),
            new ArrayList<UE>(Arrays.asList(blocMethode,methodeBlocM))));
    //LA LISTE DES UE DANS LE BLOC FONDEMENT
    ArrayList<UE> listeUEBlocFondement = new ArrayList<>(Arrays.asList(geo1,geo2,disciplinaire1,base,web,complement1,methodeBlocF,genetique,organique,chimie,electronique,
            culture1,analyse,macro1,physique,terre,fondement1));
    //LA LISTE DES UE DANS LE BLOC METHODE
    ArrayList<UE> listeUEBlocMethode = new ArrayList<>(Arrays.asList(geo1,geo2,disciplinaire1,base,web,methodeBlocM,genetique,organique,chimie,electronique,
            culture1,analyse,macro1,physique,terre));

    public ArrayList<ArrayList<UE>> getBlocEtSaMatiere() {
        return blocEtSaMatiere;
    }

    public ArrayList<UE> getListeUEBlocFondement() {
        return listeUEBlocFondement;
    }

    public ArrayList<UE> getListeUEBlocMethode() {
        return listeUEBlocMethode;
    }



    //UE SEMESTRE 2

    UE blocFondementS2 = new UE(null,"BLOC MATH S2 : Fondements 2",null,2,30,0);
    UE blocMethodeS2 = new UE(null,"BLOC MATH S2 : Methodes. approche discrete",null,2,30,0);

    UE geo3 = new UE("SPUGDE20","Geographie S2","UE GEOGRAPHIE : Decouverte 3",2,6,3);
    UE geo4 = new UE("SPUGDC20","GGeographie S2","UE GEOGRAPHIE : Decouverte 4",2,6,3);
    UE disciplinaire2 = new UE("SPUGDI20","Geographie S2","UE GEOGRAPHIE : Disciplinaire 2",2,6,1);
    UE diversite = new UE("SPUV201","SV S2","UE SV S2 : Diversite du vivant",2,6,13);
    UE physiologie = new UE("SPUV200","SV S2","UE SV S2 : Physiologie - neurologie - enzymologie",2,6,27);
    UE chimieS2Reaction = new UE("SPUC20","Chimie S2","UE CHIMIE S2 : Reactions et reactivites chimiques",2,6,84, false, true);
    UE chimieS2Thermo = new UE("SPEC22","UE CHIMIE S2 : Thermodynamique chimique / Options","ECUE CHIMIE Thermodynamique Chimie",2,3,84, true ,false);
    UE chimieS2Pollution = new UE("SPEC23","UE CHIMIE S2 : Thermodynamique chimique / Options","ECUE CHIMIE S2 : Chimie et pollution",2,3,23);
    UE chimieS2Complement = new UE("SPEC25","UE CHIMIE S2 : Thermodynamique chimique / Options","ECUE CHIMIE S2 : Complement de thermodynamique physique",2,3,16);
    UE chimieS2TChimique = new UE("SPEC24","UE CHIMIE S2 : Thermodynamique chimique / Options","ECUE CHIMIE S2 : Sens chimique",2,3,35);
    UE electroniqueSansFil = new UE("SPUE21","Electronique S2","UE ELECTRONIQUE S2 : Communication sans fil",2,6,39, false,true);
    UE electroniqueAnalogique = new UE("SPUE20","Electronique S2","UE ELECTRONIQUE S2 : Electronique analogique",2,6,44,false,true);
    UE infoImperative = new UE("SPUF21","Informatique S2","UE INFO S2 : Programmation imperative",2,6,226,false,true);
    UE infoShell = new UE("SPUF20","Informatique S2","UE INFO S2 : Systeme 1 unix et programmation shell",2,6,111,false,true);
    UE mathComplement2 = new UE("SPUM23","Mathematique S2","UE MATH S2 : Complements 2",2,6,83,false,true);
    UE mathMethodeDiscrete = new UE("SPUM22","Mathematique S2","UE MATH S2 : Methodes Maths-Approche discrete",2,6,242,false,true);
    UE mathFondement2 = new UE("SPUM21","Mathematique S2","UE MATH S2 : Fondements 2",2,6,261,false,true);
    UE entreprise1 = new UE("SPEA21","Choix ECUE MIASHS S2","ECUE MIASHS S2 : Economie d'entreprise 1",2,3,55);
    UE information = new UE("SPEA22","Choix ECUE MIASHS S2","ECUE MIASHS S2 : Economie de l'information",2,3,28);
    UE macro1S2 = new UE("SPEA20","Choix ECUE MIASHS S2","ECUE MIASHS S2 : Macroeconomie 1",2,3,100, true, false);

    UE physiqueS2Mecanique = new UE("SPUP21","Physique S2","UE PHYSIQUE S2 : Mecanique - complements",2,6,86,false,true);
    UE physiqueS2Optique = new UE("SPUP20","Physique S2","UE PHYSIQUE S2 : Optique",2,6,117,false,true);
    UE terreS2Atmosphere = new UE("SPUT22","Sciences de la terre S2","UE TERRE S2 : Atmosphere. ocean. climats",2,6,35,false,true);
    UE terreS2Structure = new UE("SPUT20","Sciences de la terre S2","UE TERRE S2 : Structure et dynamique de la terre",2,6,31,false,true);

    UE fondementS2BlocF = new UE("SPUM21","Matematique S2","UE MATH S2 : Fondements 2",2,6,261, true, true);
    UE methodesS2BlocM = new UE("SPUM22","Matematique S2","UE MATH S2 : Methodes Maths-Approche discrete",2,6,242,true,true);


    //LA LISTE DES 2 BLOCS AVEC LEUR MATIERE PRE-CHECKED
    ArrayList<ArrayList<UE>> blocEtSaMatiereS2 = new ArrayList<>(Arrays.asList(new ArrayList<UE>(Arrays.asList(blocFondementS2,fondementS2BlocF)),
            new ArrayList<UE>(Arrays.asList(blocMethodeS2,methodesS2BlocM))));
    //LA LISTE DES UE DANS LE BLOC FONDEMENT
    ArrayList<UE> listeUEBlocFondementS2 = new ArrayList<>(Arrays.asList(geo3, geo4, disciplinaire2, diversite, physiologie, chimieS2Reaction, chimieS2Thermo,
            chimieS2Pollution, chimieS2Complement, chimieS2TChimique, electroniqueSansFil, electroniqueAnalogique, infoImperative, infoShell, mathComplement2,
            mathMethodeDiscrete, entreprise1, information, macro1S2, physiqueS2Mecanique, physiqueS2Optique, terreS2Atmosphere, terreS2Structure, fondementS2BlocF));
    //LA LISTE DES UE DANS LE BLOC METHODE
    ArrayList<UE> listeUEBlocMethodeS2 = new ArrayList<>(Arrays.asList(geo3, geo4, disciplinaire2, diversite, physiologie, chimieS2Reaction, chimieS2Thermo,
            chimieS2Pollution, chimieS2Complement, chimieS2TChimique, electroniqueSansFil, electroniqueAnalogique, infoImperative, infoShell, mathFondement2,
            mathMethodeDiscrete, entreprise1, information, macro1S2, physiqueS2Mecanique, physiqueS2Optique, terreS2Atmosphere, terreS2Structure, methodesS2BlocM));

    public ArrayList<ArrayList<UE>> getBlocEtSaMatiereS2() {
        return blocEtSaMatiereS2;
    }

    public ArrayList<UE> getListeUEBlocFondementS2() {
        return listeUEBlocFondementS2;
    }

    public ArrayList<UE> getListeUEBlocMethodeS2() {
        return listeUEBlocMethodeS2;
    }


    //UE SEMESTRE 3

    UE blocSciencesEtTech = new UE(null,"S3 Sciences et technologies",null,3,30,0);

    UE geoApprofondissement = new UE("SPUGDE33","Choix geographie","UE GEO S3 : Approfondissement hors geographie 1",3,6,0);
    UE disciplinaire3 = new UE("SPUGDI30","Choix geographie","UE GEO S3 : Disciplinaire 3",3,6,2);
    UE disciplinaire4 = new UE("SPUGDI31","Choix geographie","UE GEOGRAPHIE : Disciplinaire 4",3,6,2);
    UE disciplinaire5 = new UE("SPUGDI32","Choix geographie","UE GEOGRAPHIE : Disciplinaire 5",3,6,2);

    UE chimieS3Solutions = new UE("SPUC30","Chimie S3","UE CHIMIE S3 : Chimie des solutions",3,6,65,false,true);
    UE chimieS3Medicinale = new UE("SPEC306","UE CHIMIE S3 : Chimie organique","ECUE CHIMIE S3 : Chimie medicinale",3,3,18);
    UE chimieS3Cosmetique = new UE("SPEC305","UE CHIMIE S3 : Chimie organique","ECUE CHIMIE S3 : Cosmetique et parfums",3,3,39);
    UE chimieS3Biomolecules = new UE("SPEC304","UE CHIMIE S3 : Chimie organique","ECUE CHIMIE S3 : Structure des biomolecules",3,3,8);
    UE chimieS3Organique = new UE("SPEC303","UE CHIMIE S3 : Chimie organique","ECUE CHIMIE S3 : Chimie organique fonctionelle 1",3,3,64, true,false);
    UE chimieS3Materiaux = new UE("SPUC32","Chimie S3","UE CHIMIE S3 : Materiaux 1",3,6,68,false,true);

    UE ContinuumEnseign = new UE("HPULE36","Continuum Enseignement","UE Continuum Enseignements fondamentaux 1D",3,6,5, false,true);

    UE electroniqueAutomatique = new UE("SPUE30","Electronique S3","UE ELECTRONIQUE S3 : Automatique - introduction",3,6,29, false,true);
    UE electroniquePhysique = new UE("SPUE32","Electronique S3","UE ELECTRONIQUE S3 : Physique des capteurs",3,6,29, false,true);
    UE electroniqueSysteme = new UE("SPUE31","Electronique S3","UE ELECTRONIQUE S3 : Systeme embarque",3,6,26, false,true);

    UE infoBaseD = new UE("SPUF31","Informatique S3","UE INFO S3 : Bases de donnees",3,6,94, false,true);
    UE infoOFI = new UE("SPUF32","Informatique S3","UE INFO S3 : Outils formels de l'informatique",3,6,71, false,true);
    UE infoC = new UE("SPUF30","Informatique S3","UE INFO S3 : Structures de donnees et programmation C",3,6,94, false,true);

    UE mathAlgebre = new UE("SPUM32","Mathematique S3","UE MATHS S3 : Complements d'algebre",3,6,64, false,true);
    UE mathAnalyse = new UE("SPUM31","Mathematique S3","UE MATHS S3 : Complements d'analyse",3,6,62, false,true);
    UE mathFondement3 = new UE("SPUM30","Mathematique S3","UE MATHS S3 : Fondements 3",3,6,192, false,true);
    UE mathMethodesGeo = new UE("SPUM34","Mathematique S3","UE MATHS S3 : Methodes - approche geometrique",3,6,104, false,true);
    UE mathMethodeInge = new UE("SPUM33","Mathematique S3","UE MATHS S3 : Methodes - mathematiques et ingenierie",3,6,50, false,true);

    UE miashBancaire = new UE("SPEA31","MIASH S3","ECUE MIASHS S3 : Economie bancaire",3,6,66 );
    UE miashAssurance = new UE("SPEA32","MIASH S3","ECUE MIASHS S3 : Economie de l'assurance",3,6,21);
    UE miashMacro2 = new UE("SPEA30","MIASH S3","ECUE MIASHS S3 : Microeconomie 2",3,6,83, true, false);
    UE miashR = new UE("SPUA31","MIASH S3","UE MIASHS S3 : Introduction R",3,6,96, false, true);

    UE physiqueEclectro = new UE("SPUP30","Physique S3","UE PHYSIQUE S3 : Electromagnetisme 1",3,6,48, false, true);
    UE physiqueOutils = new UE("SPUP32","Physique S3","UE PHYSIQUE S3 : Outils et methodes 1",3,6,39, false, true);
    UE physiqueThermo = new UE("SPUP31","Physique S3","UE PHYSIQUE S3 : Thermodynamique 1",3,6,64, false, true);

    UE terreClimats = new UE("SPUT30","Sciences de la terre S3","UE TERRE S3 : Atmosphere - ocean - climats",3,6,21, false, true);
    UE terreGeosciences = new UE("SPUT31","Sciences de la terre S3","UE TERRE S3 : Le temps en geosciences",3,6,17, false, true);
    UE terreMateriaux = new UE("SPUT33","Sciences de la terre S3","UE TERRE S3 : Materiaux terrestres",3,6,17, false, true);
    UE terrePhysique = new UE("SPUT32","Sciences de la terre S3","UE TERRE S3 : Physique de la Terre",3,6,17, false, true);


    //LA LISTE DES 2 BLOCS AVEC LEUR MATIERE PRE-CHECKED
    ArrayList<UE> blocEtSaMatiereS3 = new ArrayList<>(Arrays.asList(blocSciencesEtTech));
    //LA LISTE DES UE DANS LE BLOC
    ArrayList<UE> listeUEBlocS3 = new ArrayList<>(Arrays.asList(geoApprofondissement,disciplinaire3,disciplinaire4,disciplinaire5,
            geo1,geo2,disciplinaire1, chimie,chimieS3Solutions,chimieS3Medicinale,chimieS3Cosmetique,
            electronique,base,web,
            chimieS3Biomolecules,chimieS3Organique,chimieS3Materiaux,ContinuumEnseign,electroniqueAutomatique,electroniquePhysique,electroniqueSysteme,infoBaseD,infoOFI,infoC,mathAlgebre
            ,mathAnalyse,mathFondement3,mathMethodesGeo,mathMethodeInge,miashBancaire,miashAssurance,miashMacro2,miashR,physiqueEclectro,physiqueOutils,physiqueThermo,terreClimats,terreGeosciences
            ,terreMateriaux,terrePhysique
    ));

    public ArrayList<UE> getBlocEtSaMatiereS3() {
        return blocEtSaMatiereS3;
    }

    public ArrayList<UE> getListeUEBlocS3() {
        return listeUEBlocS3;
    }



    UE chimie1 = new UE("SPUC43","Parcours Chimie S4","UE CHIMIE S4 : Bloc de chimie experimentale",4,6,60,true,true,true);
    UE chimie2 = new UE("SPUC42","Parcours Chimie S4","UE CHIMIE S4 : Chimie organique fonctionnelle II",4,6,62, true,true);
    UE chimie3 = new UE("SPUC41","Parcours Chimie S4","UE CHIMIE S4 : Materiaux 2",4,6,62,true,true);
    UE chimie4 = new UE("SPUC40","Parcours Chimie S4","UE CHIMIE S4 : Vision macroscopique des molecules",4,6,62,true,true);

    UE electronique1 = new UE("SPUM44","Parcours Electronique S4", "Choix option", "UE MATH S4 : Methodes - approche aleatoire",4,6,79);
    UE electronique2 = new UE("SPUM41","Parcours Electronique S4","Choix option","UE MATH S4 : Probabilites et introduction aux statistiques",4,6,63);
    UE electronique3 = new UE("SPUE42","Parcours Electronique S4","UE ELECTRONIQUE S4 : Architecture des processeurs",4,6,18,true,true);
    UE electronique4 = new UE("SPUE41","Parcours Electronique S4","UE ELECTRONIQUE S4 : Electronique analogique avancee",4,6,18,true,true);
    UE electronique5 = new UE("SPUE40","Parcours Electronique S4","UE ELECTRONIQUE S4 : Systeme optimise en energie",4,6,23,true,true,true);

    UE info1 = new UE("SPUF43","Parcours Informatique S4","Choix option","UE INFO S4 : Introduction aux systemes intelligents",4,6,18);
    UE info2 = new UE("SPUF44","Parcours Informatique S4","Choix option","UE INFO S4 : Technologies du web",4,6,77);
    UE info3 = new UE("SPUF40","Parcours Informatique S4","UE INFO S4 : Algorithmique 1",4,6,89,true,true);
    UE info4 = new UE("SPUF41","Parcours Informatique S4","UE INFO S4 : Reseaux et telecommunication",4,6,74,true,true);
    UE info5 = new UE("SPUF42","Parcours Informatique S4","UE INFO S4 : Systemes 2 - mecanismes internes syst. d'expl",4,6,72,true,true,true);

    UE majeur1 = new UE("SPUC43","Parcours Majeure Math S4", "Choix option 2","UE CHIMIE S4 : Bloc de chimie experimentale",4,6,60);
    UE majeur2 = new UE("SPUC42","Parcours Majeure Math S4", "Choix option 2","UE CHIMIE S4 : Chimie organique fonctionnelle II",4,6,62);
    UE majeur3 = new UE("SPUC41","Parcours Majeure Math S4", "Choix option 2","UE CHIMIE S4 : Materiaux 2",4,6,62);
    UE majeur4 = new UE("SPUC40","Parcours Majeure Math S4", "Choix option 2","UE CHIMIE S4 : Vision macroscopique des molecules",4,6,62);
    UE majeur5 = new UE("VPUG2D4","Parcours Majeure Math S4", "Choix option 2","UE CLE2D Methodologie et didactique - Geometrie",4,6,9);
    UE majeur = new UE("SPUM40","Parcours Majeure Math S4","UE MATH S4 : Analyse",4,6,67,true,true,true);




    ArrayList<UE> listeUEBlocS4 = new ArrayList<>(Arrays.asList(chimie1, chimie2, chimie3, chimie4, electronique1, electronique2, electronique3, electronique4, electronique5,
            info1, info2, info3, info4, info5, majeur, majeur1, majeur2, majeur3, majeur4, majeur5
    ));
    public ArrayList<UE> getListeUEBlocS4() {
        return listeUEBlocS4;
    }

}
