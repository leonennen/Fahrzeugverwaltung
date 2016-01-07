package de.decoit.fahrzeugverwaltung.Export;

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
