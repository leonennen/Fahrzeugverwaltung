package de.decoit.fahrzeugverwaltung.Export.Abspeichern;

import de.decoit.fahrzeugverwaltung.Export.Helper;

public class PfadAuto extends Helper {

    @Override
    public String pfad() {
        return ordner() + "AutoListe.ser";
    }
}
