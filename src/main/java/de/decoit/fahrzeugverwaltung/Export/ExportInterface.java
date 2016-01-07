package de.decoit.fahrzeugverwaltung.Export;

import de.decoit.fahrzeugverwaltung.Export.Ausgabe.AusgabeInterface;
import de.decoit.fahrzeugverwaltung.Fahrzeug.KFZ;
import java.util.ArrayList;

public interface ExportInterface {

    public void listeExport(String name, AusgabeInterface ausgabe, ArrayList<KFZ> autoListe);

}
