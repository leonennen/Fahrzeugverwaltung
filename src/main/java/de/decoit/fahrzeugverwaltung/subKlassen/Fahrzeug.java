package de.decoit.fahrzeugverwaltung.subKlassen;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Fahrzeug implements Entity {

    private int id;
    private String besitzer;
    private String marke;
    private String typ;
    private double verbrauch;
    private int leistung;
    private int kmstand;
    private int kraftstoff;
    private int klasse;

    public static String TABLENAME = "FAHRZEUGE";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getKraftstoff() {
        return kraftstoff;
    }

    public void setKraftstoff(int kraftstoff) {
        this.kraftstoff = kraftstoff;
    }

    public int getKlasse() {
        return klasse;
    }

    public void setKlasse(int klasse) {
        this.klasse = klasse;
    }

    @Override
    public String getTablename() {
        return TABLENAME;
    }

    @Override
    public Fahrzeug convertiere(ResultSet rsfahrzeug) throws SQLException{
        
        Fahrzeug fahrzeug = new Fahrzeug();

        fahrzeug.setId(rsfahrzeug.getInt("ID"));
        fahrzeug.setBesitzer(rsfahrzeug.getString("BESITZER"));
        fahrzeug.setMarke(rsfahrzeug.getString("MARKE"));
        fahrzeug.setTyp(rsfahrzeug.getString("TYP"));
        fahrzeug.setVerbrauch(rsfahrzeug.getDouble("VERBRAUCH"));
        fahrzeug.setLeistung(rsfahrzeug.getInt("LEISTUNG"));
        fahrzeug.setKmstand(rsfahrzeug.getInt("KILOMETERSTAND"));
        fahrzeug.setKraftstoff(rsfahrzeug.getInt("KRAFTSTOFF_ID"));
        fahrzeug.setKlasse(rsfahrzeug.getInt("KLASSEN_ID"));

        return fahrzeug;
    }

}
