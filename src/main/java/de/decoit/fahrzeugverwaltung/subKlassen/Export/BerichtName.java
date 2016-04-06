package de.decoit.fahrzeugverwaltung.subKlassen.Export;

public class BerichtName extends Dateiname {

    @Override
    public String dateiname(String name) {

        String dateiname = name + ".txt";

        return dateiname;
    }

}