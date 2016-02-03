package de.decoit.fahrzeugverwaltung;

import de.decoit.fahrzeugverwaltung.enumKlassen.Datei;
import de.decoit.fahrzeugverwaltung.enumKlassen.Klasse;
import de.decoit.fahrzeugverwaltung.enumKlassen.Treibstoff;
import de.decoit.fahrzeugverwaltung.interfaceKlassen.ExportInterface;
import de.decoit.fahrzeugverwaltung.methodenKlassen.Ausgabe;
import de.decoit.fahrzeugverwaltung.methodenKlassen.Deserializer;
import de.decoit.fahrzeugverwaltung.methodenKlassen.ExportAuswahl;
import de.decoit.fahrzeugverwaltung.methodenKlassen.Serializer;
import de.decoit.fahrzeugverwaltung.methodenKlassen.Sparsamkeit;
import de.decoit.fahrzeugverwaltung.methodenKlassen.Treibstoffpreise;
import de.decoit.fahrzeugverwaltung.methodenKlassen.Verschleißwerte;
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        new Main();
    }

    public Main() {

        Console user_input = System.console();
        ArrayList<KFZ> autoListe;
        autoListe = new ArrayList<>();
        Treibstoffpreise treibstoffpreise;
        treibstoffpreise = new Treibstoffpreise(0, 0);
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
                anzeigenFahrzeug(user_input, autoListe);

            } else if (input.equals("4")) {                                                 //Fahrzeug bearbeiten

                liste(autoListe);
                bearbeitenFahrzeug(user_input, autoListe);

            } else if (input.equals("5")) {                                                 //Fahrzeug löschen

                liste(autoListe);
                löschenFahrzeug(user_input, autoListe);

            } else if (input.equals("6")) {                                                 //Treibstoffpreise anzeigen

                System.out.println(treibstoffpreise);
                user_input.readLine();

            } else if (input.equals("7")) {                                                 //Treibstoffpreise ändern

                preiseÄndern(user_input, treibstoffpreise);

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

                speichernFahrzeug(autoListe);
                speichernPreise(treibstoffpreise);
                System.out.println("Wird beendet!");
                System.exit(0);

            } else {

                System.out.println("Falsche Eingabe!!!");

            }
        } while (true);
    }

    public void neuesFahrzeug(Console user_input, ArrayList<KFZ> autoListe) {

        System.out.println("--------------------------------------------------------------------------------");

        KFZ auto = abfrageFahrzeug(user_input, autoListe);
        autoListe.add(auto);

        speichernFahrzeug(autoListe);

        System.out.println("--------------------------------------------------------------------------------");
        System.out.println(new Ausgabe().autoAusgabe(auto));
        System.out.println("--------------------------------------------------------------------------------");
    }

    public void bearbeitenFahrzeug(Console user_input, ArrayList<KFZ> autoListe) {

        int bearbeiten = 0;
        do {
            try {
                System.out.println("Welches Fahrzeug?");
                bearbeiten = Integer.parseInt(user_input.readLine());

                if (bearbeiten < 1 || bearbeiten > autoListe.size()) {
                    System.out.println("Kein Fahrzeug an dieser Position in der Liste!");
                    bearbeiten = 0;
                }

            } catch (NumberFormatException ex) {
                System.out.println("Keine gültige Zahl!");
                System.out.println(ex.getMessage());
            }
        } while (bearbeiten == 0);

        KFZ auto = autoListe.get(bearbeiten - 1);
        System.out.println(new Ausgabe().autoAusgabe(auto));

        int i = 0;
        do {
            try {
                auto = abfrageFahrzeug(user_input, autoListe);
                autoListe.set(bearbeiten - 1, auto);
                i = 1;

            } catch (Exception ex) {
                System.out.println("Konnte nicht bearbeitet werden, bitte versuchen sie es erneut!");
                System.out.println(ex.getMessage());
            }

        } while (i == 0);

        System.out.print(bearbeiten + ". Fahrzeug bearbeitet!");
        System.out.println(new Ausgabe().autoAusgabe(auto));

        speichernFahrzeug(autoListe);

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

        Klasse neuklasse = null;                                        //Fahrzeugklasse
        do {
            try {
                System.out.println("Fahrzeugklasse:");
                System.out.println("Kleinwagen|Mittelklasse|Oberklasse|Transporter");
                neuklasse = Klasse.valueOf(user_input.readLine());

            } catch (IllegalArgumentException ex) {
                System.out.println("Keine gültige Fahrzeugklasse!");
                System.out.println(ex.getMessage());
            }
        } while (neuklasse == null);

        double neuverbrauch = 0;                                        //Verbrauch
        do {
            try {
                System.out.println("Verbrauch des Autos in l/100km:");
                neuverbrauch = Double.parseDouble(user_input.readLine());

                if (neuverbrauch < 0) {
                    System.out.println(neuverbrauch + " l/100km ist kein gültiger Verbrauchswert!");
                    System.out.println("Bitte geben Sie einen positiven Wert ein.");
                    neuverbrauch = 0;
                }
            } catch (NumberFormatException ex) {
                System.out.println("Kein gültiger Verbrauchswert!");
                System.out.println("Bitte geben Sie eine Zahl ein.");
                System.out.println(ex.getMessage());
            }
        } while (neuverbrauch == 0);

        int neuleistung = 0;                                            //Leistung
        do {
            try {
                System.out.println("Leistung des autos in kW:");
                neuleistung = Integer.parseInt(user_input.readLine());

                if (neuleistung < 0) {
                    System.out.println(neuleistung + " kW ist kein gültiger Leistungswert!");
                    System.out.println("Bitte geben Sie einen positiven Wert ein.");
                    neuleistung = 0;
                }
            } catch (NumberFormatException ex) {
                System.out.println("Kein gültiger Leistungswert!");
                System.out.println("Bitte geben Sie eine Zahl ein.");
                System.out.println(ex.getMessage());
            }
        } while (neuleistung == 0);

        int neukmstand = 0;
        do {
            try {
                System.out.println("Kilometerstand:");
                neukmstand = Integer.parseInt(user_input.readLine());

                if (neukmstand < 0) {
                    System.out.println(neukmstand + " km ist kein gültiger Kilometerstand!");
                    System.out.println("Bitte geben Sie einen positiven Wert ein.");
                    neukmstand = 0;
                }
            } catch (NumberFormatException ex) {
                System.out.println("Kein gültiger Kilometerstand!");
                System.out.println("Bitte geben Sie eine Zahl ein.");
                System.out.println(ex.getMessage());
            }
        } while (neukmstand == 0);

        Treibstoff neutreibstoff = null;
        do {
            try {
                System.out.println("Treibstoffart:");
                System.out.println("Benzin | Diesel");
                neutreibstoff = Treibstoff.valueOf(user_input.readLine());

            } catch (IllegalArgumentException ex) {
                System.out.println("Kein gültiger Treibstoff!");
                System.out.println(ex.getMessage());
            }
        } while (neutreibstoff == null);

        KFZ auto = new KFZ(neubesitzer, neumarke, neutyp, neuverbrauch, neuleistung, neukmstand, neutreibstoff, neuklasse);

        return auto;

    }

    public void löschenFahrzeug(Console user_input, ArrayList<KFZ> autoListe) {

        int i = 0;
        do {
            try {
                System.out.println("Welches Fahrzeug von der Liste?");
                int loeschen = Integer.parseInt(user_input.readLine());

                if (loeschen < 1 || loeschen > autoListe.size()) {
                    throw new IllegalStateException("Kein Fahrzeug an dieser Position auf der Liste!");
                }

                autoListe.remove(loeschen - 1);
                System.out.println(loeschen + ". Fahrzeug gelöscht!");
                i = 1;
                speichernFahrzeug(autoListe);

            } catch (Exception ex) {
                System.out.println("Keine gültige Zahl!");
                System.out.println(ex.getMessage());
            }
        } while (i == 0);
    }

    public void anzeigenFahrzeug(Console user_input, ArrayList<KFZ> autoListe) {

        int anzeigen = 0;
        do {
            try {
                System.out.println("Welches Fahrzeug?");
                anzeigen = Integer.parseInt(user_input.readLine());

                if (anzeigen < 1 || anzeigen > autoListe.size()) {
                    anzeigen = 0;
                    throw new IllegalStateException("Kein Fahrzeug an dieser Position auf der Liste!");
                }

                KFZ auto = autoListe.get(anzeigen - 1);
                System.out.println(new Ausgabe().autoAusgabe(auto));
                user_input.readLine();

            } catch (Exception ex) {
                System.out.println("Keine gültige Zahl!");
                System.out.println(ex.getMessage());
            }
        } while (anzeigen == 0);
    }

    public void preiseÄndern(Console user_input, Treibstoffpreise treibstoffpreise) {

        double neudieselpreis = 0;
        do {
            try {
                System.out.println("Dieselpreis in €:");
                neudieselpreis = Double.parseDouble(user_input.readLine());
                if (neudieselpreis <= 0) {
                    neudieselpreis = 0;
                    throw new IllegalStateException("Dieselpreis kann nicht kleiner gleich 0 sein");
                }
            } catch (NumberFormatException ex) {
                System.out.println("Bitte geben Sie eine gültige Zahl ein.");
                System.out.println(ex.getMessage());
            } catch (IllegalStateException ex) {
                System.out.println(ex.getMessage());
            }
        } while (neudieselpreis == 0);

        double neubenzinpreis = 0;
        do {
            try {
                System.out.println("Benzinpreis in €:");
                neubenzinpreis = Double.parseDouble(user_input.readLine());
                if (neubenzinpreis <= 0) {
                    neubenzinpreis = 0;
                    throw new IllegalStateException("Benzinpreis kann nicht kleiner gleich 0 sein");
                }
            } catch (NumberFormatException ex) {
                System.out.println("Bitte geben Sie eine gültige Zahl ein.");
                System.out.println(ex.getMessage());
            } catch (IllegalStateException ex) {
                System.out.println(ex.getMessage());
            }
        } while (neubenzinpreis == 0);

        treibstoffpreise = new Treibstoffpreise(neudieselpreis, neubenzinpreis);

        speichernPreise(treibstoffpreise);
    }

    public void liste(ArrayList<KFZ> autoListe) {

        System.out.println("--------------------------------------------------------------------------------");

        int i = 1;
        for (KFZ o : autoListe) {

            System.out.print(i + ". ");
            System.out.println(new Ausgabe().autoAusgabe(o));
            i++;
        }
        System.out.println("--------------------------------------------------------------------------------");
    }

    public void exportieren(Console user_input, ArrayList<KFZ> autoListe) {

        System.out.println("Dateinamen wählen:");
        String name = user_input.readLine();

        Datei eingabe = null;
        do {
            try {
                System.out.println("Dateiormat wählen:");
                System.out.println("Bericht | XML | CSV");

                eingabe = Datei.valueOf(user_input.readLine());

            } catch (IllegalArgumentException ex) {
                System.out.println("Kein gültiges Dateiformat!");
                System.out.println(ex.getMessage());
            }
        } while (eingabe == null);

        ExportInterface exportdatei = ExportAuswahl.auswahl(eingabe);
        exportdatei.listeExport(name, autoListe);

    }

    public void spritVerbrauch(Console user_input, ArrayList<KFZ> autoListe, Treibstoffpreise treibstoffpreise) {

        System.out.println("Für welches Fahrzeug?");

        KFZ auto = null;
        int input = 0;
        do {
            try {
                input = Integer.parseInt(user_input.readLine());

                if (input < 1 || input > autoListe.size()) {
                    input = 0;
                    throw new IllegalStateException("Kein Fahrzeug an dieser Position auf der Liste!");
                }
                input = 1;

            } catch (Exception ex) {
                System.out.println("Keine gültige Zahl!");
                System.out.println(ex.getMessage());
            }

        } while (input == 0);
        do {
            try {
                auto = autoListe.get(input - 1);
            } catch (IndexOutOfBoundsException ex) {
                System.out.println(ex.getMessage());
            }
        } while (auto == null);

        System.out.println(
                "Für welche Strecke in Kilometern?");
        int strecke = Integer.parseInt(user_input.readLine());

        double preis = treibstoffpreise.preis(auto);
        double kosten = auto.kosten(strecke + Verschleißwerte.verschleiß(auto, strecke), preis);

        System.out.println(
                "--------------------------------------------------------------------------------");
        System.out.println(
                "Für eine Strecke von " + strecke + "km, betragen die Kosten " + kosten + "€.");
        System.out.println(
                "--------------------------------------------------------------------------------");
    }

    public void sparsamstesFahrzeug(Console user_input, ArrayList<KFZ> autoListe, Treibstoffpreise treibstoffpreise) {

        System.out.println("Für welche Strecke in Kilometern?");
        int strecke = 0;
        do {
            try {

                strecke = Integer.parseInt(user_input.readLine());

                if (strecke <= 0) {
                    strecke = 0;
                    throw new IllegalStateException("Bitte eine Strecke größer 0 eingeben!");
                }

            } catch (Exception ex) {
                System.out.println("Keine gültige Zahl!");
                System.out.println(ex.getMessage());
            }
        } while (strecke == 0);

        KFZ auto = Sparsamkeit.sparsamkeit(autoListe, treibstoffpreise, strecke);
        double preis = treibstoffpreise.preis(auto);
        double kosten = auto.kosten(strecke + Verschleißwerte.verschleiß(auto, strecke), preis);

        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Das günstigste Auto für eine Strecke von " + strecke + "km,");
        System.out.println("ist der Wagen von " + auto.getBesitzer() + ", ");
        System.out.println("es entstehen Kosten in Höhe von " + kosten + "€.");
        System.out.println(new Ausgabe().autoAusgabe(auto));
        System.out.println("--------------------------------------------------------------------------------");
    }

    public void speichernFahrzeug(ArrayList<KFZ> autoListe) {

        Serializer serializer = new Serializer();
        serializer.serializeAuto(autoListe);
    }

    public void speichernPreise(Treibstoffpreise treibstoffpreise) {

        Serializer serializer = new Serializer();
        serializer.serializeTreibstoffpreise(treibstoffpreise);
    }
}
