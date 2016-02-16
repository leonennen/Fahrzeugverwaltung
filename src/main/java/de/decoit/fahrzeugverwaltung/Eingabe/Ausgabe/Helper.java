package de.decoit.fahrzeugverwaltung.Eingabe.Ausgabe;

import java.io.Console;

public abstract class Helper {

    public abstract String pfad();

    public String ordner() {

        return System.getProperty("user.home") + System.getProperty("file.separator") + "Schreibtisch" + System.getProperty("file.separator");

    }

    public static Console user_input = System.console();

}

//"NetBeansProjects" + System.getProperty("file.separator") + "Fahrzeugverwaltung" + System.getProperty("file.separator");
