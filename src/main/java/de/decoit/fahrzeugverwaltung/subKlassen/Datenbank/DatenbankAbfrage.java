package de.decoit.fahrzeugverwaltung.subKlassen.Datenbank;

import static de.decoit.fahrzeugverwaltung.subKlassen.Datenbank.Datenbank.con;
import de.decoit.fahrzeugverwaltung.subKlassen.Entity;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DatenbankAbfrage {

    public static ArrayList abfrageListe(Entity entity) {

        ArrayList liste = new ArrayList();

        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM KFZ." + entity.getTablename());

            while (rs.next()) {
                liste.add(entity.convertiere(rs));
            }

            return liste;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

    public static Entity abfrageObjekt(Entity entity, int id) {

        try {
            PreparedStatement prestmt = con.prepareStatement("SELECT * FROM KFZ." + entity.getTablename() + " WHERE ID = ?");
            prestmt.setLong(1, id);
            ResultSet rs = prestmt.executeQuery();

            rs.next();

            return entity.convertiere(rs);

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

}
