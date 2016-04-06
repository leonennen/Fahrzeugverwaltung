package de.decoit.fahrzeugverwaltung.subKlassen.Export;

import de.decoit.fahrzeugverwaltung.subKlassen.Datenbank.DatenbankAbfrage;
import de.decoit.fahrzeugverwaltung.subKlassen.Fahrzeug;
import de.decoit.fahrzeugverwaltung.subKlassen.Klasse;
import de.decoit.fahrzeugverwaltung.subKlassen.Kraftstoff;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class BerichtExport implements ExportInterface {

    @Override
    public void DatenbankExport(String name) {

        PfadDatei pfad = new PfadDatei();
        BerichtName dateiname = new BerichtName();

        String export = "";

        try (PrintStream out = new PrintStream(new FileOutputStream(pfad.pfad() + dateiname.dateiname(name)))) {

            Fahrzeug fahrzeug = new Fahrzeug();
            Klasse klasse = new Klasse();
            Kraftstoff kraftstoff = new Kraftstoff();
            ArrayList<Fahrzeug> fahrzeuge = DatenbankAbfrage.abfrageListe(fahrzeug);
            klasse = (Klasse) DatenbankAbfrage.abfrageObjekt(klasse, fahrzeug.getKlasse());
            kraftstoff = (Kraftstoff) DatenbankAbfrage.abfrageObjekt(kraftstoff, fahrzeug.getKraftstoff());

            for (Fahrzeug bericht : fahrzeuge) {

                export = export + "ID: " + bericht.getId()
                        + ", Besitzer: " + bericht.getBesitzer()
                        + ", Fahrzeug: " + bericht.getMarke() + " " + bericht.getTyp()
                        + ", " + klasse.getKlasse()
                        + ", \nVerbrauch: " + bericht.getVerbrauch()
                        + "l/100km, Leistung: " + bericht.getLeistung()
                        + "kW, Kmstand: " + bericht.getKmstand()
                        + "km, Treibstoff: " + kraftstoff.getKraftstoff() + "\n";

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
