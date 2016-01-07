package de.decoit.fahrzeugverwaltung.Export;

import de.decoit.fahrzeugverwaltung.Fahrzeug.KFZ;
import java.util.ArrayList;

public interface ExportInterface {

    public void listeExport(String name, ArrayList<KFZ> autoListe);

}
