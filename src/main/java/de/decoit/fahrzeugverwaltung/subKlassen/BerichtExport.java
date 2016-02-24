package de.decoit.fahrzeugverwaltung.subKlassen;

import de.decoit.fahrzeugverwaltung.Eingabe.Ausgabe.Datenbank;
import de.decoit.fahrzeugverwaltung.Eingabe.Ausgabe.ExportInterface;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.Statement;

public class BerichtExport implements ExportInterface {

    @Override
    public void DatenbankExport(String name) {

        PfadDatei pfad = new PfadDatei();
        BerichtName dateiname = new BerichtName();

        String export = "";

        try (PrintStream out = new PrintStream(new FileOutputStream(pfad.pfad() + dateiname.dateiname(name)))) {

            Statement stmtbericht = Datenbank.con.createStatement();
            ResultSet bericht = stmtbericht.executeQuery("SELECT * FROM Fahrzeuge\n"
                    + "JOIN KFZ.KLASSEN ON KFZ.KLASSEN.KLASSENID = KFZ.FAHRZEUGE.KLASSE\n"
                    + "JOIN KFZ.KRAFTSTOFFE ON KFZ.KRAFTSTOFFE.KRAFTSTOFFID = KFZ.FAHRZEUGE.KRAFTSTOFF");

            while (bericht.next()) {

                export = export + "ID: " + bericht.getInt("FahrzeugID")
                        + ", Besitzer: " + bericht.getString("Besitzer")
                        + ", Fahrzeug: " + bericht.getString("Marke") + " " + bericht.getString("Typ")
                        + ", " + bericht.getString("Fahrzeugklasse")
                        + ", \nVerbrauch: " + bericht.getDouble("Verbrauch")
                        + "l/100km, Leistung: " + bericht.getInt("Leistung")
                        + "kW, Kmstand: " + bericht.getInt("Kilometerstand")
                        + "km, Treibstoff: " + bericht.getString("Kraftstoffart") + "\n";

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
