package de.decoit.fahrzeugverwaltung.Export;

public abstract class Helper {

    public abstract String pfad();

    public String ordner() {

        return System.getProperty("user.home") + System.getProperty("file.separator") + "NetBeansProjects"
                + System.getProperty("file.separator") + "Fahrzeugverwaltung" + System.getProperty("file.separator");

    }
}
