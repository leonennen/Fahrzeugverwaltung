package de.decoit.fahrzeugverwaltung;

import de.decoit.fahrzeugverwaltung.Eingabe.Ausgabe.DatenbankAbfrage;
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
        Kraftstoff kraftstoff;

        ArrayList<Fahrzeug> fahrzeuge = DatenbankAbfrage.abfrageFahrzeugListe();

        try {
            for (Fahrzeug o : fahrzeuge) {
                kraftstoff = DatenbankAbfrage.abfrageKraftstoff(o.getKraftstoff());
                preis = kraftstoff.getPreis();
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
