package de.decoit.fahrzeugverwaltung.Export;

import de.decoit.fahrzeugverwaltung.KFZ;
import de.decoit.fahrzeugverwaltung.Klasse;
import de.decoit.fahrzeugverwaltung.Treibstoff;

public class CsvAusgabe implements AusgabeInterface {


    @Override
    public String autoAusgabe(KFZ auto) {
        String besitzer = auto.getBesitzer();
        String marke = auto.getMarke();
        String typ = auto.getTyp();
        double verbrauch = auto.getVerbrauch();
        int leistung = auto.getLeistung();
        int kmstand = auto.getKmstand();
        Treibstoff treibstoff = auto.getTreibstoff();
        Klasse klasse = auto.getKlasse();

        String ausgabe = besitzer + "," + marke + "," + typ
                + "," + klasse + "," + verbrauch + "," + leistung + ","
                + kmstand + "," + treibstoff+"\n";
        return ausgabe;
    }
}
