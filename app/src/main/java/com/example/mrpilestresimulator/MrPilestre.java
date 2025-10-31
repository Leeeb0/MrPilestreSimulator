package com.example.mrpilestresimulator;

import android.os.Parcel;
import android.os.Parcelable;

public class MrPilestre implements Parcelable {

    // Variables suivies
    private int energie = 75;
    private int sante = 50;
    private int bonheur = 50;
    private int argent = 75;
    private int discipline = 25;
    private int stress = 25;
    private int competences = 50;
    private int retard = 0;

    // Constructeur par défaut
    public MrPilestre() {}

    // Constructeur à partir d’un Parcel
    protected MrPilestre(Parcel in) {
        energie = in.readInt();
        sante = in.readInt();
        bonheur = in.readInt();
        argent = in.readInt();
        discipline = in.readInt();
        stress = in.readInt();
        competences = in.readInt();
        retard = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(energie);
        dest.writeInt(sante);
        dest.writeInt(bonheur);
        dest.writeInt(argent);
        dest.writeInt(discipline);
        dest.writeInt(stress);
        dest.writeInt(competences);
        dest.writeInt(retard);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MrPilestre> CREATOR = new Creator<MrPilestre>() {
        @Override
        public MrPilestre createFromParcel(Parcel in) {
            return new MrPilestre(in);
        }

        @Override
        public MrPilestre[] newArray(int size) {
            return new MrPilestre[size];
        }
    };

    // Getters
    public int getEnergie() { return energie; }
    public int getSante() { return sante; }
    public int getBonheur() { return bonheur; }
    public int getArgent() { return argent; }
    public int getDiscipline() { return discipline; }
    public int getStress() { return stress; }
    public int getCompetences() { return competences; }
    public int getRetard() { return retard; }

    // Setters
    public void setEnergie(int valeur) { energie = valeur; }
    public void setSante(int valeur) { sante = valeur; }
    public void setBonheur(int valeur) { bonheur = valeur; }
    public void setArgent(int valeur) { argent = valeur; }
    public void setDiscipline(int valeur) { discipline = valeur; }
    public void setStress(int valeur) { stress = valeur; }
    public void setCompetences(int valeur) { competences = valeur; }
    public void setRetard(int valeur) { retard = valeur; }
}