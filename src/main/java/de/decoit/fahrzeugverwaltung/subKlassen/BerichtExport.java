package de.decoit.fahrzeugverwaltung.subKlassen;

import de.decoit.fahrzeugverwaltung.Eingabe.Ausgabe.DatenbankAbfrage;
import de.decoit.fahrzeugverwaltung.Eingabe.Ausgabe.ExportInterface;
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

            ArrayList<Fahrzeug> fahrzeuge = DatenbankAbfrage.abfrageFahrzeugListe();

            for (Fahrzeug bericht : fahrzeuge) {

                export = export + "ID: " + bericht.getId()
                        + ", Besitzer: " + bericht.getBesitzer()
                        + ", Fahrzeug: " + bericht.getMarke() + " " + bericht.getTyp()
                        + ", " + DatenbankAbfrage.abfrageKlasse(bericht.getKlasse()).getKlasse()
                        + ", \nVerbrauch: " + bericht.getVerbrauch()
                        + "l/100km, Leistung: " + bericht.getLeistung()
                        + "kW, Kmstand: " + bericht.getKmstand()
                        + "km, Treibstoff: " + DatenbankAbfrage.abfrageKraftstoff(bericht.getKraftstoff()).getKraftstoff() + "\n";

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
