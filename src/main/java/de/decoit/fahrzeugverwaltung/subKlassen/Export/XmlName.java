package de.decoit.fahrzeugverwaltung.subKlassen.Export;

public class XmlName extends Dateiname {

    @Override
    public String dateiname(String name) {

        String dateiname = name + ".xml";

        return dateiname;
    }

}
