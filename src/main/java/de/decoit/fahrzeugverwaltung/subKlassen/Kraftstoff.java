package de.decoit.fahrzeugverwaltung.subKlassen;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Kraftstoff implements Entity {

    private int id;
    private String kraftstoff;
    private double preis;

    public static String TABLENAME = "KRAFTSTOFFE";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKraftstoff() {
        return kraftstoff;
    }

    public void setKraftstoff(String kraftstoff) {
        this.kraftstoff = kraftstoff;
    }

    public double getPreis() {
        return preis;
    }

    public void setPreis(double preis) {
        this.preis = preis;
    }

    @Override
    public String getTablename() {
        return TABLENAME;
    }

    @Override
    public Kraftstoff convertiere(ResultSet rskraftstoff) throws SQLException{
        
        Kraftstoff kraftstoff = new Kraftstoff();

        kraftstoff.setId(rskraftstoff.getInt("ID"));
        kraftstoff.setKraftstoff(rskraftstoff.getString("KRAFTSTOFF"));
        kraftstoff.setPreis(rskraftstoff.getDouble("PREIS"));

        return kraftstoff;
    }

}
