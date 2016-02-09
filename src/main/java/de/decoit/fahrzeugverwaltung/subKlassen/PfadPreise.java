package de.decoit.fahrzeugverwaltung.subKlassen;

import de.decoit.fahrzeugverwaltung.Eingabe.Ausgabe.Helper;

public class PfadPreise extends Helper {

    @Override
    public String pfad() {
        return ordner() + "Treibstoffpreise.ser";
    }
}
