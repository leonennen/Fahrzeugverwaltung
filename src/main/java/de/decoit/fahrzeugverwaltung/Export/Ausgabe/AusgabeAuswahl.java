package de.decoit.fahrzeugverwaltung.Export.Ausgabe;

import de.decoit.fahrzeugverwaltung.Export.Datei;

public class AusgabeAuswahl {

    public static AusgabeInterface auswahl(Datei datei) {
        AusgabeInterface ausgabe = null;
        switch (datei) {
            case XML:
                ausgabe = new XmlAusgabe();
                break;
            case Bericht:
                ausgabe = new BerichtAusgabe();
                break;
            case CSV:
                ausgabe = new CsvAusgabe();
                break;
            default:
                new IllegalStateException("Undefiniert!");
        }
        return ausgabe;
    }

}