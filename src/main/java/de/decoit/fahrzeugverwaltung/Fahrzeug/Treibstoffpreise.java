package de.decoit.fahrzeugverwaltung.Fahrzeug;

import de.decoit.fahrzeugverwaltung.Fahrzeug.KFZ;
import java.io.Serializable;

public class Treibstoffpreise implements Serializable {

    public double dieselpreis;
    public double benzinpreis;

    public Treibstoffpreise(double dieselpreis, double benzinpreis) {
        this.dieselpreis = dieselpreis;
        this.benzinpreis = benzinpreis;
    }

    public double preis(KFZ auto) {
        double preis = 0;
        Treibstoff treibstoff = auto.getTreibstoff();

        switch (treibstoff) {
            case Diesel:
                preis = dieselpreis;
                break;
            case Benzin:
                preis = benzinpreis;
                break;
            default:
                throw new IllegalStateException("Undefinierter Treibstofftp!");
        }
        return preis;
    }

    @Override
    public String toString() {
        return "Dieselpreis: " + dieselpreis + "€, Benzinpreis: " + benzinpreis + "€";
    }

    public double getDieselpreis() {
        return dieselpreis;
    }

    public void setDieselpreis(double dieselpreis) {
        this.dieselpreis = dieselpreis;
    }

    public double getBenzinpreis() {
        return benzinpreis;
    }

    public void setBenzinpreis(double benzinpreis) {
        this.benzinpreis = benzinpreis;
    }

}
