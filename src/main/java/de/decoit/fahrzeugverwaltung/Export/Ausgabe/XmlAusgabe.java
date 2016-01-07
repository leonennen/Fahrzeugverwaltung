package de.decoit.fahrzeugverwaltung.Export.Ausgabe;

import de.decoit.fahrzeugverwaltung.Fahrzeug.KFZ;
import de.decoit.fahrzeugverwaltung.Fahrzeug.Klasse;
import de.decoit.fahrzeugverwaltung.Fahrzeug.Treibstoff;

public class XmlAusgabe implements AusgabeInterface {

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

        String ausgabe = "<KFZ>\n"
                + "<Besitzer>" + besitzer + "</Besitzer>\n"
                + "<Marke>" + marke + "</Marke>\n"
                + "<Typ>" + typ + "</Typ>\n"
                + "<Klasse>" + klasse + "</Klasse>\n"
                + "<Verbrauch>" + verbrauch + "</Verbrauch>\n"
                + "<Leistung>" + leistung + "</Leistung>\n"
                + "<Kilometerstand>" + kmstand + "</Kilometerstand>\n"
                + "<Treibstoff>" + treibstoff + "</Treibstoff>\n"
                + "</KFZ>\n";
        
        return ausgabe;
    }

}
