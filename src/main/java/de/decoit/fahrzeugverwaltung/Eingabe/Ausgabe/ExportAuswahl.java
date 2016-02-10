package de.decoit.fahrzeugverwaltung.Eingabe.Ausgabe;

import de.decoit.fahrzeugverwaltung.subKlassen.BerichtExport;
import de.decoit.fahrzeugverwaltung.subKlassen.CsvExport;
import de.decoit.fahrzeugverwaltung.subKlassen.XmlExport;

public class ExportAuswahl {

    public static ExportInterface auswahl(Datei datei) {

        ExportInterface export = null;
        try {
            switch (datei) {
                case XML:
                    export = new XmlExport();
                    break;
                case Bericht:
                    export = new BerichtExport();
                    break;
                case CSV:
                    export = new CsvExport();
                    break;
                default:
                    throw new IllegalStateException("Undefiniert!");
            }
        } catch (IllegalStateException ex) {

            System.out.println(ex.getMessage());
        }
        return export;
    }

}
