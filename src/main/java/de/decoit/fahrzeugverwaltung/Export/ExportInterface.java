package de.decoit.fahrzeugverwaltung.Export;

import de.decoit.fahrzeugverwaltung.KFZ;
import java.util.ArrayList;

public interface ExportInterface {

    public void listeExport(String name, AusgabeInterface ausgabe, ArrayList<KFZ> autoListe);

}
