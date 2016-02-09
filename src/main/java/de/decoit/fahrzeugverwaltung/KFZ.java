package de.decoit.fahrzeugverwaltung;

import java.io.Serializable;

public class KFZ implements Serializable {

    public String besitzer;
    public String marke;
    public String typ;
    public double verbrauch;
    public int leistung;
    public int kmstand;
    public Treibstoff treibstoff;
    public Klasse klasse;

    public KFZ(String besitzer, String marke, String typ, double verbrauch, int leistung, int kmstand, Treibstoff treibstoff, Klasse klasse) {

        this.besitzer = besitzer;
        this.marke = marke;
        this.typ = typ;
        this.verbrauch = verbrauch;
        this.leistung = leistung;
        this.kmstand = kmstand;
        this.treibstoff = treibstoff;
        this.klasse = klasse;
    }

    public double kosten(double strecke, double preis) {
        double kraftstoffverbrauch = getVerbrauch();
        double liter = kraftstoffverbrauch / 100 * strecke;
        double kosten = liter * preis;

        return kosten;
    }

    public String getBesitzer() {
        return besitzer;
    }

    public void setBesitzer(String besitzer) {
        this.besitzer = besitzer;
    }

    public String getMarke() {
        return marke;
    }

    public void setMarke(String marke) {
        this.marke = marke;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public double getVerbrauch() {
        return verbrauch;
    }

    public void setVerbrauch(double verbrauch) {
        this.verbrauch = verbrauch;
    }

    public int getLeistung() {
        return leistung;
    }

    public void setLeistung(int leistung) {
        this.leistung = leistung;
    }

    public int getKmstand() {
        return kmstand;
    }

    public void setKmstand(int kmstand) {
        this.kmstand = kmstand;
    }

    public Treibstoff getTreibstoff() {
        return treibstoff;
    }

    public void setTreibstoff(Treibstoff treibstoff) {
        this.treibstoff = treibstoff;
    }

    public Klasse getKlasse() {
        return klasse;
    }

    public void setKlasse(Klasse klasse) {
        this.klasse = klasse;
    }

}
