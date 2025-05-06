package aufgabe3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

public class NetzwerkBranch {

    /**
     * Fügt einen neuen Netzwerk-Datensatz in die Datenbank ein.
     *
     * @param c die Datenbankverbindung
     */
    public void NetzwerkInsert(Connection c) {
        String insert = "INSERT INTO ED_NETZWERK VALUES (?,?, ?, ?)";
        String selcet = "select Netzwerk_ID	from ED_NETZWERK ";
        ArrayList<String> primarykeys = new ArrayList<>();
        try (BufferedReader readInput = new BufferedReader(new InputStreamReader(System.in)); PreparedStatement st = c.prepareStatement(insert); Statement statement = c.createStatement(); ResultSet rs = statement.executeQuery(selcet)) {
            while (rs.next()) {
                primarykeys.add(rs.getString(1));
            }
            String primarykey = CreateNetzwerkID(primarykeys);
            String netzwerkName = NetzwerkNamenModul(readInput);
            String netzwerkArt = NetzwerkArtModul(readInput);
            String netzwerkBeschreibung = NetzwerkBeschreibungModul(readInput);
            st.setString(1, primarykey);
            st.setString(2, netzwerkName);
            st.setString(3, netzwerkArt);
            st.setString(4, netzwerkBeschreibung);
            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Insert erfolgreich.");
            } else {
                System.out.println("Insert fehlgeschlagen.");
            }
            Aufgabe3.main(null);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    /**
     * Aktualisiert einen bestehenden Netzwerk-Datensatz in der Datenbank.
     *
     * @param c die Datenbankverbindung
     */
    public void NetzwerkUpdate(Connection c) {
        String update = null;
        String selcet = "select Netzwerk_ID	from ED_NETZWERK ";
        String input = null;
        String key = null;
        ArrayList<String> primarykeys = new ArrayList<>();
        try (BufferedReader readInput = new BufferedReader(new InputStreamReader(System.in)); Statement statement = c.createStatement(); ResultSet rs = statement.executeQuery(selcet)) {
            while (rs.next()) {
                primarykeys.add(rs.getString(1));
            }
            PreparedStatement st = null;
            System.out.println("Welche Splatte wollen sie Updaten geben sie ein");
            System.out.println("1 für Netzwerk Name || 2 für Netzwerk Art || 3 für Beschreibung ");
            while (input == null) {
                input = readInput.readLine();
            }
            int colum = Integer.parseInt(input.trim());
            switch (colum) {
                case 1:
                    update = "update ED_NETZWERK set Netzwerk_Name = ? where Netzwerk_ID = ? ";
                    st = c.prepareStatement(update);
                    key = getNetzwerk(primarykeys, "Updaten", readInput, false);
                    String netzwerkName = NetzwerkNamenModul(readInput);
                    st.setString(1, netzwerkName);
                    st.setString(2, key);
                    break;
                case 2:
                    update = "update ED_NETZWERK set N_ART = ? where Netzwerk_ID = ? ";
                    st = c.prepareStatement(update);
                    key = getNetzwerk(primarykeys, "Updaten", readInput, false);
                    String netzwerkArt = NetzwerkArtModul(readInput);
                    st.setString(1, netzwerkArt);
                    st.setString(2, key);
                    break;
                case 3:
                    update = "update ED_NETZWERK set Netzwerk_Beschreibung = ? where Netzwerk_ID = ? ";
                    st = c.prepareStatement(update);
                    key = getNetzwerk(primarykeys, "Updaten", readInput, false);
                    String netzwerkBeschreibung = NetzwerkBeschreibungModul(readInput);
                    st.setString(1, netzwerkBeschreibung);
                    st.setString(2, key);
                default:
                    System.out.println("Ungültige Spaltennummer.");
                    NetzwerkUpdate(c);
                    break;
            }
            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Update erfolgreich.");
            } else {
                System.out.println("Update fehlgeschlagen.");
            }
            Aufgabe3.main(null);
        } catch (Exception e) {

        }

    }

    /**
     * Löscht einen Netzwerk-Datensatz aus der Datenbank.
     *
     * @param c die Datenbankverbindung
     */
    public void NetzwerkDelete(Connection c) {
        String delete = "DELETE FROM ED_NETZWERK  where Netzwerk_ID = ?";
        String zugehoerigkeitdelete = "DELETE FROM ED_NETZWERKZUGEHOERIGKEIT where Netzwerk_ID = ? ";
        String selcet = "select Netzwerk_ID from ED_NETZWERK ";
        String key = null;
        PreparedStatement st = null;
        ArrayList<String> primarykeys = new ArrayList<>();
        try (BufferedReader readInput = new BufferedReader(new InputStreamReader(System.in)); Statement statement = c.createStatement(); ResultSet rs = statement.executeQuery(selcet)) {
            while (rs.next()) {
                primarykeys.add(rs.getString(1));
            }
            key = getNetzwerk(primarykeys, "Löschen", readInput, false);
            try {
                st = c.prepareStatement(zugehoerigkeitdelete);
                st.setString(1, key);
                st.executeQuery();
            } catch (Exception e1) {
            }
            st = c.prepareStatement(delete);
            st.setString(1, key);
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
     * Fordert den Benutzer zur Eingabe einer Netzwerk-ID auf und validiert die
     * Eingabe.
     *
     * @param l die Liste der bestehenden Netzwerk-IDs
     * @param operation die auszuführende Operation (z.B. "Updaten", "Löschen")
     * @param readInput der BufferedReader zum Lesen der Benutzereingabe
     * @param isNeuerwert ob die Operation für einen neuen Wert ist
     * @return die validierte Netzwerk-ID
     */
    private String getNetzwerk(ArrayList<String> l, String Opertion, BufferedReader readInput, boolean isNeuerwert) {
        if (isNeuerwert) {
            System.out.println("Geben sie den die neu Benutzer ID die ein:");
        } else {
            System.out.println("Geben sie den Netzwerk ID ein den sie " + Opertion + " möchten");
        }
        String input = null;
        for (String string : l) {
            System.out.println(string);
        }
        try {
            while (true) {
                input = readInput.readLine();
                if (l.lastIndexOf(input.trim()) == -1) {
                    System.out.println("Bitte richtige Netzwerk ID eingeben");
                } else {
                    return input.trim();
                }
            }
        } catch (Exception e) {
            System.out.println("Bitte richtige Netzwerk ID eingeben");
            return getNetzwerk(l, Opertion, readInput, isNeuerwert);
        }
    }

    /**
     * Fordert den Benutzer zur Eingabe der Netzwerk-Art auf und validiert die
     * Eingabe.
     *
     * @param reader der BufferedReader zum Lesen der Benutzereingabe
     * @return die validierte Netzwerk-Art
     */
    private String NetzwerkArtModul(BufferedReader reader) {
        String input = null;
        String kontoart = null;
        try {
            System.out.println(
                    "Bitte geben Sie die Netzwerk Art ein (OG für Offene Gruppe, GG für Geschlossene Gruppe oder  PG für Private Gruppe ):");
            while ((input = reader.readLine()) != null) {
                input = input.trim(); // Trim whitespace
                kontoart = isNeztwetzwerkArt(input);
                if (kontoart != null) {
                    return kontoart;
                } else {
                    System.out.println("Ungültige Eingabe. Bitte geben Sie OG, GG oder PG ein.");
                }
            }
        } catch (Exception e) {
            return NetzwerkArtModul(reader);
        }
        return null;
    }

    /**
     * Fordert den Benutzer zur Eingabe des Netzwerk-Namens auf und validiert
     * die Eingabe.
     *
     * @param readInput der BufferedReader zum Lesen der Benutzereingabe
     * @return der validierte Netzwerk-Name
     */
    private String NetzwerkNamenModul(BufferedReader readInput) {
        String input = null;
        try {
            System.out.println("Bitte geben sie ihren Netzwerk Namen ein:");
            while (true) {
                input = readInput.readLine();
                if ((input = input.trim()) != null) {
                    return isValidNetzwerkName(input, readInput);
                }
            }

        } catch (Exception e) {
            return NetzwerkNamenModul(readInput);
        }
    }

    /**
     * Fordert den Benutzer zur Eingabe der Netzwerk-Beschreibung auf und
     * validiert die Eingabe.
     *
     * @param readInput der BufferedReader zum Lesen der Benutzereingabe
     * @return die validierte Netzwerk-Beschreibung
     */
    private String NetzwerkBeschreibungModul(BufferedReader readInput) {
        String input = null;
        try {
            System.out.println("Bitte geben sie ihren Netzwerk Beschreibung ein:");
            while (true) {
                input = readInput.readLine();
                if ((input = input.trim()) != null) {
                    return isValidNetzwerkBeschreibung(input, readInput);
                }
            }

        } catch (Exception e) {
            return NetzwerkBeschreibungModul(readInput);
        }
    }

    /**
     * Erstellt eine neue eindeutige Netzwerk-ID.
     *
     * @param l die Liste der bestehenden Netzwerk-IDs
     * @return die neue eindeutige Netzwerk-ID
     */
    private String CreateNetzwerkID(ArrayList l) {
        Random r = new Random();
        int random = r.nextInt(8998) + 1001;
        String newkey = "N" + random;
        if (l.lastIndexOf(newkey) == -1) {
            return newkey;
        } else {
            return CreateNetzwerkID(l);
        }

    }

    /**
     * Validiert den Netzwerk-Namen.
     *
     * @param nachname der zu validierende Netzwerk-Name
     * @param readInput der BufferedReader zum Lesen der Benutzereingabe
     * @return der validierte Netzwerk-Name
     */
    private String isValidNetzwerkName(String nachname, BufferedReader readInput) {
        nachname = nachname.trim();
        if (nachname.length() <= 50 && nachname.length() > 1) {
            return nachname;
        } else {
            System.out.println("Nicht gültiger Netzwerk Namen");
            return NetzwerkNamenModul(readInput);
        }
    }

    /**
     * Validiert die Netzwerk-Beschreibung.
     *
     * @param nachname die zu validierende Netzwerk-Beschreibung
     * @param readInput der BufferedReader zum Lesen der Benutzereingabe
     * @return die validierte Netzwerk-Beschreibung
     */
    private String isValidNetzwerkBeschreibung(String nachname, BufferedReader readInput) {
        nachname = nachname.trim();
        if (nachname.length() <= 100 && nachname.length() > 1) {
            return nachname;
        } else {
            System.out.println("Nicht gültiger Netzwerk Namen");
            return NetzwerkBeschreibungModul(readInput);
        }
    }

    /**
     * Validiert die Netzwerk-Art.
     *
     * @param Kontoart die zu validierende Netzwerk-Art
     * @return die validierte Netzwerk-Art oder null, wenn ungültig
     */
    private String isNeztwetzwerkArt(String Kontoart) {
        if (Kontoart.length() > 2 && Kontoart.length() < 2) {
            System.out.println("Falsche Eingabe bei Kontoart");
            return null;
        }
        if (Kontoart.equalsIgnoreCase("GG")) {
            return "GG";
        }
        if (Kontoart.equalsIgnoreCase("OG")) {
            return "OG";
        }
        if (Kontoart.equalsIgnoreCase("PG")) {
            return "PG";
        }
        System.out.println("Falsche Eingabe bei Kontoart");
        return null;
    }
}
