package de.decoit.fahrzeugverwaltung.subKlassen;

import de.decoit.fahrzeugverwaltung.abstrakteKlassen.Dateiname;

public class CsvName extends Dateiname {

    @Override
    public String dateiname(String name) {

        String dateiname = name + ".csv";

        return dateiname;
    }

}
