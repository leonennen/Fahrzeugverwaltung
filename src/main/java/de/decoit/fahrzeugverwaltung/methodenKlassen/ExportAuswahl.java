package de.decoit.fahrzeugverwaltung.methodenKlassen;

import de.decoit.fahrzeugverwaltung.interfaceKlassen.ExportInterface;
import de.decoit.fahrzeugverwaltung.enumKlassen.Datei;
import de.decoit.fahrzeugverwaltung.subKlassen.BerichtExport;
import de.decoit.fahrzeugverwaltung.subKlassen.CsvExport;
import de.decoit.fahrzeugverwaltung.subKlassen.XmlExport;

public class ExportAuswahl {

    public static ExportInterface auswahl(Datei datei) {
        ExportInterface export = null;
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
                new IllegalStateException("Undefiniert!");
        }
        return export;
    }

}
