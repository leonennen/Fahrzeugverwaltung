package de.decoit.fahrzeugverwaltung.methodenKlassen;

import de.decoit.fahrzeugverwaltung.subKlassen.PfadAuto;
import de.decoit.fahrzeugverwaltung.subKlassen.PfadPreise;
import de.decoit.fahrzeugverwaltung.KFZ;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class Deserializer {

    public ArrayList<KFZ> deserialzeAuto() {

        ArrayList<KFZ> autoListe = new ArrayList<>();

        try {

            PfadAuto pfad = new PfadAuto();
            FileInputStream fin = new FileInputStream(pfad.pfad());
            ObjectInputStream ois = new ObjectInputStream(fin);

            while (true) {

                autoListe.add((KFZ) ois.readObject());
            }

        } catch (FileNotFoundException ex) {

        } catch (EOFException eof) {

        } catch (Exception ex) {

            System.out.println("---------------------------------------------------------------------------------");
            System.out.println("Fehler beim lesen der Datei: " + ex.getMessage());
            System.out.println("---------------------------------------------------------------------------------");
        }

        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("Datei geladen mit " + autoListe.size() + " Elementen!");

        return autoListe;
    }

    public Treibstoffpreise deserializeTreibstoffpreise() {

        Treibstoffpreise treibstoffpreise = new Treibstoffpreise(0, 0);

        try {

            PfadPreise pfad = new PfadPreise();
            FileInputStream fin = new FileInputStream(pfad.pfad());
            ObjectInputStream ois = new ObjectInputStream(fin);
            treibstoffpreise = (Treibstoffpreise) ois.readObject();

        } catch (FileNotFoundException ex) {

        } catch (EOFException eof) {

        } catch (Exception ex) {

            System.out.println("---------------------------------------------------------------------------------");
            System.out.println("Fehler beim lesen der Datei: " + ex.getMessage());
            System.out.println("---------------------------------------------------------------------------------");
        }

        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("Treibstoffpreise geladen!");

        return treibstoffpreise;
    }

}
