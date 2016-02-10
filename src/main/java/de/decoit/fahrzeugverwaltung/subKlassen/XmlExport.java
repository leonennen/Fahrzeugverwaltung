package de.decoit.fahrzeugverwaltung.subKlassen;

import de.decoit.fahrzeugverwaltung.Eingabe.Ausgabe.ExportInterface;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class XmlExport implements ExportInterface {

    @Override
    public void DatenbankExport(String name, Connection con, Statement stmt) {

        PfadDatei pfad = new PfadDatei();
        XmlName dateiname = new XmlName();

        String export = "";

        try (PrintStream out = new PrintStream(new FileOutputStream(pfad.pfad() + dateiname.dateiname(name)))) {

            ResultSet rs = stmt.executeQuery("SELECT * FROM Fahrzeuge");

            while (rs.next()) {

                int klasse = rs.getInt("Klasse");
                int treibstoff = rs.getInt("Kraftstoff");

                export = export + "<KFZ>\n"
                        + "<FahrzeugID>" + rs.getInt("FahrzeugID") + "</FahrzeugID>\n"
                        + "<Besitzer>" + rs.getString("Besitzer") + "</Besitzer>\n"
                        + "<Marke>" + rs.getString("Marke") + "</Marke>\n"
                        + "<Typ>" + rs.getString("Typ") + "</Typ>\n"
                        + "<Klasse>" + klasse + "</Klasse>\n"
                        + "<Verbrauch>" + rs.getDouble("Verbrauch") + "</Verbrauch>\n"
                        + "<Leistung>" + rs.getInt("Leistung") + "</Leistung>\n"
                        + "<Kilometerstand>" + rs.getInt("Kilometerstand") + "</Kilometerstand>\n"
                        + "<Treibstoff>" + treibstoff + "</Treibstoff>\n"
                        + "</KFZ>\n";

            }

            export = "<?xml version=\"1.0\"?>\n"
                    + "<Fahrzeugverwaltung>\n"
                    + export
                    + "</Fahrzeugverwaltung>\n";

            System.out.println(export);

            out.print(export);

            out.close();

        } catch (Exception ex) {

            System.out.println("---------------------------------------------------------------------------------");
            System.out.print("Fehler beim schreiben der Datei: " + ex.getMessage());
            System.out.println("---------------------------------------------------------------------------------");
        }
    }

}
