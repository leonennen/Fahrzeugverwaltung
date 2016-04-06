package de.decoit.fahrzeugverwaltung.subKlassen.Export;

public class CsvName extends Dateiname {

    @Override
    public String dateiname(String name) {

        String dateiname = name + ".csv";

        return dateiname;
    }

}
