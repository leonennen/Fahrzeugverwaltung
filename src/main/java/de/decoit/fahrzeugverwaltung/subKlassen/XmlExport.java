package de.decoit.fahrzeugverwaltung.subKlassen;

import de.decoit.fahrzeugverwaltung.Eingabe.Ausgabe.Datenbank;
import de.decoit.fahrzeugverwaltung.Eingabe.Ausgabe.ExportInterface;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.Statement;

public class XmlExport implements ExportInterface {

    @Override
    public void DatenbankExport(String name) {

        PfadDatei pfad = new PfadDatei();
        XmlName dateiname = new XmlName();

        String export = "";

        try (PrintStream out = new PrintStream(new FileOutputStream(pfad.pfad() + dateiname.dateiname(name)))) {

            Statement stmtxml = Datenbank.con.createStatement();
            ResultSet xml = stmtxml.executeQuery("SELECT * FROM Fahrzeuge\n"
                    + "JOIN KFZ.KLASSEN ON KFZ.KLASSEN.KLASSENID = KFZ.FAHRZEUGE.KLASSE\n"
                    + "JOIN KFZ.KRAFTSTOFFE ON KFZ.KRAFTSTOFFE.KRAFTSTOFFID = KFZ.FAHRZEUGE.KRAFTSTOFF");

            while (xml.next()) {

                export = export + "<KFZ>\n"
                        + "<FahrzeugID>" + xml.getInt("FahrzeugID") + "</FahrzeugID>\n"
                        + "<Besitzer>" + xml.getString("Besitzer") + "</Besitzer>\n"
                        + "<Marke>" + xml.getString("Marke") + "</Marke>\n"
                        + "<Typ>" + xml.getString("Typ") + "</Typ>\n"
                        + "<Klasse>" + xml.getString("Fahrzeugklasse") + "</Klasse>\n"
                        + "<Verbrauch>" + xml.getDouble("Verbrauch") + "</Verbrauch>\n"
                        + "<Leistung>" + xml.getInt("Leistung") + "</Leistung>\n"
                        + "<Kilometerstand>" + xml.getInt("Kilometerstand") + "</Kilometerstand>\n"
                        + "<Treibstoff>" + xml.getString("Kraftstoffart") + "</Treibstoff>\n"
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
