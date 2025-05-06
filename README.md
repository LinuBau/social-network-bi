# social-network-bi

## Aufgabenstellung

Im Rahmen der Prüfungsleistung im Fach *Business Intelligence* wurde eine Anwendung für ein soziales Netzwerk entwickelt. Ziel war es, sowohl eine operative Datenbanklösung als auch ein analytisches Data-Warehouse-System mit wöchentlichen Aggregationen zu erstellen. Die Aufgaben im Überblick:

1. **Einrichtung der operativen Datenbank** auf Basis bereitgestellter SQL-Skripte.
2. **Implementierung eines Triggers**, der beim Einfügen einer Interaktion automatisch Zähler für Likes, Kommentare oder Shares im zugehörigen Beitrag aktualisiert.
3. **Erweiterung der Datenbasis** um mindestens 10 Benutzer, 10 Beiträge, 20 Follower- und 20 Interaktionseinträge.
4. **Java-Anwendung (JDBC oder Hibernate)** zur Verwaltung (Create, Update, Delete) von Benutzern, Netzwerken und deren Zugehörigkeiten via Terminalmenü.
5. **Entwurf eines Data-Warehouse-Schemas (Star/Snowflake)** zur Auswertung von Beiträgen nach Benutzer, Beitragsart und Zeiträumen – mit Fakten auf Tages- und Wochenbasis.
6. **Entwicklung eines ETL-Prozesses**, um operative Daten in das Data-Warehouse zu überführen.
7. **Erstellung einer Prozedur `DW_REPORT`**, die auf Basis benutzerdefinierter Parameter Auswertungen durchführt und aggregierte Kennzahlen berechnet.
8. **Visualisierung der Analyseergebnisse mit PowerBI** in mindestens fünf unterschiedlichen Szenarien.

---

## Inhalt

- Trigger zur Interaktionszählung
- Erweiterung des Datenbestands
- Java-Terminalprogramm
- Snowflake-Schema für das DW
- ETL-Skripte und SQL-Prozeduren
- PowerBI-Auswertungen

## Technologien

- Java mit JDBC
- MS SQL Server

## Projektstruktur

```plaintext
social-network-bi/
├── README.md               # Projektbeschreibung
├── start.bat               # Startskript für Java-Anwendung
├── src/                    # Quellcode
│  ├──Java Code/            # Hier ist der Java für die Anwendung
│  └── Sql Code/            # Hier ist der Trigger und der Datawarehouse Code
└── sql/                    # Code zu einrichten der Datenbank
```
## Setup & Ausführung

1. **Datenbank vorbereiten**
   - SQL-Skripte ausführen (`START.sql`, `INSERT_EXTRA.sql` etc.)
   - Trigger einrichten

2. **Java-Anwendung starten**
   - Mit `start.bat` oder über Konsole (JAR-Datei)
   - Login-Daten eingeben, Menü folgt im Terminal

3. **Data-Warehouse & ETL**
   - Snowflake-Modell mit Fakten (Beiträge, Interaktionen, Likes, Shares, Kommentare)
   - Wochenaggregation durch GROUP BY
   - Ladeprozess via ETL-Skripten

4. **DW_REPORT Prozedur**
   - Parametergesteuerte Analyse
   - Ausgabe im SQL-Editor
   - Beispielaufruf siehe Dokumentation