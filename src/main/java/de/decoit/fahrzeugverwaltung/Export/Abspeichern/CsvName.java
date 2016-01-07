package de.decoit.fahrzeugverwaltung.Export.Abspeichern;

public class CsvName extends Dateiname {

    @Override
    public String dateiname(String name) {

        String dateiname = name + ".csv";

        return dateiname;
    }

}
