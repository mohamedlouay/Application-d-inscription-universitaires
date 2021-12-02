package matière;

import java.io.Serializable;
import java.util.Objects;

public class UE implements Serializable {
    private String code;
    private String discipline;

    public String getSousDiscipline() {
        return sousDiscipline;
    }

    private String sousDiscipline;
    private int semestre;
    private int ects;
    private String nomUE;
    private int nbPlaces;
    private Boolean preChecked = false;
    private Boolean sansSousMatieres = false;

    public Boolean getTeteGroupe() {
        return teteGroupe;
    }

    private Boolean teteGroupe = false;

    public UE(){

    }

    public UE(String code, String discipline, String nomUE, int semestre, int ECTS, int nbPlaces) {
        this.code = code;
        this.discipline = discipline;
        this.semestre = semestre;
        this.ects = ECTS;
        this.nomUE = nomUE;
        this.nbPlaces = nbPlaces;
    }

    public UE(String code, String discipline, String nomUE, int semestre, int ECTS, int nbPlaces, boolean preChecked, boolean sansSousMatieres) {
        this.code = code;
        this.discipline = discipline;
        this.semestre = semestre;
        this.ects = ECTS;
        this.nomUE = nomUE;
        this.nbPlaces = nbPlaces;
        this.preChecked = preChecked;
        this.sansSousMatieres = sansSousMatieres;
    }

    public UE(String code, String discipline, String sousDiscipline, String nomUE, int semestre, int ECTS, int nbPlaces) {
        this.code = code;
        this.discipline = discipline;
        this.sousDiscipline = sousDiscipline;
        this.semestre = semestre;
        this.ects = ECTS;
        this.nomUE = nomUE;
        this.nbPlaces = nbPlaces;
    }

    public UE(String code, String discipline, String nomUE, int semestre, int ECTS, int nbPlaces, boolean preChecked, boolean sansSousMatieres, boolean teteGroupe) {
        this.code = code;
        this.discipline = discipline;
        this.semestre = semestre;
        this.ects = ECTS;
        this.nomUE = nomUE;
        this.nbPlaces = nbPlaces;
        this.preChecked = preChecked;
        this.sansSousMatieres = sansSousMatieres;
        this.teteGroupe = teteGroupe;

    }

    public String getCode() {
        return code;
    }

    public String getDiscipline() {
        return discipline;
    }

    public int getSemestre() {
        return semestre;
    }

    public int getECTS() {
        return ects;
    }

    public String getNomUE() {
        return nomUE;
    }

    public int getNbPlaces() {
        return nbPlaces;
    }

    public Boolean getPreChecked() {
        return preChecked;
    }

    public Boolean getSansSousMatieres() {
        return sansSousMatieres;
    }

    @Override
    public String toString() {
        return "UE{" +
                "discipline='" + discipline + '\'' +
                ", semestre=" + semestre +
                ", ects=" + ects +
                ", nomUE='" + nomUE + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UE ue = (UE) o;
        return getCode().equals(ue.getCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCode());
    }
}
