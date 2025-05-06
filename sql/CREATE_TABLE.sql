/*==================================================================*/
/* Name:		   CREATE_TABLE                                     */
/* Created on:     04.03.2024                                       */
/* Creater:        Jade HS/Winf                                     */
/* Company:        Jade HS                                          */
/* Aufgabe:        Erstellt alle Tabellen                           */ 
/*==================================================================*/

--USE DBXXXXXXX

/*==================================================================*/
/*                             KONTOART                             */
/*==================================================================*/
CREATE TABLE ED_KONTOART
(
	KART_ID		char(1)		 not null,
	KART_Name	varchar(15)	 not null,
	CONSTRAINT PK_ED_KONTOART PRIMARY KEY (KART_ID)
);

/*==================================================================*/
/*                             BENUTZER                             */
/*==================================================================*/
CREATE TABLE ED_BENUTZER
(
	Benutzer_ID				char(5)			not null,
	Nachname				varchar(50)		not null,
	Vorname					varchar(50)		    null,
	Geschlecht				char(1)			    null	CHECK(Geschlecht = 'M' OR Geschlecht = 'W' OR Geschlecht = 'D'),
	Geburtsdatum			datetime		    null,
	EMail					varchar(50)		not null,
	Telefonnummer			varchar(20)		    null,	
	Anschrift			    varchar(50)		    null,
	Kontoart				char(1)			not null
		REFERENCES ED_KONTOART (KART_ID),
    Anzahl_Follower         int                 null,
	Anzahl_Abonnements     int                 null,
	CONSTRAINT PK_ED_BENUTZER PRIMARY KEY (Benutzer_ID)
);

/*==================================================================*/
/*                             FOLLOWER                             */
/*==================================================================*/
--Speichert die Follower von Benutzer x ("Benutzer x hat Benutzer y als Follower")
CREATE TABLE ED_FOLLOWER
(
	F_ID                    int             not null,
	Verfolgter_Benutzer		char(5)			not null
		REFERENCES ED_BENUTZER (Benutzer_ID),
	Follower				char(5)			not null
		REFERENCES ED_BENUTZER (Benutzer_ID),
	CONSTRAINT PK_ED_FOLLOWER PRIMARY KEY (F_ID)
);

/*==================================================================*/
/*                            BEITRAGSART                           */
/*==================================================================*/
CREATE TABLE ED_BEITRAGSART
(
	BART_ID		char(2)		 not null,
	BART_Name	varchar(15)	 not null,
	CONSTRAINT PK_ED_BEITRAGSART PRIMARY KEY (BART_ID)
);

/*==================================================================*/
/*                              BEITRAG                             */
/*==================================================================*/
CREATE TABLE ED_BEITRAG
(
	Beitrag_ID				    varchar(10)		not null,
	Veroeffentlichungsdatum		date			not null,
	Ersteller                   char(5)         not null
		REFERENCES ED_BENUTZER (Benutzer_ID),
	Anzahl_Likes				int					null,
	Anzahl_Kommentare			int					null,
	Anzahl_Shares				int					null,
    BART_ID                     char(2)         not null
		REFERENCES ED_BEITRAGSART (BART_ID),
	Beitragstext                text                null,
	Bild_Video                  image               null,
	CONSTRAINT PK_ED_BEITRAG PRIMARY KEY (Beitrag_ID)
);

/*==================================================================*/
/*                          INTERAKTIONSART                         */
/*==================================================================*/
CREATE TABLE ED_INTERAKTIONSART
(
	IART_ID		char(1)		not null,
	IART_Name	varchar(7)	not null,
	CONSTRAINT PK_ED_INTERAKTIONSART PRIMARY KEY (IART_ID)
);

/*==================================================================*/
/*                            INTERAKTION                           */
/*==================================================================*/
CREATE TABLE ED_INTERAKTION
(
	Beitrag_ID				varchar(10)		not null
		REFERENCES ED_BEITRAG (Beitrag_ID),
	Benutzer_ID				char(5)			not null
		REFERENCES ED_BENUTZER (Benutzer_ID),
	IART_ID					char(1)			not null
		REFERENCES ED_INTERAKTIONSART (IART_ID),
	Kommentartext           text        		null,
	CONSTRAINT PK_ED_INTERAKTION PRIMARY KEY (Benutzer_ID, Beitrag_ID, IART_ID)
);

/*==================================================================*/
/*                             NETZWERKART                          */
/*==================================================================*/
CREATE TABLE ED_NETZWERKART
(
	NART_ID		char(2)		 not null,
	NART_Name	varchar(20)	 not null,
	CONSTRAINT PK_ED_NETZWERKART PRIMARY KEY (NART_ID)
);

/*==================================================================*/
/*                              NETZWERK                            */
/*==================================================================*/
CREATE TABLE ED_NETZWERK
(
	Netzwerk_ID				 char(5)		not null,
	Netzwerk_Name		     varchar(50)    not null,
	N_ART					 char(2)		not null
		REFERENCES ED_NETZWERKART (NART_ID),
	Netzwerk_Beschreibung	 varchar(100)	not null,
	CONSTRAINT PK_ED_NETZWERK PRIMARY KEY (Netzwerk_ID)
);

/*==================================================================*/
/*                        NETZWERKZUGEHOERIGKEIT                    */
/*==================================================================*/
CREATE TABLE ED_NETZWERKZUGEHOERIGKEIT
(
	Netzwerk_ID				char(5)		not null
		REFERENCES ED_NETZWERK (Netzwerk_ID),
	Mitglied				char(5)			not null
		REFERENCES ED_BENUTZER (Benutzer_ID),
	CONSTRAINT PK_ED_NETZWERKZUGEHOERIGKEIT PRIMARY KEY (Netzwerk_ID, Mitglied)
);


SELECT * FROM ED_KONTOART
SELECT * FROM ED_BENUTZER
SELECT * FROM ED_FOLLOWER
SELECT * FROM ED_BEITRAGSART
SELECT * FROM ED_BEITRAG
SELECT * FROM ED_INTERAKTIONSART
SELECT * FROM ED_INTERAKTION
SELECT * FROM ED_NETZWERKART
SELECT * FROM ED_NETZWERK
SELECT * FROM ED_NETZWERKZUGEHOERIGKEIT