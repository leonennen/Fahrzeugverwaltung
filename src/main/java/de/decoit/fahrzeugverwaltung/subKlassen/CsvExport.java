package de.decoit.fahrzeugverwaltung.subKlassen;

import de.decoit.fahrzeugverwaltung.Eingabe.Ausgabe.Datenbank;
import de.decoit.fahrzeugverwaltung.Eingabe.Ausgabe.ExportInterface;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.Statement;

public class CsvExport implements ExportInterface {

    @Override
    public void DatenbankExport(String name) {

        PfadDatei pfad = new PfadDatei();
        CsvName dateiname = new CsvName();

        String export = "";

        try (PrintStream out = new PrintStream(new FileOutputStream(pfad.pfad() + dateiname.dateiname(name)))) {

            Statement stmtcsv = Datenbank.con.createStatement();
            ResultSet csv = stmtcsv.executeQuery("SELECT * FROM Fahrzeuge\n"
                    + "JOIN KFZ.KLASSEN ON KFZ.KLASSEN.KLASSENID = KFZ.FAHRZEUGE.KLASSE\n"
                    + "JOIN KFZ.KRAFTSTOFFE ON KFZ.KRAFTSTOFFE.KRAFTSTOFFID = KFZ.FAHRZEUGE.KRAFTSTOFF");

            while (csv.next()) {

                export = export + csv.getInt("FahrzeugID") + "," + csv.getString("Besitzer") + ","
                        + csv.getString("Marke") + "," + csv.getString("Typ") + ","
                        + csv.getString("Fahrzeugklasse") + "," + csv.getDouble("Verbrauch") + ","
                        + csv.getInt("Leistung") + "," + csv.getInt("Kilometerstand") + ","
                        + csv.getString("Kraftstoffart") + "\n";

            }

            export = "FahrzeugID,Besitzer,Marke,Typ,Klasse,Verbrauch,Leistung,Kilometerstand,Treibstoff\n" + export;

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
