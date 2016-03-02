package de.decoit.fahrzeugverwaltung.Eingabe.Ausgabe;

import java.sql.*;

public class Datenbank {

    public static void listeDatenbank() {

        try {
            Statement stmtliste = con.createStatement();
            ResultSet rs = stmtliste.executeQuery("SELECT * FROM KFZ.FAHRZEUGE\n"
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
            stmtliste.close();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

//    public static void listeDatenbank2() {
//
//        ArrayList<Fahrzeug> fahrzeuge = new ArrayList<>();
//        fahrzeuge = DatenbankAbfrage.abfrageFahrzeugListe();
//
//        while(Fahrzeug o : fahrzeuge){
//    }
//    }

    public static void listeKraftstoffDatenbank() {

        try {
            Statement stmtlistekraftstoff = con.createStatement();
            ResultSet rs = stmtlistekraftstoff.executeQuery("SELECT * FROM KRAFTSTOFFE");

            while (rs.next()) {
                String art = rs.getString("KRAFTSTOFF");
                double preis = rs.getDouble("PREIS");

                System.out.println(art + ": " + preis + "€");
            }

            stmtlistekraftstoff.close();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public static void neuesFahrzeugDatenbank() {

        try {

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
                    + "KRAFTSTOFF_ID, KLASSEN_ID) VALUES ('?', '?', '?', ?, ?, ?, ?, ?)");
            prestmtneu.setString(1, neubesitzer);
            prestmtneu.setString(2, neumarke);
            prestmtneu.setString(3, neutyp);
            prestmtneu.setDouble(4, neuverbrauch);
            prestmtneu.setInt(5, neuleistung);
            prestmtneu.setInt(6, neukmstand);
            prestmtneu.setInt(7, neukraftstoff);
            prestmtneu.setInt(8, neuklasse);

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

        try {
            PreparedStatement prestmtfahrzeug = con.prepareStatement("SELECT * FROM KFZ.FAHRZEUGE\n"
                    + "JOIN KFZ.KLASSEN ON KFZ.KLASSEN.ID = KFZ.FAHRZEUGE.KLASSEN_ID\n"
                    + "JOIN KFZ.KRAFTSTOFFE ON KFZ.KRAFTSTOFFE.ID = KFZ.FAHRZEUGE.KRAFTSTOFF_ID\n"
                    + "WHERE FAHRZEUG_ID = ?");
            prestmtfahrzeug.setLong(1, id);
            ResultSet fahrzeug = prestmtfahrzeug.executeQuery();
            fahrzeug.next();

            String besitzer = fahrzeug.getString("BESITZER");
            String marke = fahrzeug.getString("MARKE");
            String typ = fahrzeug.getString("TYP");
            double verbrauch = fahrzeug.getDouble("VERBRAUCH");
            int leistung = fahrzeug.getInt("LEISTUNG");
            int kmstand = fahrzeug.getInt("KILOMETERSTAND");
            String kraftstoff = fahrzeug.getString("KRAFTSTOFF");
            String klasse = fahrzeug.getString("KLASSE");

            System.out.println("ID: " + id + ", Besitzer: " + besitzer + ", Fahrzeug: "
                    + marke + " " + typ + ", Klasse: " + klasse
                    + ", \nVerbrauch: " + verbrauch
                    + "l/100km, Leistung: " + leistung + "kW, Kmstand: "
                    + kmstand + "km, Antrieb: " + kraftstoff);

            prestmtfahrzeug.close();

        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public static void löschenFahrzeugDatenbank(int id) {

        try {
            PreparedStatement prestmtloeschen = con.prepareStatement("DELETE FROM Fahrzeuge WHERE FAHRZEUG_ID = ?");
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
