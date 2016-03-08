package de.decoit.fahrzeugverwaltung.subKlassen;

import de.decoit.fahrzeugverwaltung.Eingabe.Ausgabe.DatenbankAbfrage;
import de.decoit.fahrzeugverwaltung.Eingabe.Ausgabe.ExportInterface;
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

            ArrayList<Fahrzeug> fahrzeuge = DatenbankAbfrage.abfrageFahrzeugListe();

            for (Fahrzeug csv : fahrzeuge) {

                export = export + csv.getId() + "," + csv.getBesitzer() + ","
                        + csv.getMarke() + "," + csv.getTyp() + ","
                        + DatenbankAbfrage.abfrageKlasse(csv.getKlasse()).getKlasse() + "," + csv.getVerbrauch() + ","
                        + csv.getLeistung() + "," + csv.getKmstand() + ","
                        + DatenbankAbfrage.abfrageKraftstoff(csv.getKraftstoff()).getKraftstoff() + "\n";

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
