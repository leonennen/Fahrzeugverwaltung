package de.decoit.fahrzeugverwaltung;

public class Helper {

    public static String pfadAuto() {
        return System.getProperty("user.home") + System.getProperty("file.separator") + "AutoListe.ser";
    }

    public static String pfadPreise() {
        return System.getProperty("user.home") + System.getProperty("file.separator") + "Treibstoffpreise.ser";
    }

    public static String pfadDatei() {
        return (System.getProperty("user.home") + System.getProperty("file.separator"));
    }

}
