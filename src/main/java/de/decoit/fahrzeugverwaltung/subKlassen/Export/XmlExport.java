package de.decoit.fahrzeugverwaltung.subKlassen.Export;

import de.decoit.fahrzeugverwaltung.subKlassen.Datenbank.DatenbankAbfrage;
import de.decoit.fahrzeugverwaltung.subKlassen.Fahrzeug;
import de.decoit.fahrzeugverwaltung.subKlassen.Klasse;
import de.decoit.fahrzeugverwaltung.subKlassen.Kraftstoff;
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

            Fahrzeug fahrzeug = new Fahrzeug();
            Klasse klasse = new Klasse();
            Kraftstoff kraftstoff = new Kraftstoff();
            ArrayList<Fahrzeug> fahrzeuge = DatenbankAbfrage.abfrageListe(fahrzeug);
            klasse = (Klasse) DatenbankAbfrage.abfrageObjekt(klasse, fahrzeug.getKlasse());
            kraftstoff = (Kraftstoff) DatenbankAbfrage.abfrageObjekt(kraftstoff, fahrzeug.getKraftstoff());

            for (Fahrzeug xml : fahrzeuge) {

                export = export + "<KFZ>\n"
                        + "<FahrzeugID>" + xml.getId() + "</FahrzeugID>\n"
                        + "<Besitzer>" + xml.getBesitzer() + "</Besitzer>\n"
                        + "<Marke>" + xml.getMarke() + "</Marke>\n"
                        + "<Typ>" + xml.getTyp() + "</Typ>\n"
                        + "<Klasse>" + klasse.getKlasse() + "</Klasse>\n"
                        + "<Verbrauch>" + xml.getVerbrauch() + "</Verbrauch>\n"
                        + "<Leistung>" + xml.getLeistung() + "</Leistung>\n"
                        + "<Kilometerstand>" + xml.getKmstand() + "</Kilometerstand>\n"
                        + "<Treibstoff>" + kraftstoff.getKraftstoff() + "</Treibstoff>\n"
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
