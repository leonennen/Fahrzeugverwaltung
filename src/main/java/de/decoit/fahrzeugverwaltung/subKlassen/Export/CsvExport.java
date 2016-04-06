package de.decoit.fahrzeugverwaltung.subKlassen.Export;

import de.decoit.fahrzeugverwaltung.subKlassen.Datenbank.DatenbankAbfrage;
import de.decoit.fahrzeugverwaltung.subKlassen.Fahrzeug;
import de.decoit.fahrzeugverwaltung.subKlassen.Klasse;
import de.decoit.fahrzeugverwaltung.subKlassen.Kraftstoff;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class CsvExport implements ExportInterface {

    @Override
    public void DatenbankExport(String name) {

        PfadDatei pfad = new PfadDatei();
        CsvName dateiname = new CsvName();

        String export = "";

        try (PrintStream out = new PrintStream(new FileOutputStream(pfad.pfad() + dateiname.dateiname(name)))) {

            Fahrzeug fahrzeug = new Fahrzeug();
            Klasse klasse = new Klasse();
            Kraftstoff kraftstoff = new Kraftstoff();
            ArrayList<Fahrzeug> fahrzeuge = DatenbankAbfrage.abfrageListe(fahrzeug);
            klasse = (Klasse) DatenbankAbfrage.abfrageObjekt(klasse, fahrzeug.getKlasse());
            kraftstoff = (Kraftstoff) DatenbankAbfrage.abfrageObjekt(kraftstoff, fahrzeug.getKraftstoff());

            for (Fahrzeug csv : fahrzeuge) {

                export = export + csv.getId() + "," + csv.getBesitzer() + ","
                        + csv.getMarke() + "," + csv.getTyp() + ","
                        + klasse.getKlasse() + "," + csv.getVerbrauch() + ","
                        + csv.getLeistung() + "," + csv.getKmstand() + ","
                        + kraftstoff.getKraftstoff() + "\n";

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
