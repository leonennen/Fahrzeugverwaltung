package de.decoit.fahrzeugverwaltung;

import de.decoit.fahrzeugverwaltung.Eingabe.Ausgabe.Datei;
import de.decoit.fahrzeugverwaltung.Eingabe.Ausgabe.Datenbank;
import static de.decoit.fahrzeugverwaltung.Eingabe.Ausgabe.Datenbank.stmt;
import de.decoit.fahrzeugverwaltung.Eingabe.Ausgabe.ExportAuswahl;
import de.decoit.fahrzeugverwaltung.Eingabe.Ausgabe.ExportInterface;
import de.decoit.fahrzeugverwaltung.Eingabe.Ausgabe.Helper;
import java.sql.*;

public class Main {

    public static void main(String[] args) {

        new Main();
    }

    public Main() {

        try {

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

                String input = Helper.user_input.readLine();

                switch (input) {
                    case "1":
                        Datenbank.neuesFahrzeugDatenbank();
                        break;
                    case "2":
                        Datenbank.listeDatenbank();
                        Helper.user_input.readLine();
                        break;
                    case "3":
                        Datenbank.listeDatenbank();
                        try {
                            System.out.println("FahrzeugID?");
                            int id = Integer.parseInt(Helper.user_input.readLine());
                            Datenbank.anzeigenFahrzeugDatenbank(id);

                        } catch (Exception ex) {
                            System.out.println("Keine gültige FahrzeugID!");
                            System.out.println(ex.getMessage());
                        }
                        Helper.user_input.readLine();
                        break;
                    case "4":
                        Datenbank.listeDatenbank();
                        try {
                            System.out.println("FahrzeugID?");
                            int id = Integer.parseInt(Helper.user_input.readLine());
                            Datenbank.bearbeitenFahrzeugDatenbank(id);

                        } catch (Exception ex) {
                            System.out.println("Keine gültige FahrzeugID!");
                            System.out.println(ex.getMessage());
                        }
                        break;
                    case "5":
                        Datenbank.listeDatenbank();
                        try {
                            System.out.println("FahrzeugID?");
                            int id = Integer.parseInt(Helper.user_input.readLine());
                            Datenbank.löschenFahrzeugDatenbank(id);

                        } catch (Exception ex) {
                            System.out.println("Keine gültige FahrzeugID!");
                            System.out.println(ex.getMessage());
                        }
                        break;
                    case "6":
                        Datenbank.listeKraftstoffDatenbank();
                        Helper.user_input.readLine();
                        break;
                    case "7":
                        Datenbank.bearbeitenKraftstoffDatenbank();
                        break;
                    case "8":
                        Datenbank.listeDatenbank();
                        spritVerbrauch();
                        Helper.user_input.readLine();
                        break;
                    case "9":
                        sparsamstesFahrzeug();
                        Helper.user_input.readLine();
                        break;
                    case "10":
                        exportieren();
                        Helper.user_input.readLine();
                        break;
                    case "11":
                        System.out.println("Wird beendet!");
                        System.exit(0);
                    default:
                        System.out.println("Falsche Eingabe!!!");
                        break;
                }

                Datenbank.stmt.close();

            } while (true);

        } catch (SQLException e) {

            System.err.println(e);
        }
    }

    public static void exportieren() {

        System.out.println("Dateinamen wählen:");
        String name = Helper.user_input.readLine();

        Datei eingabe = null;
        do {
            try {
                System.out.println("Dateiormat wählen:");
                System.out.println("Bericht | XML | CSV");

                eingabe = Datei.valueOf(Helper.user_input.readLine());

            } catch (IllegalArgumentException ex) {
                System.out.println("Kein gültiges Dateiformat!");
                System.out.println(ex.getMessage());
            }
        } while (eingabe == null);

        ExportInterface exportdatei = ExportAuswahl.auswahl(eingabe);
        exportdatei.DatenbankExport(name);

    }

    public static void spritVerbrauch() {

        try {
            int id = 0;
            int strecke = 0;
            do {
                try {
                    System.out.println("Für welches Fahrzeug?");
                    id = Integer.parseInt(Helper.user_input.readLine());
                    System.out.println("Für welche Strecke in Kilometern?");
                    strecke = Integer.parseInt(Helper.user_input.readLine());
                    if (strecke <= 0) {
                        strecke = 0;
                        throw new IllegalStateException("Bitte eine Strecke größer 0 eingeben!");
                    }
                } catch (NumberFormatException | IllegalStateException ex) {
                    System.out.println("Keine gültige Zahl!");
                    System.out.println(ex.getMessage());
                }
            } while (id == 0);

            stmt = Datenbank.con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Fahrzeuge\n"
                    + "JOIN KFZ.KLASSEN ON KFZ.KLASSEN.KLASSENID = KFZ.FAHRZEUGE.KLASSE\n"
                    + "JOIN KFZ.KRAFTSTOFFE ON KFZ.KRAFTSTOFFE.KRAFTSTOFFID = KFZ.FAHRZEUGE.KRAFTSTOFF\n"
                    + "WHERE FAHRZEUGID = " + id);
            rs.next();

            double preis = rs.getDouble("Preis");
            double verbrauch = rs.getDouble("Verbrauch");
            double kosten = ((Verschleißwerte.verschleiß(id, strecke) + strecke) * verbrauch / 100) * preis;
            stmt.close();

            System.out.println("Für eine Strecke von " + strecke + "km, betragen die Kosten " + kosten + "€.");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public static void sparsamstesFahrzeug() {
        try {
            int strecke = 0;
            int id;
            do {
                try {
                    System.out.println("Für welche Strecke in Kilometern?");
                    strecke = Integer.parseInt(Helper.user_input.readLine());

                    if (strecke <= 0) {
                        strecke = 0;
                        throw new IllegalStateException("Bitte eine Strecke größer 0 eingeben!");
                    }

                } catch (NumberFormatException | IllegalStateException ex) {
                    System.out.println("Keine gültige Zahl!");
                    System.out.println(ex.getMessage());
                }
            } while (strecke == 0);

            id = Sparsamkeit.sparsamkeit(strecke);

            stmt = Datenbank.con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Fahrzeuge\n"
                    + "JOIN KFZ.KLASSEN ON KFZ.KLASSEN.KLASSENID = KFZ.FAHRZEUGE.KLASSE\n"
                    + "JOIN KFZ.KRAFTSTOFFE ON KFZ.KRAFTSTOFFE.KRAFTSTOFFID = KFZ.FAHRZEUGE.KRAFTSTOFF\n"
                    + "WHERE FAHRZEUGID = " + id);
            rs.next();

            double preis = rs.getDouble("Preis");
            double kosten = (Verschleißwerte.verschleiß(id, strecke) + strecke) * preis;
            stmt.close();

            System.out.println("Das günstigste Auto für eine Strecke von " + strecke + "km,");
            System.out.println("ist der Wagen von " + rs.getString("Besitzer") + ", ");
            System.out.println("es entstehen Kosten in Höhe von " + kosten + "€.");
            Datenbank.anzeigenFahrzeugDatenbank(id);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

}
