package de.decoit.fahrzeugverwaltung.Eingabe.Ausgabe;

import java.io.Console;
import java.sql.*;

public class Datenbank {

    public void listeDatenbank(Connection con, Statement stmt) {
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM Fahrzeuge\n"
                    + "JOIN KFZ.KLASSEN ON KFZ.KLASSEN.KLASSENID = KFZ.FAHRZEUGE.KLASSE\n"
                    + "JOIN KFZ.KRAFTSTOFFE ON KFZ.KRAFTSTOFFE.KRAFTSTOFFID = KFZ.FAHRZEUGE.KRAFTSTOFF");

            while (rs.next()) {
                int id = rs.getInt("FahrzeugID");
                String besitzer = rs.getString("Besitzer");
                String marke = rs.getString("Marke");
                String typ = rs.getString("Typ");
                String klasse = rs.getString("Fahrzeugklasse");
                String kraftstoff = rs.getString("Kraftstoffart");

                System.out.println("ID: " + id + ", Besitzer: " + besitzer + ", Fahrzeug: "
                        + marke + " " + typ + ", " + klasse + ", Antrieb: " + kraftstoff);

            }

        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public void listeKraftstoffDatenbank(Connection con, Statement stmt) {

        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM Kraftstoffe");

            while (rs.next()) {
                String art = rs.getString("Kraftstoffart");
                double preis = rs.getDouble("Preis");

                System.out.println(art + ": " + preis + "€");

            }

        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public void neuesFahrzeugDatenbank(Console user_input, Connection con, Statement stmt) {

        int id = 0;
        try {
            ResultSet rs = stmt.executeQuery("SELECT MAX(FAHRZEUGID) FROM Fahrzeuge");
            rs.next();
            id = rs.getInt(1) + 1;

            System.out.println("Besitzer des Fahrzeugs:");
            String neubesitzer;
            neubesitzer = user_input.readLine();

            System.out.println("Marke des Autos:");
            String neumarke;
            neumarke = user_input.readLine();

            System.out.println("Autotyp:");
            String neutyp;
            neutyp = user_input.readLine();

            int neuklasse = 0;                                        //Fahrzeugklasse
            do {
                try {
                    System.out.println("Fahrzeugklassen:");

                    stmt = con.createStatement();
                    rs = stmt.executeQuery("SELECT * FROM Klassen");

                    while (rs.next()) {
                        int klassenID = rs.getInt("KlassenID");
                        String klasse = rs.getString("Klasse");
                        System.out.println(klassenID + ": " + klasse);
                    }

                    neuklasse = Integer.parseInt(user_input.readLine());

                } catch (NumberFormatException ex) {
                    System.out.println("Keine gültige Fahrzeugklasse!");
                    System.out.println(ex.getMessage());
                }
            } while (neuklasse == 0);

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

            int neutreibstoff = 0;
            do {
                try {
                    System.out.println("Treibstoffart:");

                    stmt = con.createStatement();
                    rs = stmt.executeQuery("SELECT * FROM Kraftstoffe");

                    while (rs.next()) {
                        int kraftstoffID = rs.getInt("KraftstoffID");
                        String kraftstoff = rs.getString("Kraftstoffart");
                        System.out.println(kraftstoffID + ": " + kraftstoff);
                    }

                    neutreibstoff = Integer.parseInt(user_input.readLine());

                } catch (NumberFormatException ex) {
                    System.out.println("Kein gültiger Treibstoff!");
                    System.out.println(ex.getMessage());
                }
            } while (neutreibstoff == 0);

            stmt = con.createStatement();
            stmt.executeUpdate("INSERT INTO Fahrzeuge VALUES (" + id + ",'"
                    + neubesitzer + "', '" + neumarke + "', '" + neutyp + "', "
                    + neuverbrauch + ", " + neuleistung + ", "
                    + neukmstand + ", " + neutreibstoff + ", " + neuklasse + ")");

            rs.close();
            stmt.close();

            System.out.println("Eintrag erstellt!");
            anzeigenFahrzeugDatenbank(id, con, stmt);
            user_input.readLine();

        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public void bearbeitenFahrzeugDatenbank(Console user_input, int id, Connection con, Statement stmt) {

        try {
            ResultSet rs;

            System.out.println("Besitzer des Fahrzeugs:");
            String neubesitzer;
            neubesitzer = user_input.readLine();

            System.out.println("Marke des Autos:");
            String neumarke;
            neumarke = user_input.readLine();

            System.out.println("Autotyp:");
            String neutyp;
            neutyp = user_input.readLine();

            int neuklasse = 0;                                        //Fahrzeugklasse
            do {
                try {
                    System.out.println("Fahrzeugklassen:");

                    stmt = con.createStatement();
                    rs = stmt.executeQuery("SELECT * FROM Klassen");

                    while (rs.next()) {
                        int klassenID = rs.getInt("KlassenID");
                        String klasse = rs.getString("Klasse");
                        System.out.println(klassenID + ": " + klasse);
                    }

                    neuklasse = Integer.parseInt(user_input.readLine());

                } catch (NumberFormatException ex) {
                    System.out.println("Keine gültige Fahrzeugklasse!");
                    System.out.println(ex.getMessage());
                }
            } while (neuklasse == 0);

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

            int neutreibstoff = 0;
            do {
                try {
                    System.out.println("Treibstoffart:");

                    stmt = con.createStatement();
                    rs = stmt.executeQuery("SELECT * FROM Kraftstoffe");

                    while (rs.next()) {
                        int kraftstoffID = rs.getInt("KraftstoffID");
                        String kraftstoff = rs.getString("Kraftstoffart");
                        System.out.println(kraftstoffID + ": " + kraftstoff);
                    }

                    neutreibstoff = Integer.parseInt(user_input.readLine());

                } catch (NumberFormatException ex) {
                    System.out.println("Kein gültiger Treibstoff!");
                    System.out.println(ex.getMessage());
                }
            } while (neutreibstoff == 0);

            stmt = con.createStatement();
            stmt.executeUpdate("UPDATE Fahrzeuge SET Besitzer = '" + neubesitzer
                    + "', Marke = '" + neumarke + "', Typ = '" + neutyp
                    + "', Verbrauch = " + neuverbrauch + ", Leistung = " + neuleistung
                    + ", Kilometerstand = " + neukmstand + ", Kraftstoff = " + neutreibstoff
                    + ", Klasse = " + neuklasse + " WHERE FahrzeugID = " + id);

            stmt.close();

            System.out.println("Eintrag bearbeitet!");

            anzeigenFahrzeugDatenbank(id, con, stmt);

        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public void bearbeitenKraftstoffDatenbank(Console user_input, Connection con, Statement stmt) {

        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM Kraftstoffe");
            double preis;

            while (rs.next()) {
                stmt = con.createStatement();
                int id = rs.getInt(1);
                String Kraftstoff = rs.getString(2);
                System.out.println("Aktueller Preis für " + Kraftstoff + ":");
                preis = Double.parseDouble(user_input.readLine());
                stmt.executeUpdate("UPDATE Kraftstoffe SET Preis = " + preis + " WHERE KraftstoffID = " + id);
                stmt.close();
            }

            System.out.println("Preise geändert!");

        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public void anzeigenFahrzeugDatenbank(int id, Connection con, Statement stmt) {

        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM Fahrzeuge WHERE FAHRZEUGID = " + id);

            rs.next();

            String besitzer = rs.getString("Besitzer");
            String marke = rs.getString("Marke");
            String typ = rs.getString("Typ");
            double verbrauch = rs.getDouble("Verbrauch");
            int leistung = rs.getInt("Leistung");
            int kmstand = rs.getInt("Kilometerstand");
            int treibstoff = rs.getInt("Kraftstoff");
            int klasse = rs.getInt("Klasse");

            System.out.println("ID: " + id + ", Besitzer: " + besitzer + ", Fahrzeug: "
                    + marke + " " + typ + ", Klasse: " + klasse
                    + ", \nVerbrauch: " + verbrauch
                    + "l/100km, Leistung: " + leistung + "kW, Kmstand: "
                    + kmstand + "km, Treibstoff: " + treibstoff);

        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public void löschenFahrzeugDatenbank(int id, Connection con, Statement stmt) {

        try {
            stmt.executeUpdate("DELETE FROM Fahrzeuge WHERE FAHRZEUGID = " + id);
            System.out.println("Fahrzeug gelöscht!");

        } catch (SQLException e) {
            System.err.println(e);
        }
    }

}
