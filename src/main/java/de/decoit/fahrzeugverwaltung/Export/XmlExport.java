package de.decoit.fahrzeugverwaltung.Export;

import de.decoit.fahrzeugverwaltung.Export.Ausgabe.AusgabeInterface;
import de.decoit.fahrzeugverwaltung.Fahrzeug.KFZ;
import de.decoit.fahrzeugverwaltung.Export.Abspeichern.PfadDatei;
import de.decoit.fahrzeugverwaltung.Export.Abspeichern.XmlName;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class XmlExport implements ExportInterface {

    @Override
    public void listeExport(String name, AusgabeInterface ausgabe, ArrayList<KFZ> autoListe) {

        String export = "";

        for (KFZ auto : autoListe) {
            export = export + ausgabe.autoAusgabe(auto);
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
