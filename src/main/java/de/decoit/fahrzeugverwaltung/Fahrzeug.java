package de.decoit.fahrzeugverwaltung;

public class Fahrzeug {

    public String besitzer;
    public String marke;
    public String typ;
    public double verbrauch;
    public int leistung;
    public int kmstand;

    public Fahrzeug(String besitzer, String marke, String typ, double verbrauch, int leistung, int kmstand) {

        this.besitzer = besitzer;
        this.marke = marke;
        this.typ = typ;
        this.verbrauch = verbrauch;
        this.leistung = leistung;
        this.kmstand = kmstand;

    }

}
