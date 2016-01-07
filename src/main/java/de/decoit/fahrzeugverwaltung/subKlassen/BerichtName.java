package de.decoit.fahrzeugverwaltung.subKlassen;

import de.decoit.fahrzeugverwaltung.abstrakteKlassen.Dateiname;

public class BerichtName extends Dateiname {

    @Override
    public String dateiname(String name) {

        String dateiname = name + ".txt";

        return dateiname;
    }

}