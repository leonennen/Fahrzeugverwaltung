package de.decoit.fahrzeugverwaltung.subKlassen;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Entity {

    String getTablename();

    Entity convertiere(ResultSet rs) throws SQLException;

}
