package de.decoit.fahrzeugverwaltung.Eingabe.Ausgabe;

import java.sql.Connection;
import java.sql.Statement;

public interface ExportInterface {

    public void DatenbankExport(String name, Connection con, Statement stmt);

}
