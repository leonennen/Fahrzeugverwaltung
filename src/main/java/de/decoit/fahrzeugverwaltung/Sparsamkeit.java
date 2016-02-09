package de.decoit.fahrzeugverwaltung;

import de.decoit.fahrzeugverwaltung.Eingabe.Ausgabe.Treibstoffpreise;
import de.decoit.fahrzeugverwaltung.KFZ;
import java.util.ArrayList;

public class Sparsamkeit {

    public static KFZ sparsamkeit(ArrayList<KFZ> autoListe, Treibstoffpreise treibstoffpreise, int strecke) {

        KFZ auto = null;

        for (KFZ o : autoListe) {

            if (auto == null) {
                auto = o;
            }
            double richtwert = (auto.getVerbrauch() * treibstoffpreise.preis(auto)) / 100 * (strecke + Verschleißwerte.verschleiß(auto, strecke));
            double neuwert = (o.getVerbrauch() * treibstoffpreise.preis(o)) / 100 * (strecke + Verschleißwerte.verschleiß(o, strecke));

            if (neuwert < richtwert) {
                auto = o;
            }
        }

        return auto;
    }
}
