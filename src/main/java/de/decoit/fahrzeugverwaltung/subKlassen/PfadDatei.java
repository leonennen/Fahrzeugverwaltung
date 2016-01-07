package de.decoit.fahrzeugverwaltung.subKlassen;

import de.decoit.fahrzeugverwaltung.abstrakteKlassen.Helper;

public class PfadDatei extends Helper {

    @Override
    public String pfad() {
        return ordner();
    }
}
