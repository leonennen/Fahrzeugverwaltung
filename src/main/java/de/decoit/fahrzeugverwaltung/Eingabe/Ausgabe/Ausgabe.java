package de.decoit.fahrzeugverwaltung.Eingabe.Ausgabe;

import de.decoit.fahrzeugverwaltung.KFZ;
import de.decoit.fahrzeugverwaltung.Klasse;
import de.decoit.fahrzeugverwaltung.Treibstoff;

public class Ausgabe{

    public String autoAusgabe(KFZ auto) {

        String besitzer = auto.getBesitzer();
        String marke = auto.getMarke();
        String typ = auto.getTyp();
        double verbrauch = auto.getVerbrauch();
        int leistung = auto.getLeistung();
        int kmstand = auto.getKmstand();
        Treibstoff treibstoff = auto.getTreibstoff();
        Klasse klasse = auto.getKlasse();

        String ausgabe = "Kfz: |Besitzer: " + besitzer + ", Fahrzeug: "
                + marke + " " + typ + ", Klasse: " + klasse
                + ", \n     |Verbrauch: " + verbrauch
                + "l/100km, Leistung: " + leistung + "kW, Kmstand: "
                + kmstand + "km, Treibstoff: " + treibstoff;
        return ausgabe;
    }
}
