package de.decoit.fahrzeugverwaltung.Export;

import de.decoit.fahrzeugverwaltung.Helper;
import de.decoit.fahrzeugverwaltung.KFZ;
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

        try (PrintStream out = new PrintStream(new FileOutputStream(Helper.pfadDatei() + name + ".xml"))) {

            out.print(export);

            out.close();

        } catch (Exception ex) {

            System.out.println("---------------------------------------------------------------------------------");
            System.out.print("Fehler beim schreiben der Datei: " + ex.getMessage());
            System.out.println("---------------------------------------------------------------------------------");
        }
    }
}
