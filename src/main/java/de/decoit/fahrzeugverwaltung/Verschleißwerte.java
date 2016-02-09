package de.decoit.fahrzeugverwaltung;

import de.decoit.fahrzeugverwaltung.KFZ;
import de.decoit.fahrzeugverwaltung.Klasse;

public class Verschleißwerte {

    public static double verschleiß(KFZ auto, int strecke) {

        Klasse klasse = auto.getKlasse();
        double verschleißwert = 0;
        double streckenkorrektur = 1 + (auto.getKmstand() / 500000);

        try {
            switch (klasse) {
                case Kleinwagen:
                    verschleißwert = strecke - 20;
                    if (strecke < 40) {
                        verschleißwert = strecke * 0.2;
                    }
                    break;
                case Mittelklasse:
                    verschleißwert = strecke * 0.6;
                    if (strecke < 50) {
                        verschleißwert = 25;
                    } else if (strecke > 500) {
                        verschleißwert = strecke * 0.8;
                    }
                    break;
                case Oberklasse:
                    verschleißwert = strecke * 0.3 + 100;
                    if (strecke < 500) {
                        verschleißwert = strecke * 0.4;
                    } else if (strecke > 1000) {
                        verschleißwert = strecke * 0.2;
                    }
                    break;
                case Transporter:
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
