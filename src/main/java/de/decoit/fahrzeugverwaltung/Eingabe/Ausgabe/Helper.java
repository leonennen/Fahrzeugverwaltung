package de.decoit.fahrzeugverwaltung.Eingabe.Ausgabe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class Helper {

    public abstract String pfad();

    public String ordner() {

        return System.getProperty("user.home") + System.getProperty("file.separator") + "Schreibtisch" + System.getProperty("file.separator");

    }

    public static void input() {
        try {
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/Fahrzeugverwaltung", "kfz", "kfz");
        } catch (SQLException ex) {
        }
    }

}

//"NetBeansProjects" + System.getProperty("file.separator") + "Fahrzeugverwaltung" + System.getProperty("file.separator");
