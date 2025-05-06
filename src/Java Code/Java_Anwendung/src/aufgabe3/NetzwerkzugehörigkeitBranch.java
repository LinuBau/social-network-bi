package aufgabe3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class NetzwerkzugehörigkeitBranch {

    /**
     * Aktualisiert den Eintrag in der Tabelle "ED_NETZWERKZUGEHOERIGKEIT"
     * basierend auf Benutzer- und Netzwerk-ID.
     *
     * @param c Die Verbindungsinstanz zur Datenbank.
     */
    public void NetzwerkzugehörigkeitUpdate(Connection c) {
        String update = "update ED_NETZWERKZUGEHOERIGKEIT set ? = ? where Netzwerk_ID = ?,Mitglied =?";
        String query = "select  * from ED_NETZWERKZUGEHOERIGKEIT";
        String queryBenutzerId = "SELECT Benutzer_ID FROM ED_BENUTZER";
        String queryNetwerkId = "SELECT Netzwerk_ID FROM ED_NETZWERK";
        ArrayList<String> benutzerIdPrimarykey = new ArrayList<>();
        ArrayList<String> netwerkIdPrimarykey = new ArrayList<>();
        ArrayList<String> benutzerId = new ArrayList<>();
        ArrayList<String> netzwerkId = new ArrayList<>();
        try (BufferedReader readInput = new BufferedReader(new InputStreamReader(System.in)); PreparedStatement st = c.prepareStatement(update); Statement statement = c.createStatement();) {
            ResultSet rs = statement.executeQuery(query);
            System.out.println("Welche Splatte wollen sie Updaten geben sie ein");
            System.out.println("1 für Buntzer ID || 2 für Netzwerk ID");
            String input = null;
            while (input == null) {
                input = readInput.readLine();
            }
            int colum = Integer.parseInt(input.trim());
            while (rs.next()) {
                benutzerIdPrimarykey.add(rs.getString(1));
                netwerkIdPrimarykey.add(rs.getString(2));
            }
            switch (colum) {
                case 1:
                    ResultSet resultSet = statement.executeQuery(queryBenutzerId);
                    while (resultSet.next()) {
                        benutzerId.add(resultSet.getString(1));
                    }
                    String netzId = getNetzwerk(netwerkIdPrimarykey, "Updaten", readInput, false);
                    String primaryKey = getBenutzerId(benutzerIdPrimarykey, "Updaten", readInput, false);
                    String Id = getBenutzerId(benutzerId, "Einfügen", readInput, true);
                    st.setString(1, "Mitglied");
                    st.setString(2, Id);
                    st.setString(3, primaryKey);
                    st.setString(4, netzId);
                    break;
                case 2:
                    ResultSet resultSet2 = statement.executeQuery(queryNetwerkId);
                    while (resultSet2.next()) {
                        netzwerkId.add(resultSet2.getString(1));
                    }
                    String NetzwerkId = getNetzwerk(netwerkIdPrimarykey, "Updaten", readInput, false);
                    String primarykey = getBenutzerId(benutzerIdPrimarykey, "Updaten", readInput, false);
                    String netzWerkId = getNetzwerk(netzwerkId, "Einfügen", readInput, true);
                    st.setString(1, "Netzwerk_ID");
                    st.setString(2, NetzwerkId);
                    st.setString(3, primarykey);
                    st.setString(4, netzWerkId);
                default:
                    System.out.println("Ungültige Spaltennummer.");
                    NetzwerkzugehörigkeitUpdate(c);
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
            e.printStackTrace();
        }
    }

    /**
     * Fügt einen neuen Eintrag zur Tabelle "ED_NETZWERKZUGEHOERIGKEIT" hinzu.
     *
     * @param c Die Verbindungsinstanz zur Datenbank.
     */
    public void NetzwerkzugehörigkeitInsert(Connection c) {
        String insert = "INSERT INTO ED_NETZWERKZUGEHOERIGKEIT VALUES (?,?)";
        String queryBenutzerId = "SELECT Benutzer_ID FROM ED_BENUTZER";
        String queryNetwerkId = "SELECT Netzwerk_ID FROM ED_NETZWERK";
        ArrayList<String> benutzerIdPrimarykey = new ArrayList<>();
        ArrayList<String> netwerkIdPrimarykey = new ArrayList<>();
        ArrayList<String> benutzerId = new ArrayList<>();
        ArrayList<String> netzwerkId = new ArrayList<>();
        try (BufferedReader readInput = new BufferedReader(new InputStreamReader(System.in)); PreparedStatement st = c.prepareStatement(insert); Statement statement = c.createStatement();) {
            ResultSet resultSet = statement.executeQuery(queryBenutzerId);
            while (resultSet.next()) {
                benutzerId.add(resultSet.getString(1));
            }
            ResultSet resultSet2 = statement.executeQuery(queryNetwerkId);
            while (resultSet2.next()) {
                netzwerkId.add(resultSet2.getString(1));
            }
            String benutzer = getBenutzerId(benutzerId, "", readInput, true);
            String netzwerk = getNetzwerk(netzwerkId, "", readInput, true);
            st.setString(1, netzwerk);
            st.setString(2, benutzer);
            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Insert erfolgreich.");
            } else {
                System.out.println("Insert fehlgeschlagen.");
            }
            Aufgabe3.main(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Löscht einen Eintrag aus der Tabelle "ED_NETZWERKZUGEHOERIGKEIT"
     * basierend auf Benutzer- und Netzwerk-ID.
     *
     * @param c Die Verbindungsinstanz zur Datenbank.
     */
    public void NetzwerkzugehörigkeitDelete(Connection c) {
        String delete = "DELETE FROM ED_NETZWERKZUGEHOERIGKEIT where Netzwerk_ID = ? and Mitglied =?";
        String query = "select  * from ED_NETZWERKZUGEHOERIGKEIT";
        ArrayList<String> benutzerIdPrimarykey = new ArrayList<>();
        ArrayList<String> netwerkIdPrimarykey = new ArrayList<>();
        try (BufferedReader readInput = new BufferedReader(new InputStreamReader(System.in)); PreparedStatement st = c.prepareStatement(delete); Statement statement = c.createStatement();) {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                benutzerIdPrimarykey.add(rs.getString(1));
                netwerkIdPrimarykey.add(rs.getString(2));
            }
            String benutzer = getBenutzerId(benutzerIdPrimarykey, "Löschen", readInput, false);
            String netzwerk = getNetzwerk(netwerkIdPrimarykey, "Löschen", readInput, false);
            st.setString(1, benutzer);
            st.setString(2, netzwerk);
            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Delte  erfolgreich.");
            } else {
                System.out.println("Delte fehlgeschlagen.");
            }
            Aufgabe3.main(null);
        } catch (Exception e) {

        }
    }

    /**
     * Hilfsmethode zum Abrufen einer Netzwerk-ID basierend auf der übergebenen
     * Liste und Operation.
     *
     * @param l Die Liste der verfügbaren Netzwerk-IDs.
     * @param Operation Die Operation, die durchgeführt wird (Aktualisieren,
     * Einfügen, Löschen).
     * @param readInput Der BufferedReader zum Lesen von Benutzereingaben.
     * @param isNeuerwert Ein boolescher Wert, der angibt, ob ein neuer Wert
     * eingegeben wird.
     * @return Die ausgewählte Netzwerk-ID.
     */
    private String getNetzwerk(ArrayList<String> l, String Opertion, BufferedReader readInput, boolean isNeuerwert) {
        String input = null;
        for (String string : l) {
            System.out.println(string);
        }
        if (isNeuerwert) {
            System.out.println("Geben sie den die neu Netzwerk ID die ein:");
        } else {
            System.out.println("Geben sie den Netzwerk ID ein den sie " + Opertion + " möchten");
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
     * Hilfsmethode zum Abrufen einer Benutzer-ID basierend auf der übergebenen
     * Liste und Operation.
     *
     * @param l Die Liste der verfügbaren Benutzer-IDs.
     * @param Operation Die Operation, die durchgeführt wird (Aktualisieren,
     * Einfügen, Löschen).
     * @param readInput Der BufferedReader zum Lesen von Benutzereingaben.
     * @param isNeuerwert Ein boolescher Wert, der angibt, ob ein neuer Wert
     * eingegeben wird.
     * @return Die ausgewählte Benutzer-ID.
     */
    private String getBenutzerId(ArrayList<String> l, String Opertion, BufferedReader readInput, boolean isNeuerwert) {
        String input = null;
        for (String string : l) {
            System.out.println(string);
        }
        if (isNeuerwert) {
            System.out.println("Geben sie den die neu Benutzer ID die ein:");
        } else {
            System.out.println("Geben sie den Benutzer ID ein den sie " + Opertion + " möchten");
        }
        try {
            while (true) {
                input = readInput.readLine();
                if (l.lastIndexOf(input.trim()) == -1) {
                    System.out.println("Bitte richtige Benutzer ID eingeben");
                } else {
                    return input.trim();
                }
            }
        } catch (Exception e) {
            System.out.println("Bitte richtige Benutzer ID eingeben");
            return getNetzwerk(l, Opertion, readInput, isNeuerwert);
        }
    }
}
