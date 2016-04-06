package de.decoit.fahrzeugverwaltung;

import de.decoit.fahrzeugverwaltung.subKlassen.Datenbank.DatenbankAbfrage;
import de.decoit.fahrzeugverwaltung.subKlassen.Fahrzeug;
import de.decoit.fahrzeugverwaltung.subKlassen.Kraftstoff;
import java.util.ArrayList;

public class Sparsamkeit {

    public static int sparsamkeit(int strecke) {

        int id;
        int ecoid = 0;
        double preis;
        double kosten;
        double referenz = 0;
        double verbrauch;

        Fahrzeug fahrzeug = new Fahrzeug();
        Kraftstoff kraftstoff = new Kraftstoff();

        ArrayList<Fahrzeug> fahrzeuge = DatenbankAbfrage.abfrageListe(fahrzeug);
        ArrayList<Kraftstoff> kraftstoffe = DatenbankAbfrage.abfrageListe(kraftstoff);

        try {
            for (Fahrzeug o : fahrzeuge) {

                preis = kraftstoffe.get(o.getKraftstoff()).getPreis();
                id = o.getId();
                verbrauch = o.getVerbrauch();
                kosten = (Verschleißwerte.verschleiß(id, strecke) + strecke) / 100 * verbrauch * preis;

                if (referenz == 0) {
                    ecoid = id;
                    referenz = kosten;
                } else if (referenz > kosten) {
                    ecoid = id;
                    referenz = kosten;
                }
            }

        } catch (Exception ex) {

            System.out.println(ex);
        }
        return ecoid;

    }

}
