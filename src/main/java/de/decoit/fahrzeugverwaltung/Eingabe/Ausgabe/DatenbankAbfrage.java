package de.decoit.fahrzeugverwaltung.Eingabe.Ausgabe;

import static de.decoit.fahrzeugverwaltung.Eingabe.Ausgabe.Datenbank.con;
import de.decoit.fahrzeugverwaltung.subKlassen.Fahrzeug;
import de.decoit.fahrzeugverwaltung.subKlassen.Klasse;
import de.decoit.fahrzeugverwaltung.subKlassen.Kraftstoff;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DatenbankAbfrage {

    public static ArrayList<Fahrzeug> abfrageFahrzeugListe() {

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

    public static Fahrzeug abfrageFahrzeug(int id) {

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

    private static Fahrzeug convertierenFahrzeug(ResultSet rsfahrzeug) throws SQLException {

        Fahrzeug fahrzeug = new Fahrzeug();

        fahrzeug.setId(rsfahrzeug.getInt("FAHRZEUG_ID"));
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

    public static ArrayList<Kraftstoff> abfrageKraftstoffListe() {

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

    public static Kraftstoff abfrageKraftstoff(int id) {

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

    private static Kraftstoff convertierenKraftstoff(ResultSet rskraftstoff) throws SQLException {

        Kraftstoff kraftstoff = new Kraftstoff();

        kraftstoff.setId(rskraftstoff.getInt("ID"));
        kraftstoff.setKraftstoff(rskraftstoff.getString("KRAFTSTOFF"));
        kraftstoff.setPreis(rskraftstoff.getDouble("PREIS"));

        return kraftstoff;
    }

    public static ArrayList<Klasse> abfrageKlasseListe() {

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

    public static Klasse abfrageKlasse(int id) {

        try {
            PreparedStatement prestmtklasse = con.prepareStatement("SELECT * FROM KFZ.KLASSEN WHERE ID = ?");
            prestmtklasse.setLong(1, id);
            ResultSet rsklasse = prestmtklasse.executeQuery();

            rsklasse.next();

            return convertierenKlasse(rsklasse);

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

    private static Klasse convertierenKlasse(ResultSet rsklasse) throws SQLException {

        Klasse klasse = new Klasse();

        klasse.setId(rsklasse.getInt("ID"));
        klasse.setKlasse(rsklasse.getString("KLASSE"));

        return klasse;
    }

}
