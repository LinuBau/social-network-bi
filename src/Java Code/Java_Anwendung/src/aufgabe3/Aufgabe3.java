package aufgabe3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;

public class Aufgabe3 {

    private static boolean isfrist = true;

    /**
     * Diese Methode ist der Einstiegspunkt der Anwendung. Sie liest die
     * Verbindungsdaten zur Datenbank ein, ermöglicht dem Benutzer die Auswahl
     * eines Anwendungsfalls (Benutzer, Netzwerk oder Netzwerkzugehörigkeit) und
     * ruft die entsprechende Methode auf, um die gewünschte Operation auf der
     * Datenbank durchzuführen.
     *
     * @param args Die Befehlszeilenargumente (nicht verwendet).
     */
    public static void main(String[] args) {
        String url = "your-url";
        String login = "";
        String password = "";
        String database = "your-database";
        String input = null;
        try (BufferedReader readInput = new BufferedReader(new InputStreamReader(System.in));) {
            if (isfrist) {
                System.out.println("Bitte geben sie Ihren Anmeldungsname ein: ");
                while (input == null) {
                    input = readInput.readLine();
                }
                login = input.trim();
                input = null;
                System.out.println("Bitte geben sie ihr Password ein:");
                while (input == null) {
                    input = readInput.readLine();
                }
                password = input.trim();
                input = null;
                System.out.println("Bitte geben sie Databank auf dem sie die Operationen ausführen:");
                while (input == null) {
                    input = readInput.readLine();
                }
                database = input.trim();
                isfrist = false;
            }

            input = null;
            System.out.println("Drucke 1 für Benutzer ||2 für Netzwerk || 3 für NetzwerkZugehörigkeit ");

            while (input == null) {
                input = readInput.readLine();
            }
            int useCase = Integer.parseInt(input.trim());
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
            DriverManager.setLoginTimeout(10);
            Connection c = DriverManager.getConnection(url, login, password);
            c.setCatalog(database);
            Aufgabe3 ag = new Aufgabe3();
            switch (useCase) {
                case 1:
                    ag.useBenutzerBranch(c);
                    break;
                case 2:
                    ag.useNetzwerkBranch(c);
                    break;
                case 3:
                    System.out.println("X");
                    ag.useNetzwerkZugehörigkeitBranch(c);
                    break;
                default:
                    System.out.println("Flascher Input");
                    main(args);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Diese Methode behandelt die Benutzereingaben und ruft die entsprechenden
     * Methoden in der Klasse BenutzerBranch auf, um Operationen auf der
     * Benutzerdatenbank durchzuführen (Einfügen, Aktualisieren, Löschen).
     *
     * @param c Die Verbindungsinstanz zur Datenbank.
     */
    private void useBenutzerBranch(Connection c) {
        try (BufferedReader readInput = new BufferedReader(new InputStreamReader(System.in))) {
            String input = null;
            System.out.println("Gebe ein 1 ein für Einfügen || 2 für Updaten || 3 für Löschen ");
            while (input == null) {
                input = readInput.readLine();
            }
            int useCase = Integer.parseInt(input.trim());
            BenutzerBranch bb = new BenutzerBranch();
            switch (useCase) {
                case 1:
                    bb.InsertBenutzer(c);
                    break;
                case 2:
                    bb.UpdateBenutzer(c);
                    break;
                case 3:
                    bb.DeleteBenutzer(c);
                    break;
                default:
                    System.out.println("Flascher Input");
                    useBenutzerBranch(c);
                    break;
            }

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    /**
     * Diese Methode behandelt die Benutzereingaben und ruft die entsprechenden
     * Methoden in der Klasse NetzwerkBranch auf, um Operationen auf der
     * Netzwerkdatenbank durchzuführen (Einfügen, Aktualisieren, Löschen).
     *
     * @param c Die Verbindungsinstanz zur Datenbank.
     */
    private void useNetzwerkBranch(Connection c) {
        try (BufferedReader readInput = new BufferedReader(new InputStreamReader(System.in))) {
            String input = null;
            System.out.println("Gebe ein 1 ein für Einfügen || 2 für Updaten || 3 für Löschen ");
            while (input == null) {
                input = readInput.readLine();
            }
            int useCase = Integer.parseInt(input.trim());
            NetzwerkBranch nb = new NetzwerkBranch();
            switch (useCase) {
                case 1:
                    nb.NetzwerkInsert(c);
                    break;
                case 2:
                    nb.NetzwerkUpdate(c);
                    break;
                case 3:
                    nb.NetzwerkDelete(c);
                    break;
                default:
                    System.out.println("Flascher Input");
                    useNetzwerkBranch(c);
                    break;
            }

        } catch (Exception e) {
            // TODO: handle e
        }
    }

    /**
     * Diese Methode behandelt die Benutzereingaben und ruft die entsprechenden
     * Methoden in der Klasse NetzwerkzugehörigkeitBranch auf, um Operationen
     * auf der Tabelle ED_NETZWERKZUGEHOERIGKEIT durchzuführen (Einfügen,
     * Aktualisieren, Löschen).
     *
     * @param c Die Verbindungsinstanz zur Datenbank.
     */
    private void useNetzwerkZugehörigkeitBranch(Connection c) {
        try (BufferedReader readInput = new BufferedReader(new InputStreamReader(System.in))) {
            String input = null;
            System.out.println("Gebe ein 1 ein für Einfügen || 2 für Updaten || 3 für Löschen ");
            while (input == null) {
                input = readInput.readLine();
            }
            int useCase = Integer.parseInt(input.trim());
            NetzwerkzugehörigkeitBranch nzb = new NetzwerkzugehörigkeitBranch();
            switch (useCase) {
                case 1:

                    nzb.NetzwerkzugehörigkeitInsert(c);
                    break;
                case 2:
                    nzb.NetzwerkzugehörigkeitUpdate(c);
                    break;
                case 3:
                    nzb.NetzwerkzugehörigkeitDelete(c);
                    break;
                default:
                    System.out.println("Flascher Input");
                    useNetzwerkBranch(c);
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
