package de.decoit.fahrzeugverwaltung.subKlassen;

import de.decoit.fahrzeugverwaltung.Eingabe.Ausgabe.ExportInterface;
import de.decoit.fahrzeugverwaltung.KFZ;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class BerichtExport implements ExportInterface {

    @Override
    public void listeExport(String name, ArrayList<KFZ> autoListe) {

        String export = "";

        for (KFZ auto : autoListe) {

            export = export + "Kfz: Besitzer: " + auto.getBesitzer() + ", Fahrzeug: "
                    + auto.getMarke() + " " + auto.getTyp() + ", Klasse: " + auto.getKlasse()
                    + ", Verbrauch: " + auto.getVerbrauch()
                    + "l/100km, Leistung: " + auto.getLeistung() + "kW, Kmstand: "
                    + auto.getKmstand() + "km, Treibstoff: " + auto.getTreibstoff() + "\n";
        }

        System.out.println(export);

        PfadDatei pfad = new PfadDatei();
        BerichtName dateiname = new BerichtName();

        try (PrintStream out = new PrintStream(new FileOutputStream(pfad.pfad() + dateiname.dateiname(name)))) {

            out.print(export);

            out.close();

        } catch (Exception ex) {

            System.out.println("---------------------------------------------------------------------------------");
            System.out.println("Fehler beim schreiben der Datei: " + ex.getMessage());
            System.out.println("---------------------------------------------------------------------------------");
        }

    }

}
