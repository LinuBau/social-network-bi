drop table FACT_BENUTZER
drop table DIM_ZEIT
drop table DIM_BENUTZER
drop table DIM_KONTOART
drop table DIM_BEITRAGART

Create Table DIM_KONTOART(
	KART_ID		char(1)		 not null,
	KART_Name	varchar(15)	 not null,
	CONSTRAINT PK_DIM_KONTOART PRIMARY KEY (KART_ID)
)
Create table DIM_BENUTZER(
	Benutzer_ID				char(5)			not null,
	Nachname				varchar(50)		not null,
	Vorname					varchar(50)		    null,
	Geschlecht				char(1)			    null,
	Geburtsdatum			datetime		    null,
	Anschrift			    varchar(50)		    null,
	Kontoart				char(1)			not null 
	REFERENCES DIM_KONTOART (KART_ID),
    Anzahl_Follower         int                 null,
	Anzahl_Abonnements     int                 null,
	CONSTRAINT PK_DIM_BENUTZER PRIMARY KEY (Benutzer_ID))
Create Table DIM_BEITRAGART(	
	BART_ID		char(2)		 not null,
	BART_Name	varchar(15)	 not null,
	CONSTRAINT PK_DIM_BEITRAGSART PRIMARY KEY (BART_ID))

create table DIM_ZEIT (
  ZEITNUMMER int IDENTITY (1,1) not null,
  DATUM date not null,
  MONAT int not null,
  WOCHE int not null,
  JAHR int not null,
  constraint PK_DIM_ZEIT primary key (ZEITNUMMER)
)
create unique index IU_DIM_DATUMS on DIM_ZEIT (DATUM, MONAT, WOCHE, JAHR);
create table FACT_BENUTZER(
	ANZAHL_BEITRAGE int not null,
	ANZAHL_KOMMENTARE int not null,
	ANZAHL_SHARES int not null,
	ANZAHL_LIKES int not null,
	ANZAHL_INTERAKTIONEN int not null,
	ZEITNUMMER int not null,
	Benutzer_ID char(5)	 not null,
	BART_ID char(2) not null
	constraint PK_FACT_BENUTZER primary key ( Benutzer_ID, ZEITNUMMER, BART_ID)
)
alter table FACT_BENUTZER add constraint FACT_BART_ID_FK foreign key (BART_ID) references DIM_BEITRAGART(BART_ID)
alter table FACT_BENUTZER add constraint FACT_Benutzer_ID_FK foreign key (Benutzer_ID) references DIM_BENUTZER(Benutzer_ID)
alter table FACT_BENUTZER add constraint FACT_ZEIT_FK foreign key (ZEITNUMMER) references DIM_ZEIT(ZEITNUMMER)

