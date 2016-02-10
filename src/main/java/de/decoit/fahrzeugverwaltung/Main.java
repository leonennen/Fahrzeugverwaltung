package de.decoit.fahrzeugverwaltung;

import de.decoit.fahrzeugverwaltung.Eingabe.Ausgabe.Datei;
import de.decoit.fahrzeugverwaltung.Eingabe.Ausgabe.Datenbank;
import de.decoit.fahrzeugverwaltung.Eingabe.Ausgabe.ExportAuswahl;
import de.decoit.fahrzeugverwaltung.Eingabe.Ausgabe.ExportInterface;
import java.io.*;
import java.sql.*;

public class Main {

    public static void main(String[] args) {

        new Main();
    }

    public Main() {

        Console user_input = System.console();

        try {

            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/Fahrzeugverwaltung", "kfz", "kfz");

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

                Statement stmt = con.createStatement();

                if (input.equals("1")) {                                                        //NeuesFahrzeug

                    Datenbank datenbank = new Datenbank();
                    datenbank.neuesFahrzeugDatenbank(user_input, con, stmt);

                } else if (input.equals("2")) {                                                 //Liste anzeigen

                    liste(con, stmt);
                    user_input.readLine();

                } else if (input.equals("3")) {                                                 //Fahrzeug Anzeigen

                    liste(con, stmt);

                    try {
                        System.out.println("FahrzeugID?");
                        int id = Integer.parseInt(user_input.readLine());
                        anzeigenFahrzeug(id, con, stmt);

                    } catch (Exception ex) {
                        System.out.println("Keine gültige FahrzeugID!");
                        System.out.println(ex.getMessage());
                    }

                } else if (input.equals("4")) {                                                 //Fahrzeug bearbeiten

                    liste(con, stmt);

                    try {

                        System.out.println("FahrzeugID?");

                        int id = Integer.parseInt(user_input.readLine());

                        bearbeitenFahrzeug(user_input, id, con, stmt);

                    } catch (Exception ex) {
                        System.out.println("Keine gültige FahrzeugID!");
                        System.out.println(ex.getMessage());
                    }

                } else if (input.equals("5")) {                                                 //Fahrzeug löschen

                    liste(con, stmt);

                    try {
                        System.out.println("FahrzeugID?");
                        int id = Integer.parseInt(user_input.readLine());
                        löschenFahrzeug(id, con, stmt);

                    } catch (Exception ex) {
                        System.out.println("Keine gültige FahrzeugID!");
                        System.out.println(ex.getMessage());
                    }

                } else if (input.equals("6")) {                                                 //Treibstoffpreise anzeigen

                    listeKraftstoff(con, stmt);
                    user_input.readLine();

                } else if (input.equals("7")) {                                                 //Treibstoffpreise ändern

                    bearbeitenKraftstoff(user_input, con, stmt);

                } else if (input.equals("8")) {                                                 //Verbrauch für bestimmtes Auto berechnen

                    liste(con, stmt);
//                spritVerbrauch(user_input, autoListe, treibstoffpreise);
                    user_input.readLine();

                } else if (input.equals("9")) {                                                 //Sparsamstes Auto anzeigen

//                sparsamstesFahrzeug(user_input, autoListe, treibstoffpreise);
                    user_input.readLine();

                } else if (input.equals("10")) {                                                 //Exportieren

                    exportieren(user_input, con, stmt);
                    user_input.readLine();

                } else if (input.equals("11")) {                                                 //Beenden

                    System.out.println("Wird beendet!");
                    System.exit(0);

                } else {

                    System.out.println("Falsche Eingabe!!!");

                }

                stmt.close();

            } while (true);

        } catch (SQLException e) {

            System.err.println(e);
        }
    }

    public void liste(Connection con, Statement stmt) {
        Datenbank datenbank = new Datenbank();
        datenbank.listeDatenbank(con, stmt);
    }

    public void listeKraftstoff(Connection con, Statement stmt) {

        Datenbank datenbank = new Datenbank();
        datenbank.listeKraftstoffDatenbank(con, stmt);
    }

    public void anzeigenFahrzeug(int id, Connection con, Statement stmt) {

        Datenbank datenbank = new Datenbank();
        datenbank.anzeigenFahrzeugDatenbank(id, con, stmt);
    }

    public void bearbeitenFahrzeug(Console user_input, int id, Connection con, Statement stmt) {

        Datenbank datenbank = new Datenbank();
        datenbank.bearbeitenFahrzeugDatenbank(user_input, id, con, stmt);
    }

    public void bearbeitenKraftstoff(Console user_input, Connection con, Statement stmt) {

        Datenbank datenbank = new Datenbank();
        datenbank.bearbeitenKraftstoffDatenbank(user_input, con, stmt);
    }

    public void löschenFahrzeug(int id, Connection con, Statement stmt) {

        Datenbank datenbank = new Datenbank();
        datenbank.löschenFahrzeugDatenbank(id, con, stmt);
    }

    public void exportieren(Console user_input, Connection con, Statement stmt) {

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
        exportdatei.DatenbankExport(name, con, stmt);

    }
//    public void spritVerbrauch(Console user_input, ArrayList<KFZ> autoListe, Treibstoffpreise treibstoffpreise) {
//
//        System.out.println("Für welches Fahrzeug?");
//
//        KFZ auto = null;
//        int input = 0;
//        do {
//            try {
//                input = Integer.parseInt(user_input.readLine());
//
//                if (input < 1 || input > autoListe.size()) {
//                    input = 0;
//                    throw new IllegalStateException("Kein Fahrzeug an dieser Position auf der Liste!");
//                }
//                input = 1;
//
//            } catch (Exception ex) {
//                System.out.println("Keine gültige Zahl!");
//                System.out.println(ex.getMessage());
//            }
//
//        } while (input == 0);
//        do {
//            try {
//                auto = autoListe.get(input - 1);
//            } catch (IndexOutOfBoundsException ex) {
//                System.out.println(ex.getMessage());
//            }
//        } while (auto == null);
//
//        System.out.println(
//                "Für welche Strecke in Kilometern?");
//        int strecke = Integer.parseInt(user_input.readLine());
//
//        double preis = treibstoffpreise.preis(auto);
//        double kosten = auto.kosten(strecke + Verschleißwerte.verschleiß(auto, strecke), preis);
//
//        System.out.println(
//                "--------------------------------------------------------------------------------");
//        System.out.println(
//                "Für eine Strecke von " + strecke + "km, betragen die Kosten " + kosten + "€.");
//        System.out.println(
//                "--------------------------------------------------------------------------------");
//    }
//    public void sparsamstesFahrzeug(Console user_input, ArrayList<KFZ> autoListe, Treibstoffpreise treibstoffpreise) {
//
//        System.out.println("Für welche Strecke in Kilometern?");
//        int strecke = 0;
//        do {
//            try {
//
//                strecke = Integer.parseInt(user_input.readLine());
//
//                if (strecke <= 0) {
//                    strecke = 0;
//                    throw new IllegalStateException("Bitte eine Strecke größer 0 eingeben!");
//                }
//
//            } catch (Exception ex) {
//                System.out.println("Keine gültige Zahl!");
//                System.out.println(ex.getMessage());
//            }
//        } while (strecke == 0);
//
//        KFZ auto = Sparsamkeit.sparsamkeit(autoListe, treibstoffpreise, strecke);
//        double preis = treibstoffpreise.preis(auto);
//        double kosten = auto.kosten(strecke + Verschleißwerte.verschleiß(auto, strecke), preis);
//
//        System.out.println("--------------------------------------------------------------------------------");
//        System.out.println("Das günstigste Auto für eine Strecke von " + strecke + "km,");
//        System.out.println("ist der Wagen von " + auto.getBesitzer() + ", ");
//        System.out.println("es entstehen Kosten in Höhe von " + kosten + "€.");
//        System.out.println(new Ausgabe().autoAusgabe(auto));
//        System.out.println("--------------------------------------------------------------------------------");
//    }
}
