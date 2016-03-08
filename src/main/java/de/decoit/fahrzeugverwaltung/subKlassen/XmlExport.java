package de.decoit.fahrzeugverwaltung.subKlassen;

import de.decoit.fahrzeugverwaltung.Eingabe.Ausgabe.DatenbankAbfrage;
import de.decoit.fahrzeugverwaltung.Eingabe.Ausgabe.ExportInterface;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class XmlExport implements ExportInterface {

    @Override
    public void DatenbankExport(String name) {

        PfadDatei pfad = new PfadDatei();
        XmlName dateiname = new XmlName();

        String export = "";

        try (PrintStream out = new PrintStream(new FileOutputStream(pfad.pfad() + dateiname.dateiname(name)))) {

            ArrayList<Fahrzeug> fahrzeuge = DatenbankAbfrage.abfrageFahrzeugListe();

            for (Fahrzeug xml : fahrzeuge) {

                export = export + "<KFZ>\n"
                        + "<FahrzeugID>" + xml.getId() + "</FahrzeugID>\n"
                        + "<Besitzer>" + xml.getBesitzer() + "</Besitzer>\n"
                        + "<Marke>" + xml.getMarke() + "</Marke>\n"
                        + "<Typ>" + xml.getTyp() + "</Typ>\n"
                        + "<Klasse>" + DatenbankAbfrage.abfrageKlasse(xml.getKlasse()).getKlasse() + "</Klasse>\n"
                        + "<Verbrauch>" + xml.getVerbrauch() + "</Verbrauch>\n"
                        + "<Leistung>" + xml.getLeistung() + "</Leistung>\n"
                        + "<Kilometerstand>" + xml.getKmstand() + "</Kilometerstand>\n"
                        + "<Treibstoff>" + DatenbankAbfrage.abfrageKraftstoff(xml.getKraftstoff()).getKraftstoff() + "</Treibstoff>\n"
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
