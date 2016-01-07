package de.decoit.fahrzeugverwaltung.Export;

import de.decoit.fahrzeugverwaltung.Helper;
import de.decoit.fahrzeugverwaltung.KFZ;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class BerichtExport implements ExportInterface {

    @Override
    public void listeExport(String name, AusgabeInterface ausgabe, ArrayList<KFZ> autoListe) {

        String export = "";

        for (KFZ auto : autoListe) {
            export = export + ausgabe.autoAusgabe(auto);
        }

        System.out.println(export);

        try (PrintStream out = new PrintStream(new FileOutputStream(Helper.pfadDatei() + name + ".txt"))) {

            out.print(export);

            out.close();

        } catch (Exception ex) {

            System.out.println("---------------------------------------------------------------------------------");
            System.out.println("Fehler beim schreiben der Datei: " + ex.getMessage());
            System.out.println("---------------------------------------------------------------------------------");
        }

    }

}
