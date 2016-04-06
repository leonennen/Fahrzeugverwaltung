package de.decoit.fahrzeugverwaltung.subKlassen;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Klasse implements Entity {

    private int id;
    private String klasse;

    public static String TABLENAME = "KLASSEN";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKlasse() {
        return klasse;
    }

    public void setKlasse(String klasse) {
        this.klasse = klasse;
    }

    @Override
    public String getTablename() {
        return TABLENAME;
    }

    @Override
    public Klasse convertiere(ResultSet rsklasse) throws SQLException{

        Klasse klasse = new Klasse();

        klasse.setId(rsklasse.getInt("ID"));
        klasse.setKlasse(rsklasse.getString("KLASSE"));

        return klasse;
    }

}
