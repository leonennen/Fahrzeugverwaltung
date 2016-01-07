package de.decoit.fahrzeugverwaltung.subKlassen;

import de.decoit.fahrzeugverwaltung.abstrakteKlassen.Helper;

public class PfadAuto extends Helper {

    @Override
    public String pfad() {
        return ordner() + "AutoListe.ser";
    }
}
