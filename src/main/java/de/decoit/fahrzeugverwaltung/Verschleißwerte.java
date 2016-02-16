package de.decoit.fahrzeugverwaltung;

import de.decoit.fahrzeugverwaltung.Eingabe.Ausgabe.Datenbank;
import static de.decoit.fahrzeugverwaltung.Eingabe.Ausgabe.Datenbank.stmt;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Verschleißwerte {

    public static double verschleiß(int id, int strecke) {

        double verschleißwert = 0;

        try {
            stmt = Datenbank.con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Fahrzeuge\n"
                    + "JOIN KFZ.KLASSEN ON KFZ.KLASSEN.KLASSENID = KFZ.FAHRZEUGE.KLASSE\n"
                    + "JOIN KFZ.KRAFTSTOFFE ON KFZ.KRAFTSTOFFE.KRAFTSTOFFID = KFZ.FAHRZEUGE.KRAFTSTOFF\n"
                    + "WHERE FAHRZEUGID = " + id);
            rs.next();

            int kmstand = rs.getInt("Kilometerstand");
            double streckenkorrektur = 1 + (kmstand / 500000);

            try {
                switch (rs.getInt("Klasse")) {
                    case 1:
                        verschleißwert = strecke - 20;
                        if (strecke < 40) {
                            verschleißwert = strecke * 0.2;
                        }
                        break;
                    case 2:
                        verschleißwert = strecke * 0.6;
                        if (strecke < 50) {
                            verschleißwert = 25;
                        } else if (strecke > 500) {
                            verschleißwert = strecke * 0.8;
                        }
                        break;
                    case 3:
                        verschleißwert = strecke * 0.3 + 100;
                        if (strecke < 500) {
                            verschleißwert = strecke * 0.4;
                        } else if (strecke > 1000) {
                            verschleißwert = strecke * 0.2;
                        }
                        break;
                    case 4:
                        verschleißwert = strecke * 0.4;
                        if (strecke < 100) {
                            verschleißwert = strecke * 0.4 + 20;
                        } else if (strecke > 300) {
                            verschleißwert = strecke * 0.3;
                        }

                        break;
                    default:
                        throw new IllegalStateException("Undefinierte Fahrzeugklasse!");
                }
            } catch (IllegalStateException ex) {

                System.out.println(ex.getMessage());
            }
            verschleißwert = verschleißwert * streckenkorrektur;

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return verschleißwert;
    }

}
