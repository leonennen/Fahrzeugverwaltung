package de.decoit.fahrzeugverwaltung.Export.Abspeichern;

import de.decoit.fahrzeugverwaltung.Export.Helper;

public class PfadPreise extends Helper {

    @Override
    public String pfad() {
        return ordner() + "Treibstoffpreise.ser";
    }
}
