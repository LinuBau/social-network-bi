package aufgabe3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.Random;

public class BenutzerBranch {

    /**
     * Fügt einen neuen Benutzer-Datensatz in die Datenbank ein.
     *
     * @param c die Datenbankverbindung
     */
    public void InsertBenutzer(Connection c) {
        ArrayList<String> primarykey = new ArrayList<String>();
        String insert = "Insert into ED_BENUTZER values (?,?,?,?,?,?,?,?,?,?,?)";
        try (BufferedReader readInput = new BufferedReader(new InputStreamReader(System.in)); Statement statement = c.createStatement(); PreparedStatement st = c.prepareStatement(insert);) {
            ResultSet rs = statement.executeQuery("SELECT Benutzer_ID FROM ED_BENUTZER");
            while (rs.next()) {
                String str = rs.getString(1);
                primarykey.add(str);
            }
            String vorname = NachnameModul(readInput);
            String nachname = VornameModul(readInput);
            String geschlecht = GeschlectModul(readInput);
            Timestamp date = DateModul(readInput);
            String email = emailModul(readInput);
            String telefon = telefonModul(readInput);
            String anschrift = anschriftModul(readInput);
            String kontoArt = kontoartModul(readInput);
            int follower = 0;
            int abonementen = 0;
            String benutzerID = CreateBenutzerID(kontoArt, primarykey);

            st.setString(1, benutzerID);
            st.setString(2, nachname);
            st.setString(3, vorname);
            st.setString(4, geschlecht);
            st.setTimestamp(5, date);
            st.setString(6, email);
            st.setString(7, telefon);
            st.setString(8, anschrift);
            st.setString(9, kontoArt);
            st.setInt(10, follower);
            st.setInt(11, abonementen);
            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Update erfolgreich.");
            } else {
                System.out.println("Update fehlgeschlagen.");
            }
            Aufgabe3.main(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Aktualisiert einen bestehenden Benutzer-Datensatz in der Datenbank.
     *
     * @param c die Datenbankverbindung
     */
    public void UpdateBenutzer(Connection c) {
        // Primerykey kann man nicht Updaten da es ist zu viele abhänigkeiten hat
        ArrayList<String> primarykeys = new ArrayList<String>();
        System.out.println("Welche Splatte wollen sie Updaten geben sie ein");
        System.out.println("1 für Nachname||2 für Vorname|| 3 für Geschlecht|| 4 für Geburtsdatum|| \n"
                + "5 füt EMail||6 für Telefonnummer||7 für Anschrift|| 8 für Kontoart||9 für Anzahl_Follower|| 10 für Anzahl_Abonnements");
        PreparedStatement st = null;
        String update = null;
        try (BufferedReader readInput = new BufferedReader(new InputStreamReader(System.in)); Statement statement = c.createStatement();) {
            ResultSet rs = statement.executeQuery("SELECT Benutzer_ID FROM ED_BENUTZER");

            while (rs.next()) {
                String str = rs.getString(1);
                primarykeys.add(str);
            }
            String input = null;
            while (input == null) {
                input = readInput.readLine();
            }
            String primarykey;
            int colum = Integer.parseInt(input.trim());
            switch (colum) {
                case 1:
                    update = "update ED_BENUTZER set Nachname = ? where Benutzer_ID = ?";
                    st = c.prepareCall(update);
                    primarykey = getPrimaryKey(primarykeys, "Verändern", readInput);
                    String nachname = NachnameModul(readInput);
                    st.setString(1, nachname);
                    st.setString(2, primarykey);
                    break;
                case 2:
                    update = "update ED_BENUTZER set Vorname = ? where Benutzer_ID = ?";
                    st = c.prepareCall(update);
                    primarykey = getPrimaryKey(primarykeys, "Verändern", readInput);
                    String vorname = VornameModul(readInput);
                    st.setString(1, vorname);
                    st.setString(2, primarykey);
                    break;
                case 3:
                    update = "update ED_BENUTZER set Geschlecht = ? where Benutzer_ID = ?";
                    st = c.prepareCall(update);
                    primarykey = getPrimaryKey(primarykeys, "Verändern", readInput);
                    String geschlecht = genderModul(readInput);
                    st.setString(1, geschlecht);
                    st.setString(2, primarykey);
                    break;
                case 4:
                    update = "update ED_BENUTZER set Geburtsdatum = ? where Benutzer_ID = ?";
                    st = c.prepareCall(update);
                    primarykey = getPrimaryKey(primarykeys, "Verändern", readInput);
                    Timestamp geburtsdatum = DateModul(readInput);
                    st.setTimestamp(1, geburtsdatum);
                    st.setString(2, primarykey);
                    break;
                case 5:
                    update = "update ED_BENUTZER set EMail = ? where Benutzer_ID = ?";
                    st = c.prepareCall(update);
                    primarykey = getPrimaryKey(primarykeys, "Verändern", readInput);
                    String email = emailModul(readInput);
                    st.setString(1, email);
                    st.setString(2, primarykey);
                    break;
                case 6:
                    update = "update ED_BENUTZER set Telefonnummer = ? where Benutzer_ID = ?";
                    st = c.prepareCall(update);
                    primarykey = getPrimaryKey(primarykeys, "Verändern", readInput);
                    String telefon = telefonModul(readInput);
                    st.setString(1, telefon);
                    st.setString(2, primarykey);
                    break;
                case 7:
                    update = "update ED_BENUTZER set Anschrift = ? where Benutzer_ID = ?";
                    st = c.prepareCall(update);
                    primarykey = getPrimaryKey(primarykeys, "Verändern", readInput);
                    String anschrift = anschriftModul(readInput);
                    st.setString(1, anschrift);
                    st.setString(2, primarykey);
                    break;
                case 8:
                    update = "update ED_BENUTZER set Kontoart = ? where Benutzer_ID = ?";
                    st = c.prepareCall(update);
                    primarykey = getPrimaryKey(primarykeys, "Verändern", readInput);
                    String kontoart = kontoartModul(readInput);
                    st.setString(1, kontoart);
                    st.setString(2, primarykey);
                    break;
                case 9:
                    update = "update ED_BENUTZER set Anzahl_Follower = ? where Benutzer_ID = ?";
                    st = c.prepareCall(update);
                    primarykey = getPrimaryKey(primarykeys, "Verändern", readInput);
                    int follower = AnzahlFollowerModul(readInput);
                    st.setInt(1, follower);
                    st.setString(2, primarykey);
                    break;
                case 10:
                    update = "update ED_BENUTZER set Anzahl_Abonnements = ? where Benutzer_ID = ?";
                    st = c.prepareCall(update);
                    primarykey = getPrimaryKey(primarykeys, "Verändern", readInput);
                    int abonnements = AnzahlAbonnementsModul(readInput);
                    st.setInt(1, abonnements);
                    st.setString(2, primarykey);
                    break;
                default:
                    System.out.println("Ungültige Spaltennummer.");
                    UpdateBenutzer(c);
            }
            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Update erfolgreich.");
            } else {
                System.out.println("Update fehlgeschlagen.");
            }
            Aufgabe3.main(null);
        } catch (Exception e) {
            System.out.println("Eingabe nicht Richtig");
            UpdateBenutzer(c);
        }

    }

    /**
     * Löscht einen Benutzer-Datensatz aus der Datenbank.
     *
     * @param c die Datenbankverbindung
     */
    public void DeleteBenutzer(Connection c) {
        ArrayList<String> primarykeys = new ArrayList<String>();
        String delete = "DELETE FROM ED_BENUTZER WHERE Benutzer_ID = ?";
        String primarykey = null;
        PreparedStatement st = null;
        try (BufferedReader readInput = new BufferedReader(new InputStreamReader(System.in)); Statement statement = c.createStatement();) {
            ResultSet rs = statement.executeQuery("SELECT Benutzer_ID FROM ED_BENUTZER");
            while (rs.next()) {
                String str = rs.getString(1);
                primarykeys.add(str);
            }
            primarykey = getPrimaryKey(primarykeys, "Löschen", readInput);
            try {
                st = c.prepareStatement("Delete from ED_INTERAKTION where Benutzer_ID =?");
                st.setString(1, primarykey);
                st.executeQuery();
            } catch (Exception e1) {
            }
            try {
                st = c.prepareStatement("DELETE FROM ED_INTERAKTION WHERE Beitrag_ID IN (SELECT Beitrag_ID FROM ED_Beitrag where Ersteller = ?)");
                st.setString(1, primarykey);
                st.executeQuery();
            } catch (Exception e1) {
            }
            try {
                st = c.prepareStatement("Delete from ED_BEITRAG where Ersteller =?");
                st.setString(1, primarykey);
                st.executeQuery();
            } catch (Exception e1) {
            }
            try {
                st = c.prepareStatement("Delete from ED_NETZWERKZUGEHOERIGKEIT where Mitglied =?");
                st.setString(1, primarykey);
                st.executeQuery();
            } catch (Exception e1) {
            }
            try {
                st = c.prepareStatement("Delete from ED_FOLLOWER where Verfolgter_Benutzer = ? ");
                st.setString(1, primarykey);
                st.executeQuery();
            } catch (Exception e1) {
            }
            try {
                st = c.prepareStatement("Delete from ED_FOLLOWER where Follower=?");
                st.setString(1, primarykey);
                st.executeQuery();
            } catch (Exception e1) {
            }
            st = c.prepareStatement(delete);
            st.setString(1, primarykey);
            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Delete erfolgreich.");
            } else {
                System.out.println("Delete fehlgeschlagen.");
            }
            Aufgabe3.main(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Fordert den Benutzer zur Eingabe eines Primary Keys auf und validiert die
     * Eingabe.
     *
     * @param l die Liste der bestehenden Primary Keys
     * @param operation die auszuführende Operation (z.B. "Verändern",
     * "Löschen")
     * @param readInput der BufferedReader zum Lesen der Benutzereingabe
     * @return der validierte Primary Key
     */
    private String getPrimaryKey(ArrayList<String> l, String Opertion, BufferedReader readInput) {
        String input = null;
        for (String string : l) {
            System.out.println(string);
        }
        System.out.println("Geben sie den Primary Key ein den sie " + Opertion + " möchten:");
        try {
            while (true) {

                input = readInput.readLine();
                if (l.indexOf(input.trim()) == -1) {
                    System.out.println("Bitte richtige Benutzer ID eingeben");
                } else {
                    return input.trim();
                }
            }
        } catch (Exception e) {
            System.out.println("Bitte richtige Benutzer ID eingeben");
            return getPrimaryKey(l, Opertion, readInput);
        }
    }

    /**
     * Fordert den Benutzer zur Eingabe des Nachnamens auf und validiert die
     * Eingabe.
     *
     * @param readInput der BufferedReader zum Lesen der Benutzereingabe
     * @return der validierte Nachname
     */
    private String NachnameModul(BufferedReader readInput) {
        String vorname = null;
        String input = null;
        System.out.println("Bitte geben sie ihren Nachnamen ein:");
        try {
            while (true) {
                input = readInput.readLine();
                if ((input = input.trim()) != null) {
                    if ((vorname = isValidNachname(input, readInput)) != null) {
                        return vorname;
                    } else {
                        System.out.println("Nachname ist nicht Valide");
                    }
                }
            }
        } catch (Exception e) {
            return NachnameModul(readInput);
        }

    }

    /**
     * Fordert den Benutzer zur Eingabe des Vornamens auf und validiert die
     * Eingabe.
     *
     * @param readInput der BufferedReader zum Lesen der Benutzereingabe
     * @return der validierte Vorname
     */
    private String VornameModul(BufferedReader readInput) {
        String input = null;
        try {
            System.out.println("Bitte geben sie ihren Vorname ein:");
            while (true) {
                input = readInput.readLine();
                if ((input = input.trim()) != null) {
                    return isValidVorname(input);
                }
            }

        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Fordert den Benutzer zur Eingabe des Geschlechts auf und validiert die
     * Eingabe.
     *
     * @param readInput der BufferedReader zum Lesen der Benutzereingabe
     * @return das validierte Geschlecht
     */
    private String GeschlectModul(BufferedReader readInput) {
        String input = null;
        String geschlecht = null;
        try {
            System.out.println("Bitte Geschlect in M für Mänlich, W für Weiblich, D für Diverse");
            while (true) {
                input = readInput.readLine();
                if ((input = input.trim()) != null) {
                    if ((geschlecht = checkGeschlecht(input)) != null) {
                        return geschlecht;
                    } else {
                        System.out.println("Geschlect wurde nicht richtig eingeben");
                    }
                }
            }
        } catch (Exception e) {
            return GeschlectModul(readInput);
        }
    }

    /**
     * Fordert den Benutzer zur Eingabe des Geburtsdatums auf und validiert die
     * Eingabe.
     *
     * @param readInput der BufferedReader zum Lesen der Benutzereingabe
     * @return das validierte Geburtsdatum als Timestamp
     */
    private Timestamp DateModul(BufferedReader readInput) {
        System.out.println("Bitte Datum eingen in Schema TT MM JJJJ ");
        String input = null;
        try {
            while (true) {
                input = readInput.readLine();
                if ((input = input.trim()) != null) {
                    Timestamp time = convertToDate(input, readInput);
                    if (time == null) {
                        System.out.println("Eingabe für Datum falsch");
                        return DateModul(readInput);
                    }
                    return time;
                }
            }
        } catch (Exception e) {
            return DateModul(readInput);
        }
    }

    /**
     * Fordert den Benutzer zur Eingabe der Telefonnummer auf und validiert die
     * Eingabe.
     *
     * @param readInput der BufferedReader zum Lesen der Benutzereingabe
     * @return die validierte Telefonnummer
     */
    private String telefonModul(BufferedReader readInput) {
        System.out.println("Bitte Telefonnummer eingeben:");
        String input = null;
        try {
            while (true) {
                input = readInput.readLine();
                if ((input = input.trim()) != null) {
                    return pruefeTelefonnummer(input);
                }
            }
        } catch (Exception e) {
            return telefonModul(readInput);
        }
    }

    /**
     * Fordert den Benutzer zur Eingabe der Anschrift auf und validiert die
     * Eingabe.
     *
     * @param readInput der BufferedReader zum Lesen der Benutzereingabe
     * @return die validierte Anschrift
     */
    private String anschriftModul(BufferedReader readInput) {
        System.out.println("Bitte Anschrift eingeben:");
        String input = null;
        try {
            while (true) {
                input = readInput.readLine();
                if ((input = input.trim()) != null) {
                    return pruefeAnschrift(input);
                }
            }
        } catch (Exception e) {
            return anschriftModul(readInput);
        }
    }

    /**
     * Fordert den Benutzer zur Eingabe der E-Mail-Adresse auf und validiert die
     * Eingabe.
     *
     * @param readInput der BufferedReader zum Lesen der Benutzereingabe
     * @return die validierte E-Mail-Adresse
     */
    private String emailModul(BufferedReader readInput) {
        System.out.println("Bitte Email eingeben:");
        String input = null;
        try {
            while (true) {
                input = readInput.readLine();
                if ((input = input.trim()) != null) {
                    if (isEmail(input)) {
                        return input;
                    } else {
                        System.out.println("Die Email ist nicht Formatiert");
                    }
                }
            }
        } catch (Exception e) {
            return emailModul(readInput);
        }
    }

    /**
     * Fordert den Benutzer zur Eingabe des Geschlechts auf und validiert die
     * Eingabe.
     *
     * @param reader der BufferedReader zum Lesen der Benutzereingabe
     * @return das validierte Geschlecht
     */
    private String genderModul(BufferedReader reader) {
        String input = null;
        String gender = null;
        try {
            System.out.println("Bitte geben Sie Ihr Geschlecht ein (M für männlich, W für weiblich, D für divers):");
            while ((input = reader.readLine()) != null) {
                input = input.trim(); // Trim whitespace
                gender = checkGeschlecht(input);
                if (gender != null) {
                    return gender;
                } else {
                    System.out.println(
                            "Ungültige Eingabe. Bitte geben Sie M für männlich, W für weiblich oder D für divers ein.");
                }
            }
        } catch (Exception e) {
            return genderModul(reader);
        }
        return null;
    }

    /**
     * Fordert den Benutzer zur Eingabe der Kontoart auf und validiert die
     * Eingabe.
     *
     * @param reader der BufferedReader zum Lesen der Benutzereingabe
     * @return die validierte Kontoart
     */
    private String kontoartModul(BufferedReader reader) {
        String input = null;
        String kontoart = null;
        try {
            System.out.println(
                    "Bitte geben Sie die Kontoart ein (A für Admin, B für Business, O für Öffentlich oder P für Private):");
            while ((input = reader.readLine()) != null) {
                input = input.trim(); // Trim whitespace
                kontoart = isKontoart(input, reader);
                if (kontoart != null) {
                    return kontoart;
                } else {
                    System.out.println("Ungültige Eingabe. Bitte geben Sie A, B, O oder P ein.");
                }
            }
        } catch (Exception e) {
            return kontoartModul(reader);
        }
        return null;
    }

    /**
     * Erstellt eine neue eindeutige Benutzer-ID.
     *
     * @param Kontoart die Kontoart des Benutzers
     * @param primarykey die Liste der bestehenden Primary Keys
     * @return die neue eindeutige Benutzer-ID
     */
    private String CreateBenutzerID(String Kontoart, ArrayList<String> primarykey) {
        String temp = "";
        for (int i = 0; i < 3; i++) {
            temp = temp + ((int) (Math.random() * 10));
        }
        String benutzerID = "B" + Kontoart + temp;
        int result = primarykey.lastIndexOf(benutzerID);
        if (result == -1) {
            System.out.println(benutzerID);
            return benutzerID;
        } else {
            return CreateBenutzerID(Kontoart, primarykey);
        }
    }

    /**
     * Konvertiert einen Datum-String in einen Timestamp.
     *
     * @param dateString der Datum-String im Format "TT MM JJJJ"
     * @param readInput der BufferedReader zum Lesen der Benutzereingabe
     * @return der konvertierte Timestamp oder null, wenn das Format ungültig
     * ist
     */
    private Timestamp convertToDate(String dateString, BufferedReader readInput) {
        try {
            String[] parts = dateString.split(" ");
            if (parts.length != 3) {
                return null;
            }

            int day = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int year = Integer.parseInt(parts[2]);

            String formattedDate = String.format("%04d-%02d-%02d", year, month, day);

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate = dateFormat.parse(formattedDate);

            return new Timestamp(parsedDate.getTime());
        } catch (Exception e) {
            System.out.println("Datum ist nicht richtig Formatiert");
            return DateModul(readInput);
        }

    }

    /**
     * Überprüft, ob die gegebene E-Mail-Adresse gültig ist.
     *
     * @param input die zu überprüfende E-Mail-Adresse
     * @return true, wenn die E-Mail-Adresse gültig ist, andernfalls false
     */
    private boolean isEmail(String input) {
        if (input.length() > 50) {
            return false;
        }
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    /**
     * Überprüft das Geschlecht und gibt den validierten Wert zurück.
     *
     * @param geschlecht das zu überprüfende Geschlecht
     * @return das validierte Geschlecht oder null, wenn ungültig
     */
    private String checkGeschlecht(String geschlecht) {
        if (geschlecht.length() > 1 || geschlecht.length() == 0) {
            return null;
        }
        if (geschlecht.equalsIgnoreCase("M")) {
            return "M";
        }
        if (geschlecht.equalsIgnoreCase("W")) {
            return "W";
        }
        if (geschlecht.equalsIgnoreCase("D")) {
            return "D";
        }
        return null;
    }

    /**
     * Überprüft die Kontoart und gibt den validierten Wert zurück.
     *
     * @param Kontoart die zu überprüfende Kontoart
     * @param readInput der BufferedReader zum Lesen der Benutzereingabe
     * @return die validierte Kontoart oder null, wenn ungültig
     */
    private String isKontoart(String Kontoart, BufferedReader readInput) {
        if (Kontoart.length() > 1) {
            return null;
        }
        if (Kontoart.equalsIgnoreCase("A")) {
            return "A";
        }
        if (Kontoart.equalsIgnoreCase("B")) {
            return "B";
        }
        if (Kontoart.equalsIgnoreCase("O")) {
            return "O";
        }
        if (Kontoart.equalsIgnoreCase("P")) {
            return "P";
        }
        return kontoartModul(null);
    }

    /**
     * Überprüft den Nachnamen und gibt den validierten Wert zurück.
     *
     * @param nachname der zu überprüfende Nachname
     * @param readInput der BufferedReader zum Lesen der Benutzereingabe
     * @return der validierte Nachname oder null, wenn ungültig
     */
    private String isValidNachname(String nachname, BufferedReader readInput) {
        nachname = nachname.trim();
        if (nachname.length() <= 50 && nachname.length() > 1) {
            return nachname;
        } else {
            return VornameModul(readInput);
        }
    }

    /**
     * Überprüft den Vornamen und gibt den validierten Wert zurück.
     *
     * @param vorname der zu überprüfende Vorname
     * @return der validierte Vorname oder null, wenn ungültig
     */
    private String isValidVorname(String vorname) {
        vorname = vorname.trim();
        if (vorname.length() <= 50 && vorname.length() > 1) {
            return vorname;
        } else {
            return null;
        }
    }

    /**
     * Überprüft die Anschrift und gibt den validierten Wert zurück.
     *
     * @param anschrift die zu überprüfende Anschrift
     * @return die validierte Anschrift oder null, wenn ungültig
     */

    private static String pruefeAnschrift(String anschrift) {
        if (anschrift == null || anschrift.isEmpty()) {
            return null;
        }
        if (anschrift.length() > 50) {
            return null;
        }
        return anschrift;
    }

    /**
     * Überprüft die Telefonnummer und gibt den validierten Wert zurück.
     *
     * @param telefonnummer die zu überprüfende Telefonnummer
     * @return die validierte Telefonnummer oder null, wenn ungültig
     */
    private static String pruefeTelefonnummer(String telefonnummer) {
        if (telefonnummer == null || telefonnummer.isEmpty()) {
            return null;
        }
        if (telefonnummer.length() > 20) {
            return null;
        }
        return telefonnummer;
    }

    /**
     * Fordert den Benutzer zur Eingabe der neuen Anzahl der Follower auf und
     * validiert die Eingabe.
     *
     * @param readInput der BufferedReader zum Lesen der Benutzereingabe
     * @return die validierte Anzahl der Follower
     */
    private int AnzahlFollowerModul(BufferedReader readInput) {
        try {
            System.out.println("Bitte geben Sie die neue Anzahl der Follower ein:");
            String input;
            while (true) {
                input = readInput.readLine();
                if ((input = input.trim()) != null) {
                    return isValidAnzahl(input, readInput);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * Fordert den Benutzer zur Eingabe der neuen Anzahl der Abonnements auf und
     * validiert die Eingabe.
     *
     * @param readInput der BufferedReader zum Lesen der Benutzereingabe
     * @return die validierte Anzahl der Abonnements
     */
    private int AnzahlAbonnementsModul(BufferedReader readInput) {
        try {
            System.out.println("Bitte geben Sie die neue Anzahl der Abonnements ein:");
            String input;
            while (true) {
                input = readInput.readLine();
                if ((input = input.trim()) != null) {
                    return isValidAnzahl(input, readInput);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * Überprüft die Anzahl der Follower oder Abonnements und gibt den
     * validierten Wert zurück.
     *
     * @param input die zu überprüfende Anzahl als String
     * @param ri der BufferedReader zum Lesen der Benutzereingabe
     * @return die validierte Anzahl oder 0, wenn ungültig
     */
    private int isValidAnzahl(String input, BufferedReader ri) {
        try {
            int anzahl = Integer.parseInt(input);
            if (anzahl >= 0) {
                return anzahl; // Die Anzahl ist gültig
            } else {
                System.out
                        .println("Die Anzahl muss größer oder gleich 0 sein. Bitte geben Sie eine gültige Anzahl ein.");
                return AnzahlFollowerModul(ri); // Wiederholen Sie die Eingabe der Anzahl
            }
        } catch (NumberFormatException e) {
            System.out.println("Ungültige Eingabe. Bitte geben Sie eine ganze Zahl ein.");
            return AnzahlFollowerModul(ri); // Wiederholen Sie die Eingabe der Anzahl
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
