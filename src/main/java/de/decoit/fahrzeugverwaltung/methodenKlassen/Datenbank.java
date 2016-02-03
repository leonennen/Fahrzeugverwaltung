package de.decoit.fahrzeugverwaltung.methodenKlassen;

import de.decoit.fahrzeugverwaltung.KFZ;
import de.decoit.fahrzeugverwaltung.enumKlassen.Klasse;
import de.decoit.fahrzeugverwaltung.enumKlassen.Treibstoff;
import java.sql.*;

public class Datenbank {

    public void neuSchreiben(KFZ o) {

//        try {
//            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
//        } catch (ClassNotFoundException ex) {
//            System.out.println("Error: unable to load driver class!");
//        }
        try {
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/Fahrzeugverwaltung", "kfz", "kfz");
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT MAX(FAHRZEUGID) FROM Fahrzeuge");
            rs.next();
            int länge = rs.getInt(1) + 1;

            String besitzer = o.getBesitzer();
            String marke = o.getMarke();
            String typ = o.getTyp();
            double verbrauch = o.getVerbrauch();
            int leistung = o.getLeistung();
            int kmstand = o.getKmstand();
            Treibstoff treibstoff = o.getTreibstoff();
            Klasse klasse = o.getKlasse();

            stmt = con.createStatement();

            stmt.executeUpdate("INSERT INTO Fahrzeuge " + "VALUES (" + länge + ",'"
                    + besitzer + "', '" + marke + "', '" + typ + "', " + verbrauch + ", " + leistung + ", " + kmstand + ")");

        } catch (SQLException e) {
            System.err.println(e);
        }
    }

}
