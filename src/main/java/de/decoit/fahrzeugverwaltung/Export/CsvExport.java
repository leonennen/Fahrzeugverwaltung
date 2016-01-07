package de.decoit.fahrzeugverwaltung.Export;

import de.decoit.fahrzeugverwaltung.Export.Abspeichern.CsvName;
import de.decoit.fahrzeugverwaltung.Fahrzeug.KFZ;
import de.decoit.fahrzeugverwaltung.Export.Abspeichern.PfadDatei;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class CsvExport implements ExportInterface {

    @Override
    public void listeExport(String name, ArrayList<KFZ> autoListe) {

        String export = "";

        for (KFZ auto : autoListe) {

            export = export + auto.getBesitzer() + "," + auto.getMarke() + "," + auto.getTyp()
                    + "," + auto.getKlasse() + "," + auto.getVerbrauch() + "," + auto.getLeistung() + ","
                    + auto.getKmstand() + "," + auto.getTreibstoff() + "\n";
        }

        export = "Besitzer,Marke,Typ,Klasse,Verbrauch,Leistung,Kilometerstand,Treibstoff\n" + export;

        System.out.println(export);

        PfadDatei pfad = new PfadDatei();
        CsvName dateiname = new CsvName();

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
