package de.decoit.fahrzeugverwaltung;

import de.decoit.fahrzeugverwaltung.Eingabe.Ausgabe.Datenbank;
import static de.decoit.fahrzeugverwaltung.Eingabe.Ausgabe.Datenbank.stmt;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Sparsamkeit {

    public static int sparsamkeit(int strecke) {

        int id;
        int ecoid = 0;
        double preis;
        double kosten;
        double referenz = 0;
        double verbrauch;

        try {
            stmt = Datenbank.con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Fahrzeuge\n"
                    + "JOIN KFZ.KLASSEN ON KFZ.KLASSEN.KLASSENID = KFZ.FAHRZEUGE.KLASSE\n"
                    + "JOIN KFZ.KRAFTSTOFFE ON KFZ.KRAFTSTOFFE.KRAFTSTOFFID = KFZ.FAHRZEUGE.KRAFTSTOFF");

            while (rs.next()) {
                preis = rs.getDouble("Preis");
                id = rs.getInt("FahrzeugID");
                verbrauch = rs.getDouble("Verbrauch");
                kosten = (Verschleißwerte.verschleiß(id, strecke) + strecke) / 100 * verbrauch * preis;

                if (referenz == 0) {
                    ecoid = id;
                    referenz = kosten;
                } else if (referenz > kosten) {
                    ecoid = id;
                    referenz = kosten;
                }
            }

            stmt.close();

        } catch (SQLException ex) {

            System.out.println(ex);
        }
        return ecoid;

    }

}
