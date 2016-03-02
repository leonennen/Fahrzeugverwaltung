package de.decoit.fahrzeugverwaltung;

import static de.decoit.fahrzeugverwaltung.Eingabe.Ausgabe.Datenbank.con;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DatenbankAbfrage {

    public ArrayList<Fahrzeug> abfrageFahrzeugListe() {

        ArrayList<Fahrzeug> fahrzeuge = new ArrayList<>();

        try {
            Statement stmtfahrzeug = con.createStatement();
            ResultSet rsfahrzeug = stmtfahrzeug.executeQuery("SELECT * FROM KFZ.FAHRZEUGE");

            while (rsfahrzeug.next()) {
                fahrzeuge.add(convertierenFahrzeug(rsfahrzeug));
            }
            stmtfahrzeug.close();

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return fahrzeuge;
    }

    public Fahrzeug abfrageFahrzeug(int id) {

        try {
            PreparedStatement prestmtfahrzeug = con.prepareStatement("SELECT * FROM KFZ.FAHRZEUGE WHERE FAHRZEUG_ID = ?");
            prestmtfahrzeug.setLong(1, id);
            ResultSet rsfahrzeug = prestmtfahrzeug.executeQuery();

            rsfahrzeug.next();

            return convertierenFahrzeug(rsfahrzeug);

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

    private Fahrzeug convertierenFahrzeug(ResultSet rsfahrzeug) throws SQLException {

        Fahrzeug fahrzeug = new Fahrzeug();

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

    public ArrayList<Kraftstoff> abfrageKraftstoffListe() {

        ArrayList<Kraftstoff> kraftstoffe = new ArrayList<>();

        try {
            Statement stmtkraftstoff = con.createStatement();
            ResultSet rskraftstoff = stmtkraftstoff.executeQuery("SELECT * FROM KFZ.KRAFTSTOFFE");

            while (rskraftstoff.next()) {
                kraftstoffe.add(convertierenKraftstoff(rskraftstoff));
            }
            stmtkraftstoff.close();

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return kraftstoffe;
    }

    public Kraftstoff abfrageKraftstoff(int id) {

        try {
            PreparedStatement prestmtkraftstoff = con.prepareStatement("SELECT * FROM KFZ.KRAFTSTOFFE WHERE ID = ?");
            prestmtkraftstoff.setLong(1, id);
            ResultSet rskraftstoff = prestmtkraftstoff.executeQuery();

            rskraftstoff.next();

            return convertierenKraftstoff(rskraftstoff);

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

    private Kraftstoff convertierenKraftstoff(ResultSet rskraftstoff) throws SQLException {

        Kraftstoff kraftstoff = new Kraftstoff();

        kraftstoff.setId(rskraftstoff.getInt("ID"));
        kraftstoff.setKraftstoff(rskraftstoff.getString("KRAFTSTOFF"));
        kraftstoff.setPreis(rskraftstoff.getDouble("PREIS"));

        return kraftstoff;
    }

    public ArrayList<Klasse> abfrageKlasseListe() {

        ArrayList<Klasse> klassen = new ArrayList<>();

        try {
            Statement stmtklasse = con.createStatement();
            ResultSet rsklasse = stmtklasse.executeQuery("SELECT * FROM KFZ.KLASSEN");

            while (rsklasse.next()) {
                klassen.add(convertierenKlasse(rsklasse));
            }
            stmtklasse.close();

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return klassen;
    }

    public Klasse abfrageKlasse(int id) {

        try {
            PreparedStatement prestmtklasse = con.prepareStatement("SELECT * FROM KFZ.KLASSE WHERE ID = ?");
            prestmtklasse.setLong(1, id);
            ResultSet rsklasse = prestmtklasse.executeQuery();

            rsklasse.next();

            return convertierenKlasse(rsklasse);

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

    private Klasse convertierenKlasse(ResultSet rsklasse) throws SQLException {

        Klasse klasse = new Klasse();

        klasse.setId(rsklasse.getInt("ID"));
        klasse.setKlasse(rsklasse.getString("KLASSE"));

        return klasse;
    }

}
