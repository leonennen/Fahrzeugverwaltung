package de.decoit.fahrzeugverwaltung.Eingabe.Ausgabe;

import java.sql.*;

public class Datenbank {

    public static void listeDatenbank() {
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Fahrzeuge\n"
                    + "JOIN KFZ.KLASSEN ON KFZ.KLASSEN.KLASSENID = KFZ.FAHRZEUGE.KLASSE\n"
                    + "JOIN KFZ.KRAFTSTOFFE ON KFZ.KRAFTSTOFFE.KRAFTSTOFFID = KFZ.FAHRZEUGE.KRAFTSTOFF");
            while (rs.next()) {
                int id = rs.getInt("FahrzeugID");
                String besitzer = rs.getString("Besitzer");
                String marke = rs.getString("Marke");
                String typ = rs.getString("Typ");
                String klasse = rs.getString("Klasse");
                String kraftstoff = rs.getString("Kraftstoffart");

                System.out.println("ID: " + id + ", Besitzer: " + besitzer + ", Fahrzeug: "
                        + marke + " " + typ + ", " + klasse + ", Antrieb: " + kraftstoff);
            }
            stmt.close();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public static void listeKraftstoffDatenbank() {

        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Kraftstoffe");

            while (rs.next()) {
                String art = rs.getString("Kraftstoffart");
                double preis = rs.getDouble("Preis");

                System.out.println(art + ": " + preis + "€");
            }

            stmt.close();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public static void neuesFahrzeugDatenbank() {

        int id;
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT MAX(FAHRZEUGID) FROM Fahrzeuge");
            rs.next();
            id = rs.getInt(1) + 1;

            System.out.println("Besitzer des Fahrzeugs:");
            String neubesitzer;
            neubesitzer = Helper.user_input.readLine();

            System.out.println("Marke des Autos:");
            String neumarke;
            neumarke = Helper.user_input.readLine();

            System.out.println("Autotyp:");
            String neutyp;
            neutyp = Helper.user_input.readLine();

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

                    neutreibstoff = Integer.parseInt(Helper.user_input.readLine());

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

            stmt.close();

            System.out.println("Eintrag erstellt!");
            anzeigenFahrzeugDatenbank(id);
            Helper.user_input.readLine();

        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public static void bearbeitenFahrzeugDatenbank(int id) {

        try {
            ResultSet rs;

            System.out.println("Besitzer des Fahrzeugs:");
            String neubesitzer;
            neubesitzer = Helper.user_input.readLine();

            System.out.println("Marke des Autos:");
            String neumarke;
            neumarke = Helper.user_input.readLine();

            System.out.println("Autotyp:");
            String neutyp;
            neutyp = Helper.user_input.readLine();

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

                    neutreibstoff = Integer.parseInt(Helper.user_input.readLine());

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

            anzeigenFahrzeugDatenbank(id);

        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public static void bearbeitenKraftstoffDatenbank() {

        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Kraftstoffe");
            double preis;

            while (rs.next()) {
                stmt = con.createStatement();
                int id = rs.getInt(1);
                String Kraftstoff = rs.getString(2);
                System.out.println("Aktueller Preis für " + Kraftstoff + ":");
                preis = Double.parseDouble(Helper.user_input.readLine());
                stmt.executeUpdate("UPDATE Kraftstoffe SET Preis = " + preis + " WHERE KraftstoffID = " + id);
                stmt.close();
            }

            System.out.println("Preise geändert!");

        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public static void anzeigenFahrzeugDatenbank(int id) {

        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Fahrzeuge\n"
                    + "JOIN KFZ.KLASSEN ON KFZ.KLASSEN.KLASSENID = KFZ.FAHRZEUGE.KLASSE\n"
                    + "JOIN KFZ.KRAFTSTOFFE ON KFZ.KRAFTSTOFFE.KRAFTSTOFFID = KFZ.FAHRZEUGE.KRAFTSTOFF\n"
                    + "WHERE FAHRZEUGID = " + id);

            rs.next();

            String besitzer = rs.getString("Besitzer");
            String marke = rs.getString("Marke");
            String typ = rs.getString("Typ");
            double verbrauch = rs.getDouble("Verbrauch");
            int leistung = rs.getInt("Leistung");
            int kmstand = rs.getInt("Kilometerstand");
            String treibstoff = rs.getString("Kraftstoffart");
            int klasse = rs.getInt("Klasse");

            System.out.println("ID: " + id + ", Besitzer: " + besitzer + ", Fahrzeug: "
                    + marke + " " + typ + ", Klasse: " + klasse
                    + ", \nVerbrauch: " + verbrauch
                    + "l/100km, Leistung: " + leistung + "kW, Kmstand: "
                    + kmstand + "km, Treibstoff: " + treibstoff);
            stmt.close();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public static void löschenFahrzeugDatenbank(int id) {

        try {
            stmt = con.createStatement();
            stmt.executeUpdate("DELETE FROM Fahrzeuge WHERE FAHRZEUGID = " + id);
            System.out.println("Fahrzeug gelöscht!");
            stmt.close();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public static Connection con;
    public static Statement stmt;

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
