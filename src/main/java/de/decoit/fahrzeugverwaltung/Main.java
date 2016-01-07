package de.decoit.fahrzeugverwaltung;

import de.decoit.fahrzeugverwaltung.Fahrzeug.Verschleißwerte;
import de.decoit.fahrzeugverwaltung.Fahrzeug.Klasse;
import de.decoit.fahrzeugverwaltung.Fahrzeug.Sparsamkeit;
import de.decoit.fahrzeugverwaltung.Fahrzeug.KFZ;
import de.decoit.fahrzeugverwaltung.Fahrzeug.Treibstoffpreise;
import de.decoit.fahrzeugverwaltung.Fahrzeug.Treibstoff;
import de.decoit.fahrzeugverwaltung.Export.Abspeichern.Deserializer;
import de.decoit.fahrzeugverwaltung.Export.Abspeichern.Serializer;
import de.decoit.fahrzeugverwaltung.Export.Ausgabe;
import de.decoit.fahrzeugverwaltung.Export.ExportInterface;
import de.decoit.fahrzeugverwaltung.Export.ExportAuswahl;
import de.decoit.fahrzeugverwaltung.Export.Datei;
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        new Main();
    }

    public Main() {

        Console user_input = System.console();
        ArrayList<KFZ> autoListe = new ArrayList<>();
        Treibstoffpreise treibstoffpreise = new Treibstoffpreise(0, 0);
        Deserializer deserializer = new Deserializer();
        autoListe = deserializer.deserialzeAuto();
        treibstoffpreise = deserializer.deserializeTreibstoffpreise();

        do {

            System.out.println("---------------------------------------------");
            System.out.println("| FAHRZEUGVERWALTUNG:                       |");
            System.out.println("---------------------------------------------");
            System.out.println("| Neues Fahrzeug hinzufügen:          (01)  |");
            System.out.println("| Liste anzeigen:                     (02)  |");
            System.out.println("| Fahrzeug anzeigen:                  (03)  |");
            System.out.println("| Fahrzeug bearbeiten:                (04)  |");
            System.out.println("| Fahrzeug löschen:                   (05)  |");
            System.out.println("| Treibstoffpreise anzeigen:          (06)  |");
            System.out.println("| Treibstoffpreise bearbeiten:        (07)  |");
            System.out.println("| Fahrtkosten berechnen:              (08)  |");
            System.out.println("| Sparsamstes Fahrzeug:               (09)  |");
            System.out.println("| Exportieren:                        (10)  |");
            System.out.println("| Beenden:                            (11)  |");
            System.out.println("---------------------------------------------");

            String input = user_input.readLine();

            if (input.equals("1")) {                                                        //NeuesFahrzeug

                neuesFahrzeug(user_input, autoListe);

            } else if (input.equals("2")) {                                                 //Liste anzeigen

                liste(autoListe);
                user_input.readLine();

            } else if (input.equals("3")) {                                                 //Fahrzeug Anzeigen

                liste(autoListe);
                System.out.println("Welches Fahrzeug?");
                KFZ auto = autoListe.get(Integer.parseInt(user_input.readLine()) - 1);
                System.out.println(new Ausgabe().autoAusgabe(auto));
                user_input.readLine();

            } else if (input.equals("4")) {                                                 //Fahrzeug bearbeiten

                liste(autoListe);
                bearbeitenFahrzeug(user_input, autoListe);

            } else if (input.equals("5")) {                                                 //Fahrzeug löschen

                System.out.println("Welches Fahrzeug von der Liste?");
                int loeschen = Integer.parseInt(user_input.readLine());
                autoListe.remove(loeschen - 1);
                System.out.println(loeschen + ". Fahrzeug gelöscht!");
                Serializer serializer = new Serializer();
                serializer.serializeAuto(autoListe);

            } else if (input.equals("6")) {                                                 //Treibstoffpreise anzeigen

                System.out.println(treibstoffpreise);
                user_input.readLine();

            } else if (input.equals("7")) {                                                 //Treibstoffpreise ändern

                System.out.println("Dieselpreis in €:");
                double neudieselpreis = Double.parseDouble(user_input.readLine());

                System.out.println("Benzinpreis in €:");
                double neubenzinpreis = Double.parseDouble(user_input.readLine());

                treibstoffpreise = new Treibstoffpreise(neudieselpreis, neubenzinpreis);

                Serializer serializer = new Serializer();                                   //nach dem Ändern speichern
                serializer.serializeTreibstoffpreise(treibstoffpreise);

            } else if (input.equals("8")) {                                                 //Verbrauch für bestimmtes Auto berechnen

                liste(autoListe);
                spritVerbrauch(user_input, autoListe, treibstoffpreise);
                user_input.readLine();

            } else if (input.equals("9")) {                                                 //Sparsamstes Auto anzeigen

                sparsamstesFahrzeug(user_input, autoListe, treibstoffpreise);
                user_input.readLine();

            } else if (input.equals("10")) {                                                 //Exportieren

                exportieren(user_input, autoListe);

                user_input.readLine();

            } else if (input.equals("11")) {                                                 //Beenden

                System.out.println("Wird beendet!");
                System.exit(0);

            } else {

                System.out.println("Falsche Eingabe!!!");

            }
        } while (true);
    }

    public void neuesFahrzeug(Console user_input, ArrayList<KFZ> autoListe) {

        System.out.println("---------------------------------------------------------------------------------");

        KFZ auto = abfrageFahrzeug(user_input, autoListe);
        autoListe.add(auto);

        Serializer serializer = new Serializer();
        serializer.serializeAuto(autoListe);

        System.out.println("---------------------------------------------------------------------------------");
        System.out.println(new Ausgabe().autoAusgabe(auto));
        System.out.println("---------------------------------------------------------------------------------");
    }

    public void bearbeitenFahrzeug(Console user_input, ArrayList<KFZ> autoListe) {

        System.out.println("Welches Fahrzeug?");
        int bearbeiten = Integer.parseInt(user_input.readLine());

        KFZ auto = abfrageFahrzeug(user_input, autoListe);
        autoListe.set(bearbeiten - 1, auto);

        System.out.print(bearbeiten + ". Fahrzeug bearbeitet!");
        System.out.println(new Ausgabe().autoAusgabe(auto));

        Serializer serializer = new Serializer();
        serializer.serializeAuto(autoListe);

    }

    public KFZ abfrageFahrzeug(Console user_input, ArrayList<KFZ> autoListe) {

        System.out.println("Besitzer des Fahrzeugs:");
        String neubesitzer;
        neubesitzer = user_input.readLine();

        System.out.println("Marke des Autos:");
        String neumarke;
        neumarke = user_input.readLine();

        System.out.println("Autotyp:");
        String neutyp;
        neutyp = user_input.readLine();

        System.out.println("Klasse:");
        Klasse neuklasse;
        neuklasse = Klasse.valueOf(user_input.readLine());

        System.out.println("Verbrauch des Autos in l/100km:");
        double neuverbrauch;
        neuverbrauch = Double.parseDouble(user_input.readLine());

        System.out.println("Leistung des autos in kW:");
        int neuleistung;
        neuleistung = Integer.parseInt(user_input.readLine());

        System.out.println("Kilometerstand:");
        int neukmstand;
        neukmstand = Integer.parseInt(user_input.readLine());

        System.out.println("Treibstoffart:");
        Treibstoff neutreibstoff;
        neutreibstoff = Treibstoff.valueOf(user_input.readLine());

        KFZ auto = new KFZ(neubesitzer, neumarke, neutyp, neuverbrauch, neuleistung, neukmstand, neutreibstoff, neuklasse);

        return auto;

    }

    public void liste(ArrayList<KFZ> autoListe) {

        System.out.println("---------------------------------------------------------------------------------");

        int i = 1;
        for (KFZ o : autoListe) {

            System.out.print(i + ". ");
            System.out.println(new Ausgabe().autoAusgabe(o));
            i++;
        }
        System.out.println("---------------------------------------------------------------------------------");
    }

    public void exportieren(Console user_input, ArrayList<KFZ> autoListe) {

        System.out.println("Dateinamen wählen:");
        String name = user_input.readLine();

        System.out.println("Dateiormat wählen:");
        System.out.println("Bericht | XML | CSV");

        Datei eingabe = Datei.valueOf(user_input.readLine());

        ExportInterface exportdatei = ExportAuswahl.auswahl(eingabe);
        exportdatei.listeExport(name, autoListe);
    }

    public void spritVerbrauch(Console user_input, ArrayList<KFZ> autoListe, Treibstoffpreise treibstoffpreise) {

        System.out.println("Für welches Fahrzeug?");
        KFZ auto = autoListe.get(Integer.parseInt(user_input.readLine()) - 1);

        System.out.println("Für welche Strecke in Kilometern?");
        int strecke = Integer.parseInt(user_input.readLine());

        double preis = treibstoffpreise.preis(auto);
        double kosten = auto.kosten(strecke + Verschleißwerte.verschleiß(auto, strecke), preis);

        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("Für eine Strecke von " + strecke + "km, betragen die Kosten " + kosten + "€.");
        System.out.println("---------------------------------------------------------------------------------");
    }

    public void sparsamstesFahrzeug(Console user_input, ArrayList<KFZ> autoListe, Treibstoffpreise treibstoffpreise) {

        System.out.println("Für welche Strecke in Kilometern?");
        int strecke = Integer.parseInt(user_input.readLine());

        KFZ auto = Sparsamkeit.sparsamkeit(autoListe, treibstoffpreise, strecke);
        double preis = treibstoffpreise.preis(auto);
        double kosten = auto.kosten(strecke + Verschleißwerte.verschleiß(auto, strecke), preis);

        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("Das günstigste Auto für eine Strecke von " + strecke + "km,");
        System.out.println("ist der Wagen von " + auto.getBesitzer() + ", ");
        System.out.println("es entstehen Kosten in Höhe von " + kosten + "€.");
        System.out.println(new Ausgabe().autoAusgabe(auto));
        System.out.println("---------------------------------------------------------------------------------");
    }

}
