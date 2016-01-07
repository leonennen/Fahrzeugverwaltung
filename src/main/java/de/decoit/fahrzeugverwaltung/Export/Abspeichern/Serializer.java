package de.decoit.fahrzeugverwaltung.Export.Abspeichern;

import de.decoit.fahrzeugverwaltung.Fahrzeug.KFZ;
import de.decoit.fahrzeugverwaltung.Fahrzeug.Treibstoffpreise;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Serializer {

    public void serializeAuto(ArrayList<KFZ> autoListe) {

        try {

            PfadAuto pfad = new PfadAuto();
            FileOutputStream fout = new FileOutputStream(pfad.pfad());
            ObjectOutputStream oos = new ObjectOutputStream(fout);

            for (KFZ o : autoListe) {

                oos.writeObject(o);

            }

            oos.close();

            System.out.println(autoListe.size() + " Elemente Gespeichert!");

        } catch (Exception ex) {

            System.out.println("---------------------------------------------------------------------------------");
            System.out.println("Fehler beim schreiben der Datei: " + ex.getMessage());
            System.out.println("---------------------------------------------------------------------------------");
        }
    }

    public void serializeTreibstoffpreise(Treibstoffpreise treibstoffpreise) {
        try {

            PfadPreise pfad = new PfadPreise();
            FileOutputStream fout = new FileOutputStream(pfad.pfad());
            ObjectOutputStream oos = new ObjectOutputStream(fout);

            oos.writeObject(treibstoffpreise);

            oos.close();

            System.out.println("Preise gespeichert!");

        } catch (Exception ex) {

            System.out.println("---------------------------------------------------------------------------------");
            System.out.println("Fehler beim schreiben der Datei: " + ex.getMessage());
            System.out.println("---------------------------------------------------------------------------------");
        }
    }

}
