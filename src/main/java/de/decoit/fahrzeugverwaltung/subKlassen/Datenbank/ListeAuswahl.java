package de.decoit.fahrzeugverwaltung.subKlassen.Datenbank;

public class ListeAuswahl {

    public static AbfrageInterface auswahl(String liste) {

        AbfrageInterface abfrage = null;

        try {
            switch (liste) {
                case "fahrzeug":
                    break;
                case "kraftstoff":
                    break;
                case "klasse":
                    break;
                default:
                    throw new IllegalStateException("Undefiniert!");
            }
        } catch (IllegalStateException ex) {

            System.out.println(ex.getMessage());
        }

        return abfrage;
    }

}
