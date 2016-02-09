package de.decoit.fahrzeugverwaltung.subKlassen;

import de.decoit.fahrzeugverwaltung.Eingabe.Ausgabe.ExportInterface;
import de.decoit.fahrzeugverwaltung.KFZ;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class XmlExport implements ExportInterface {

    @Override
    public void listeExport(String name, ArrayList<KFZ> autoListe) {

        String export = "";

        for (KFZ auto : autoListe) {

            export = export + "<KFZ>\n"
                    + "<Besitzer>" + auto.getBesitzer() + "</Besitzer>\n"
                    + "<Marke>" + auto.getMarke() + "</Marke>\n"
                    + "<Typ>" + auto.getTyp() + "</Typ>\n"
                    + "<Klasse>" + auto.getKlasse() + "</Klasse>\n"
                    + "<Verbrauch>" + auto.getVerbrauch() + "</Verbrauch>\n"
                    + "<Leistung>" + auto.getLeistung() + "</Leistung>\n"
                    + "<Kilometerstand>" + auto.getKmstand() + "</Kilometerstand>\n"
                    + "<Treibstoff>" + auto.getTreibstoff() + "</Treibstoff>\n"
                    + "</KFZ>\n";
        }

        export = "<?xml version=\"1.0\"?>\n"
                + "<Fahrzeugverwaltung>\n" + export
                + "</Fahrzeugverwaltung>\n";

        System.out.println(export);

        PfadDatei pfad = new PfadDatei();
        XmlName dateiname = new XmlName();

        try (PrintStream out = new PrintStream(new FileOutputStream(pfad.pfad() + dateiname.dateiname(name)))) {

            out.print(export);

            out.close();

        } catch (Exception ex) {

            System.out.println("---------------------------------------------------------------------------------");
            System.out.print("Fehler beim schreiben der Datei: " + ex.getMessage());
            System.out.println("---------------------------------------------------------------------------------");
        }
    }
}
