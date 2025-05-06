insert into DIM_KONTOART(KART_ID,KART_Name)
select *
from ED_KONTOART

insert into DIM_BENUTZER(Benutzer_ID,Nachname,Vorname,Geschlecht,Geburtsdatum,Anschrift,Kontoart,Anzahl_Follower,Anzahl_Abonnements)
select Benutzer_ID ,Nachname,Vorname,Geschlecht,Geburtsdatum,Anschrift,Kontoart,Anzahl_Follower,Anzahl_Abonnements
from ED_BENUTZER

insert into DIM_BEITRAGART(BART_ID,BART_Name)
select *
from ED_BEITRAGSART

insert into DIM_ZEIT(DATUM,MONAT,WOCHE,JAHR)
select distinct b.Veroeffentlichungsdatum,MONTH(b.Veroeffentlichungsdatum),DATEPART(wk,b.Veroeffentlichungsdatum),YEAR(b.Veroeffentlichungsdatum)
from ED_BEITRAG B

insert into FACT_BENUTZER(Benutzer_ID,BART_ID,ZEITNUMMER,ANZAHL_BEITRAGE,ANZAHL_INTERAKTIONEN,ANZAHL_KOMMENTARE,ANZAHL_LIKES,ANZAHL_SHARES)
select BN.Benutzer_ID,Ba.BART_ID,z.ZEITNUMMER,COUNT(*),(bt.Anzahl_Kommentare+bt.Anzahl_Likes+bt.Anzahl_Shares),bt.Anzahl_Kommentare,Anzahl_Likes,Anzahl_Shares
from ED_BEITRAG BT , ED_BEITRAGSART BA,ED_BENUTZER BN, DIM_ZEIT z
where bt.Ersteller = bn.Benutzer_ID
and ba.BART_ID = bt.BART_ID
and z.DATUM = bt.Veroeffentlichungsdatum
group by bn.Benutzer_ID,ba.BART_ID,z.ZEITNUMMER,bt.Anzahl_Kommentare,bt.Anzahl_Likes,bt.Anzahl_Shares


select DATEPART(yyyy,z.DATUM) as Jahr,DATEPART(wk,z.DATUM) as Woche,Sum(fb.ANZAHL_BEITRAGE) Beitrage_Summe,Sum(fb.ANZAHL_INTERAKTIONEN) Interaktion_Summe,
sum(fb.ANZAHL_KOMMENTARE) Kommentare_Summe,sum(fb.ANZAHL_LIKES)Likes_Summe,Sum(fb.ANZAHL_SHARES) Shares_Summe
into FACT_BENUTZER_AGG
from FACT_BENUTZER fb , DIM_ZEIT z where z.ZEITNUMMER =fb.ZEITNUMMER 
group by DATEPART(yyyy,z.datum),DATEPART(wk,z.datum)

select DATEPART(yyyy,z.DATUM) as Jahr,DATEPART(wk,z.DATUM) as Woche,fb.Benutzer_ID,Sum(fb.ANZAHL_BEITRAGE) Beitrage_Summe,Sum(fb.ANZAHL_INTERAKTIONEN) Interaktion_Summe,
sum(fb.ANZAHL_KOMMENTARE) Kommentare_Summe,sum(fb.ANZAHL_LIKES)Likes_Summe,Sum(fb.ANZAHL_SHARES) Shares_Summe
into FACT_BENUTZER_AGG_BENUTZER
from FACT_BENUTZER fb , DIM_ZEIT z where z.ZEITNUMMER =fb.ZEITNUMMER 
group by DATEPART(yyyy,z.datum),DATEPART(wk,z.datum) ,fb.Benutzer_ID

select DATEPART(yyyy,z.DATUM) as Jahr,DATEPART(wk,z.DATUM) as Woche,fb.BART_ID,Sum(fb.ANZAHL_BEITRAGE) Beitrage_Summe,Sum(fb.ANZAHL_INTERAKTIONEN) Interaktion_Summe,
sum(fb.ANZAHL_KOMMENTARE) Kommentare_Summe,sum(fb.ANZAHL_LIKES)Likes_Summe,Sum(fb.ANZAHL_SHARES) Shares_Summe
into FACT_BENUTZER_AGG_BART
from FACT_BENUTZER fb , DIM_ZEIT z where z.ZEITNUMMER =fb.ZEITNUMMER 
group by DATEPART(yyyy,z.datum),DATEPART(wk,z.datum) ,fb.BART_ID

select DATEPART(yyyy,z.DATUM) as Jahr,DATEPART(wk,z.DATUM) as Woche,fb.Benutzer_ID,fb.BART_ID,Sum(fb.ANZAHL_BEITRAGE) Beitrage_Summe,Sum(fb.ANZAHL_INTERAKTIONEN) Interaktion_Summe,
sum(fb.ANZAHL_KOMMENTARE) Kommentare_Summe,sum(fb.ANZAHL_LIKES)Likes_Summe,Sum(fb.ANZAHL_SHARES) Shares_Summe
into FACT_BENUTZER_AGG_BART_BENUTZER
from FACT_BENUTZER fb , DIM_ZEIT z where z.ZEITNUMMER =fb.ZEITNUMMER 
group by DATEPART(yyyy,z.datum),DATEPART(wk,z.datum) ,fb.BART_ID,fb.Benutzer_ID

select * from FACT_BENUTZER_AGG_BART_BENUTZER order by Jahr,Woche