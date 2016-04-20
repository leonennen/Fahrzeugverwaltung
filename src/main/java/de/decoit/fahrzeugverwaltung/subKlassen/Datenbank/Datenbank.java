package de.decoit.fahrzeugverwaltung.subKlassen.Datenbank;

import de.decoit.fahrzeugverwaltung.Eingabe.Ausgabe.Helper;
import de.decoit.fahrzeugverwaltung.subKlassen.Fahrzeug;
import de.decoit.fahrzeugverwaltung.subKlassen.Klasse;
import de.decoit.fahrzeugverwaltung.subKlassen.Kraftstoff;
import java.sql.*;
import java.util.ArrayList;

public class Datenbank {

    public static void listeDatenbank() {

        try {
            Fahrzeug fahrzeug = new Fahrzeug();
            Klasse klasse = new Klasse();
            Kraftstoff kraftstoff = new Kraftstoff();
            ArrayList<Fahrzeug> fahrzeuge = DatenbankAbfrage.abfrageListe(fahrzeug);

            for (Fahrzeug o : fahrzeuge) {

                klasse = (Klasse) DatenbankAbfrage.abfrageObjekt(klasse, o.getKlasse());
                kraftstoff = (Kraftstoff) DatenbankAbfrage.abfrageObjekt(kraftstoff, o.getKraftstoff());

                System.out.println("ID: " + o.getId() + ", Besitzer: " + o.getBesitzer()
                        + ", Fahrzeug: " + o.getMarke() + " " + o.getTyp() + ", "
                        + klasse.getKlasse()
                        + ", Antrieb: " + kraftstoff.getKraftstoff());
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    public static void listeKraftstoffDatenbank() {

        Kraftstoff kraftstoff = new Kraftstoff();
        ArrayList<Kraftstoff> kraftstoffe = DatenbankAbfrage.abfrageListe(kraftstoff);

        try {
            for (Kraftstoff o : kraftstoffe) {

                System.out.println(o.getKraftstoff() + ": " + o.getPreis() + "€");

            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public static void neuesFahrzeugDatenbank() {

        try {

            boolean b;
            String neubesitzer;
            String neumarke;
            String neutyp;

            do {
                System.out.println("Besitzer des Fahrzeugs:");
                neubesitzer = Helper.user_input.readLine();

                b = neubesitzer.matches("^[A-ZÄÖÜ][a-zA-ZäÄöÖüÜß]+[ ][a-zA-ZäÄöÖüÜß]+([-][a-zA-ZäÄöÖüÜß]+)?$");
                if (!b) {
                    System.out.println("Ungültiger Name");
                }
            } while (!b);

            do {
                System.out.println("Marke des Autos:");
                neumarke = Helper.user_input.readLine();

                b = neumarke.matches("^[A-ZÄÖÜ][a-zA-ZäÄöÖüÜß]+([ -][a-zA-ZäÄöÖüÜß]+)?$");
                if (!b) {
                    System.out.println("Ungültige Marke");
                }
            } while (!b);

            do {
                System.out.println("Autotyp:");
                neutyp = Helper.user_input.readLine();
                b = neutyp.matches("^[a-zA-Z0-9äÄöÖüÜß]+([ ][a-zA-Z0-9äÄöÖüÜß]+)?$");
                if (!b) {
                    System.out.println("Ungültiger Typ");
                }

            } while (!b);

            int neuklasse = 0;                                        //Fahrzeugklasse
            do {
                try {
                    System.out.println("Fahrzeugklassen:");

                    Statement stmtklasse = con.createStatement();
                    ResultSet rs = stmtklasse.executeQuery("SELECT * FROM KLASSEN");

                    while (rs.next()) {
                        int klassenID = rs.getInt("ID");
                        String klasse = rs.getString("KLASSE");
                        System.out.println(klassenID + ": " + klasse);
                    }

                    stmtklasse.close();

                    neuklasse = Integer.parseInt(Helper.user_input.readLine());

                } catch (NumberFormatException ex) {
                    System.out.println("Keine gültige Fahrzeugklasse!");
                    System.out.println(ex.getMessage());
                }
            } while (neuklasse == 0);

            double neuverbrauch = 0;                                        //Verbrauch
            do {
                try {
                    System.out.println("Verbrauch des Autos in l/100km:");
                    neuverbrauch = Double.parseDouble(Helper.user_input.readLine());

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
                    neuleistung = Integer.parseInt(Helper.user_input.readLine());

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
                    neukmstand = Integer.parseInt(Helper.user_input.readLine());

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

            int neukraftstoff = 0;
            do {
                try {
                    System.out.println("Kraftstoffart:");

                    Statement stmtkraftstoff = con.createStatement();
                    ResultSet rs = stmtkraftstoff.executeQuery("SELECT * FROM KRAFTSTOFFE");

                    while (rs.next()) {
                        int kraftstoffID = rs.getInt("ID");
                        String kraftstoff = rs.getString("KRAFTSTOFF");
                        System.out.println(kraftstoffID + ": " + kraftstoff);
                    }

                    stmtkraftstoff.close();

                    neukraftstoff = Integer.parseInt(Helper.user_input.readLine());

                } catch (NumberFormatException ex) {
                    System.out.println("Kein gültiger Treibstoff!");
                    System.out.println(ex.getMessage());
                }
            } while (neukraftstoff == 0);

            PreparedStatement prestmtneu = con.prepareStatement("INSERT INTO FAHRZEUGE (BESITZER, "
                    + "MARKE, TYP, VERBRAUCH, LEISTUNG, KILOMETERSTAND, "
                    + "KRAFTSTOFF_ID, KLASSEN_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            prestmtneu.setString(1, neubesitzer);
            prestmtneu.setString(2, neumarke);
            prestmtneu.setString(3, neutyp);
            prestmtneu.setDouble(4, neuverbrauch);
            prestmtneu.setInt(5, neuleistung);
            prestmtneu.setInt(6, neukmstand);
            prestmtneu.setInt(7, neukraftstoff);
            prestmtneu.setInt(8, neuklasse);
            prestmtneu.executeUpdate();

            System.out.println("Eintrag erstellt!");
            listeDatenbank();
            Helper.user_input.readLine();

        } catch (Exception e) {
            System.err.println(e);
        }

    }

    public static void bearbeitenFahrzeugDatenbank(int id) {

        try {
            ResultSet rs;

            boolean b;
            String neubesitzer;
            String neumarke;
            String neutyp;

            do {
                System.out.println("Besitzer des Fahrzeugs:");
                neubesitzer = Helper.user_input.readLine();

                b = neubesitzer.matches("^[A-ZÄÖÜ][a-zA-ZäÄöÖüÜß]+[ ][a-zA-ZäÄöÖüÜß]+([-][a-zA-ZäÄöÖüÜß]+)?$");
                if (!b) {
                    System.out.println("Ungültiger Name");
                }
            } while (!b);

            do {
                System.out.println("Marke des Autos:");
                neumarke = Helper.user_input.readLine();

                b = neumarke.matches("^[A-ZÄÖÜ][a-zA-ZäÄöÖüÜß]+([ ][a-zA-ZäÄöÖüÜß]+)?$");
                if (!b) {
                    System.out.println("Ungültige Marke");
                }
            } while (!b);

            do {
                System.out.println("Autotyp:");
                neutyp = Helper.user_input.readLine();
                b = neutyp.matches("^[a-zA-ZäÄöÖüÜß][a-zA-ZäÄöÖüÜß]+([ ][a-zA-ZäÄöÖüÜß]+)?$");
                if (!b) {
                    System.out.println("Ungültiger Typ");
                }

            } while (!b);

            int neuklasse = 0;                                        //Fahrzeugklasse
            do {
                try {
                    System.out.println("Fahrzeugklassen:");

                    Statement stmt = con.createStatement();
                    rs = stmt.executeQuery("SELECT * FROM Klassen");

                    while (rs.next()) {
                        int klassenID = rs.getInt("ID");
                        String klasse = rs.getString("KLASSE");
                        System.out.println(klassenID + ": " + klasse);
                    }

                    neuklasse = Integer.parseInt(Helper.user_input.readLine());

                } catch (NumberFormatException ex) {
                    System.out.println("Keine gültige Fahrzeugklasse!");
                    System.out.println(ex.getMessage());
                }
            } while (neuklasse == 0);

            double neuverbrauch = 0;                                        //Verbrauch
            do {
                try {
                    System.out.println("Verbrauch des Autos in l/100km:");
                    neuverbrauch = Double.parseDouble(Helper.user_input.readLine());

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
                    neuleistung = Integer.parseInt(Helper.user_input.readLine());

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
                    neukmstand = Integer.parseInt(Helper.user_input.readLine());

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

            int neukraftstoff = 0;
            do {
                try {
                    System.out.println("Treibstoffart:");

                    Statement stmt = con.createStatement();
                    rs = stmt.executeQuery("SELECT * FROM Kraftstoffe");

                    while (rs.next()) {
                        int kraftstoffID = rs.getInt("ID");
                        String kraftstoff = rs.getString("KKRAFTSTOFF");
                        System.out.println(kraftstoffID + ": " + kraftstoff);
                    }

                    neukraftstoff = Integer.parseInt(Helper.user_input.readLine());

                } catch (NumberFormatException ex) {
                    System.out.println("Kein gültiger Treibstoff!");
                    System.out.println(ex.getMessage());
                }
            } while (neukraftstoff == 0);

            PreparedStatement prestmtbearbeiten = con.prepareStatement("UPDATE FAHRZEUGE SET BESITZER = '?', MARKE = '?'"
                    + ", TYP = '?', VERBRAUCH = ?, LEISTUNG = ?, KILOMETERSTAND = ?"
                    + ", KRAFTSTOFF_ID = ?, KLASSEN_ID = ? WHERE FAHRZEUG_ID = ?");
            prestmtbearbeiten.setString(1, neubesitzer);
            prestmtbearbeiten.setString(2, neumarke);
            prestmtbearbeiten.setString(3, neutyp);
            prestmtbearbeiten.setDouble(4, neuverbrauch);
            prestmtbearbeiten.setInt(5, neuleistung);
            prestmtbearbeiten.setInt(6, neukmstand);
            prestmtbearbeiten.setInt(7, neukraftstoff);
            prestmtbearbeiten.setInt(8, neuklasse);
            prestmtbearbeiten.setLong(9, id);
            prestmtbearbeiten.executeUpdate();

            System.out.println("Eintrag bearbeitet!");

            anzeigenFahrzeugDatenbank(id);

        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public static void bearbeitenKraftstoffDatenbank() {

        try {
            Statement stmtbearbeiten = con.createStatement();
            ResultSet rs = stmtbearbeiten.executeQuery("SELECT * FROM KRAFTSTOFFE");
            double preis;

            while (rs.next()) {
                stmtbearbeiten = con.createStatement();
                int id = rs.getInt(1);
                String kraftstoff = rs.getString("KRAFTSTOFF");
                System.out.println("Aktueller Preis für " + kraftstoff + ":");
                preis = Double.parseDouble(Helper.user_input.readLine());
                stmtbearbeiten.executeUpdate("UPDATE KRAFTSTOFFE SET PREIS = " + preis + " WHERE ID = " + id);
                stmtbearbeiten.close();
            }

            System.out.println("Preise geändert!");

        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public static void anzeigenFahrzeugDatenbank(int id) {

        Fahrzeug car = new Fahrzeug();
        Kraftstoff carfuel = new Kraftstoff();
        Klasse carclass = new Klasse();

        Fahrzeug fahrzeug = (Fahrzeug) DatenbankAbfrage.abfrageObjekt(car, id);
        Kraftstoff kraftstoff = (Kraftstoff) DatenbankAbfrage.abfrageObjekt(carfuel, fahrzeug.getKraftstoff());
        Klasse klasse = (Klasse) DatenbankAbfrage.abfrageObjekt(carclass, fahrzeug.getKlasse());

        System.out.println("ID: " + fahrzeug.getId() + ", Besitzer: " + fahrzeug.getBesitzer()
                + ", Fahrzeug: " + fahrzeug.getMarke() + " " + fahrzeug.getTyp() + ", "
                + "Verbrauch: " + fahrzeug.getVerbrauch() + ", Leistung: " + fahrzeug.getLeistung()
                + ", Kilometerstand: " + fahrzeug.getKmstand() + ", "
                + klasse.getKlasse() + ", Antrieb: " + kraftstoff.getKraftstoff());

    }

    public static void löschenFahrzeugDatenbank(int id) {

        try {
            PreparedStatement prestmtloeschen = con.prepareStatement("DELETE FROM Fahrzeuge WHERE ID = ?");
            prestmtloeschen.setLong(1, id);
            prestmtloeschen.executeUpdate();
            System.out.println("Fahrzeug gelöscht!");
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public static Connection con;

    static {
        Connection temp1 = null;
        try {
            temp1 = DriverManager.getConnection("jdbc:derby://localhost:1527/Fahrzeugverwaltung", "kfz", "kfz");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        con = temp1;
    }

}
