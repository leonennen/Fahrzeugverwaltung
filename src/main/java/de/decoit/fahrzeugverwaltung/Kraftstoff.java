package de.decoit.fahrzeugverwaltung;


public class Kraftstoff {
    
    private int id;
    private String kraftstoff;
    private double preis;

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
    
}
