package de.decoit.fahrzeugverwaltung.subKlassen;

import de.decoit.fahrzeugverwaltung.Eingabe.Ausgabe.ExportInterface;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class BerichtExport implements ExportInterface {

    @Override
    public void DatenbankExport(String name, Connection con, Statement stmt) {

        PfadDatei pfad = new PfadDatei();
        BerichtName dateiname = new BerichtName();

        String export = "";

        try (PrintStream out = new PrintStream(new FileOutputStream(pfad.pfad() + dateiname.dateiname(name)))) {

            ResultSet rs = stmt.executeQuery("SELECT * FROM Fahrzeuge");

            while (rs.next()) {

                int id = rs.getInt("FahrzeugID");
                String besitzer = rs.getString("Besitzer");
                String marke = rs.getString("Marke");
                String typ = rs.getString("Typ");
                double verbrauch = rs.getDouble("Verbrauch");
                int leistung = rs.getInt("Leistung");
                int kmstand = rs.getInt("Kilometerstand");
                int treibstoff = rs.getInt("Kraftstoff");
                int klasse = rs.getInt("Klasse");

                export = export + "ID: " + rs.getInt("FahrzeugID")
                        + ", Besitzer: " + rs.getString("Besitzer")
                        + ", Fahrzeug: " + rs.getString("Marke") + " " + rs.getString("Typ")
                        + ", Klasse: " + klasse
                        + ", \nVerbrauch: " + rs.getDouble("Verbrauch")
                        + "l/100km, Leistung: " + rs.getInt("Leistung")
                        + "kW, Kmstand: " + rs.getInt("Kilometerstand")
                        + "km, Treibstoff: " + treibstoff + "\n";

            }

            System.out.println(export);

            out.print(export);

            out.close();

        } catch (Exception ex) {

            System.out.println("---------------------------------------------------------------------------------");
            System.out.println("Fehler beim schreiben der Datei: " + ex.getMessage());
            System.out.println("---------------------------------------------------------------------------------");
        }

    }

}
