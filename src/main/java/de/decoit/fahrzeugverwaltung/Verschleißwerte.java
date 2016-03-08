package de.decoit.fahrzeugverwaltung;

import de.decoit.fahrzeugverwaltung.Eingabe.Ausgabe.DatenbankAbfrage;
import de.decoit.fahrzeugverwaltung.subKlassen.Fahrzeug;

public class Verschleißwerte {

    public static double verschleiß(int id, int strecke) {

        double verschleißwert = 0;

        Fahrzeug fahrzeug = DatenbankAbfrage.abfrageFahrzeug(id);

        int kmstand = fahrzeug.getKmstand();
        double streckenkorrektur = 1 + (kmstand / 500000);

        try {
            switch (fahrzeug.getKlasse()) {
                case 1:
                    verschleißwert = strecke - 20;
                    if (strecke < 40) {
                        verschleißwert = strecke * 0.2;
                    }
                    break;
                case 2:
                    verschleißwert = strecke * 0.6;
                    if (strecke < 50) {
                        verschleißwert = 25;
                    } else if (strecke > 500) {
                        verschleißwert = strecke * 0.8;
                    }
                    break;
                case 3:
                    verschleißwert = strecke * 0.3 + 100;
                    if (strecke < 500) {
                        verschleißwert = strecke * 0.4;
                    } else if (strecke > 1000) {
                        verschleißwert = strecke * 0.2;
                    }
                    break;
                case 4:
                    verschleißwert = strecke * 0.4;
                    if (strecke < 100) {
                        verschleißwert = strecke * 0.4 + 20;
                    } else if (strecke > 300) {
                        verschleißwert = strecke * 0.3;
                    }

                    break;
                default:
                    throw new IllegalStateException("Undefinierte Fahrzeugklasse!");
            }
        } catch (IllegalStateException ex) {

            System.out.println(ex.getMessage());
        }
        verschleißwert = verschleißwert * streckenkorrektur;

        return verschleißwert;
    }

}
