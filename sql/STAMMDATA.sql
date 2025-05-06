/*==================================================================*/
/* Name:		   STAMMDATA                                        */
/* Created on:     05.03.2024                                       */
/* Creater:        Jade HS/Winf                                     */
/* Company:        Jade HS                                          */
/* Aufgabe:        Anlegen der Anwendungsstammdaten                 */ 
/*==================================================================*/

--USE DBXXXXXXX

BEGIN TRANSACTION

--Interaktionsart
INSERT INTO ED_INTERAKTIONSART VALUES ('C', 'Comment');
INSERT INTO ED_INTERAKTIONSART VALUES ('L', 'Like');
INSERT INTO ED_INTERAKTIONSART VALUES ('S', 'Share');

--Kontoart
INSERT INTO ED_KONTOART VALUES ('A', 'Administrator');
INSERT INTO ED_KONTOART VALUES ('B', 'Business');
INSERT INTO ED_KONTOART VALUES ('O', 'Oeffentlich');
INSERT INTO ED_KONTOART VALUES ('P', 'Privat');

--Beitragsart
INSERT INTO ED_BEITRAGSART VALUES ('BB', 'Bildbeitrag');
INSERT INTO ED_BEITRAGSART VALUES ('TB', 'Textbeitrag');
INSERT INTO ED_BEITRAGSART VALUES ('VB', 'Videobeitrag');

--Netzwerkart
INSERT INTO ED_NETZWERKART VALUES ('GG', 'Geschlossene Gruppe');
INSERT INTO ED_NETZWERKART VALUES ('OG', 'Offene Gruppe');
INSERT INTO ED_NETZWERKART VALUES ('PG', 'Private Gruppe');

COMMIT;

SELECT * FROM ED_INTERAKTIONSART;
SELECT * FROM ED_KONTOART;
SELECT * FROM ED_BEITRAGSART;
SELECT * FROM ED_NETZWERKART