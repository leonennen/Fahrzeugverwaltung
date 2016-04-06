package de.decoit.fahrzeugverwaltung.subKlassen.Export;

import de.decoit.fahrzeugverwaltung.Eingabe.Ausgabe.Helper;

public class PfadDatei extends Helper {

    @Override
    public String pfad() {
        return ordner();
    }
}
