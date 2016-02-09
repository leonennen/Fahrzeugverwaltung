package de.decoit.fahrzeugverwaltung.subKlassen;

import de.decoit.fahrzeugverwaltung.Eingabe.Ausgabe.Dateiname;

public class XmlName extends Dateiname {

    @Override
    public String dateiname(String name) {

        String dateiname = name + ".xml";

        return dateiname;
    }

}
