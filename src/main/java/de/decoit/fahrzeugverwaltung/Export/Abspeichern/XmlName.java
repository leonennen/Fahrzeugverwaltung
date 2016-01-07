package de.decoit.fahrzeugverwaltung.Export.Abspeichern;

public class XmlName extends Dateiname {

    @Override
    public String dateiname(String name) {

        String dateiname = name + ".xml";

        return dateiname;
    }

}
