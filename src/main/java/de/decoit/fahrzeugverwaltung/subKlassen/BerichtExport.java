package de.decoit.fahrzeugverwaltung.subKlassen;

import de.decoit.fahrzeugverwaltung.Eingabe.Ausgabe.Datenbank;
import de.decoit.fahrzeugverwaltung.Eingabe.Ausgabe.ExportInterface;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.sql.ResultSet;

public class BerichtExport implements ExportInterface {

    @Override
    public void DatenbankExport(String name) {

        PfadDatei pfad = new PfadDatei();
        BerichtName dateiname = new BerichtName();

        String export = "";

        try (PrintStream out = new PrintStream(new FileOutputStream(pfad.pfad() + dateiname.dateiname(name)))) {

            ResultSet rs = Datenbank.stmt.executeQuery("SELECT * FROM Fahrzeuge\n"
                    + "JOIN KFZ.KLASSEN ON KFZ.KLASSEN.KLASSENID = KFZ.FAHRZEUGE.KLASSE\n"
                    + "JOIN KFZ.KRAFTSTOFFE ON KFZ.KRAFTSTOFFE.KRAFTSTOFFID = KFZ.FAHRZEUGE.KRAFTSTOFF");

            while (rs.next()) {

                export = export + "ID: " + rs.getInt("FahrzeugID")
                        + ", Besitzer: " + rs.getString("Besitzer")
                        + ", Fahrzeug: " + rs.getString("Marke") + " " + rs.getString("Typ")
                        + ", " + rs.getString("Fahrzeugklasse")
                        + ", \nVerbrauch: " + rs.getDouble("Verbrauch")
                        + "l/100km, Leistung: " + rs.getInt("Leistung")
                        + "kW, Kmstand: " + rs.getInt("Kilometerstand")
                        + "km, Treibstoff: " + rs.getString("Kraftstoffart") + "\n";

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
