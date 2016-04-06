package de.decoit.fahrzeugverwaltung.subKlassen.Export;

import de.decoit.fahrzeugverwaltung.subKlassen.Export.BerichtExport;
import de.decoit.fahrzeugverwaltung.subKlassen.Export.CsvExport;
import de.decoit.fahrzeugverwaltung.subKlassen.Export.XmlExport;

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
