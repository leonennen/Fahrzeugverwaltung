package de.decoit.fahrzeugverwaltung.Eingabe.Ausgabe;

import java.sql.*;

public class Datenbank {

    public static void listeDatenbank() {
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM KFZ.FAHRZEUGE\n"
                    + "JOIN KFZ.KLASSEN ON KFZ.KLASSEN.ID = KFZ.FAHRZEUGE.KLASSEN_ID\n"
                    + "JOIN KFZ.KRAFTSTOFFE ON KFZ.KRAFTSTOFFE.ID = KFZ.FAHRZEUGE.KRAFTSTOFF_ID\n");
            while (rs.next()) {
                int id = rs.getInt("FAHRZEUG_ID");
                String besitzer = rs.getString("BESITZER");
                String marke = rs.getString("MARKE");
                String typ = rs.getString("TYP");
                String klasse = rs.getString("KLASSE");
                String kraftstoff = rs.getString("KRAFTSTOFF");

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
            ResultSet rs = stmt.executeQuery("SELECT * FROM KRAFTSTOFFE");

            while (rs.next()) {
                String art = rs.getString("KRAFTSTOFF");
                double preis = rs.getDouble("PREIS");

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
//            stmt = con.createStatement();
//            ResultSet rs = stmt.executeQuery("SELECT MAX(FAHRZEUG_ID) FROM FAHRZEUGE");
//            rs.next();
//            id = rs.getInt(1) + 1;

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
                    ResultSet rs = stmt.executeQuery("SELECT * FROM KLASSEN");

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
                    System.out.println("Kraftstoffart:");

                    stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT * FROM KRAFTSTOFFE");

                    while (rs.next()) {
                        int kraftstoffID = rs.getInt("ID");
                        String kraftstoff = rs.getString("KRAFTSTOFF");
                        System.out.println(kraftstoffID + ": " + kraftstoff);
                    }

                    neukraftstoff = Integer.parseInt(Helper.user_input.readLine());

                } catch (NumberFormatException ex) {
                    System.out.println("Kein gültiger Treibstoff!");
                    System.out.println(ex.getMessage());
                }
            } while (neukraftstoff == 0);

            stmt = con.createStatement();
            stmt.executeUpdate("INSERT INTO FAHRZEUGE (BESITZER, "
                    + "MARKE, TYP, VERBRAUCH, LEISTUNG, KILOMETERSTAND, "
                    + "KRAFTSTOFF_ID, KLASSEN_ID) VALUES ('"
                    + neubesitzer + "', '" + neumarke + "', '" + neutyp + "', "
                    + neuverbrauch + ", " + neuleistung + ", "
                    + neukmstand + ", " + neukraftstoff + ", " + neuklasse + ")");

            stmt.close();

            System.out.println("Eintrag erstellt!");
            listeDatenbank();
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

                    stmt = con.createStatement();
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

            stmt = con.createStatement();
            stmt.executeUpdate("UPDATE FAHRZEUGE SET BESITZER = '" + neubesitzer
                    + "', MARKE = '" + neumarke + "', TYP = '" + neutyp
                    + "', VERBRAUCH = " + neuverbrauch + ", LEISTUNG = " + neuleistung
                    + ", KILOMETERSTAND = " + neukmstand + ", KRAFTSTOFF_ID = " + neukraftstoff
                    + ", KLASSEN_ID = " + neuklasse + " WHERE FAHRZEUG_ID = " + id);

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
            ResultSet rs = stmt.executeQuery("SELECT * FROM KRAFTSTOFFE");
            double preis;

            while (rs.next()) {
                stmt = con.createStatement();
                int id = rs.getInt(1);
                String kraftstoff = rs.getString("KRAFTSTOFF");
                System.out.println("Aktueller Preis für " + kraftstoff + ":");
                preis = Double.parseDouble(Helper.user_input.readLine());
                stmt.executeUpdate("UPDATE KRAFTSTOFFE SET PREIS = " + preis + " WHERE ID = " + id);
                stmt.close();
            }

            System.out.println("Preise geändert!");

        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public static void anzeigenFahrzeugDatenbank(int id) {

        try {
            PreparedStatement prestmt = con.prepareStatement("SELECT * FROM KFZ.FAHRZEUGE\n"
                    + "JOIN KFZ.KLASSEN ON KFZ.KLASSEN.ID = KFZ.FAHRZEUGE.KLASSEN_ID\n"
                    + "JOIN KFZ.KRAFTSTOFFE ON KFZ.KRAFTSTOFFE.ID = KFZ.FAHRZEUGE.KRAFTSTOFF_ID\n"
                    + "WHERE FAHRZEUG_ID = ?");
            prestmt.setLong(1, id);
            ResultSet rs = prestmt.executeQuery();
            rs.next();

            String besitzer = rs.getString("BESITZER");
            String marke = rs.getString("MARKE");
            String typ = rs.getString("TYP");
            double verbrauch = rs.getDouble("VERBRAUCH");
            int leistung = rs.getInt("LEISTUNG");
            int kmstand = rs.getInt("KILOMETERSTAND");
            String kraftstoff = rs.getString("KRAFTSTOFF");
            String klasse = rs.getString("KLASSE");

            System.out.println("ID: " + id + ", Besitzer: " + besitzer + ", Fahrzeug: "
                    + marke + " " + typ + ", Klasse: " + klasse
                    + ", \nVerbrauch: " + verbrauch
                    + "l/100km, Leistung: " + leistung + "kW, Kmstand: "
                    + kmstand + "km, Antrieb: " + kraftstoff);
            stmt.close();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public static void löschenFahrzeugDatenbank(int id) {

        try {
            stmt = con.createStatement();
            stmt.executeUpdate("DELETE FROM Fahrzeuge WHERE FAHRZEUG_ID = " + id);
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
