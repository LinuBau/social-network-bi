create procedure DW_REPORT(@benutzer varchar(5) null,@beitragArt varchar(2) null,@zeitraumbegin date null,@zeitraumende date null)as
begin
	declare @DATUM_NOT_NULL int
	set @DATUM_NOT_NULL = 1
	if @zeitraumbegin is not null and @zeitraumende is null
	begin
		select @zeitraumende = GETDATE()
	end 
	if @zeitraumbegin is null and @zeitraumende is not null
	begin
		select @zeitraumbegin = Min(DATUM) from DIM_ZEIT
	end
	if @zeitraumbegin is null and @zeitraumende is null
	begin
		set @DATUM_NOT_NULL = 0
	end
-- Erstellen der Varibalen die auswertung des Benutzer
	declare @benutzer_anzahl_beitrag int, @benutzer_anzahl_kommentare int,@benutzer_anzahl_likes int ,@benutzer_anzahl_shares int,
	@benutzer_anzahl_beitrage_zeitraum int, @benutzer_anzahl_kommentare_zeitraum int,
	@benutzer_anzahl_likes_zeitraum int, @benutzer_anzahl_shares_zeitraum int,@benutzer_Nachname varchar(50),
	@benutzer_Vorname varchar(50), @benutzer_date datetime,@benutzer_anschrift varchar(50),@benutzer_kontoart varchar(25),
	@benutzer_follower int, @benutzer_abonnenten int

	select @benutzer_Nachname=Nachname,@benutzer_Vorname=Vorname,@benutzer_date = Geburtsdatum,
	@benutzer_anschrift=Anschrift,@benutzer_kontoart=k.KART_Name, 
	@benutzer_follower = Anzahl_Follower, @benutzer_abonnenten =Anzahl_Abonnements from DIM_BENUTZER b,DIM_KONTOART k 
	where b.Benutzer_ID = @benutzer and k.KART_ID = b.Kontoart


	select @benutzer_anzahl_beitrag = Sum(Beitrage_Summe),@benutzer_anzahl_kommentare=Sum(Kommentare_Summe),
	@benutzer_anzahl_likes=Sum(Likes_Summe), @benutzer_anzahl_shares=Sum(Shares_Summe) from FACT_BENUTZER_AGG_BENUTZER 
	where Benutzer_ID = @benutzer and @benutzer IS NOT NULL 

	IF @DATUM_NOT_NULL = 1
	begin
	IF @zeitraumbegin <= @zeitraumende
	begin
		select @benutzer_anzahl_beitrage_zeitraum = Sum(ANZAHL_BEITRAGE),@benutzer_anzahl_kommentare_zeitraum=Sum(ANZAHL_KOMMENTARE),
		@benutzer_anzahl_likes_zeitraum=Sum(ANZAHL_LIKES), @benutzer_anzahl_shares_zeitraum=Sum(ANZAHL_SHARES) from FACT_BENUTZER f ,DIM_ZEIT z
		where Benutzer_ID = @benutzer  and z.ZEITNUMMER = f.ZEITNUMMER
		and z.DATUM between @zeitraumbegin and @zeitraumende
	end
	ELSE
	begin
		set @DATUM_NOT_NULL = 0
	end
	end
	

--Erstellen der Varibalen die auswertung Beitrag Art
	declare @bart_anzahl_beitrag int, @bart_anzahl_kommentare int,@bart_anzahl_likes int,@bart_anzahl_shares int,
	@bart_anzahl_beitrag_zeitraum int ,@bart_anzahl_kommentare_zeitraum int,
	@bart_anzahl_likes_zeitraum int,@bart_anzahl_shares_zeitraum int,@bart_name varchar(15)

	select @bart_name = BART_Name from DIM_BEITRAGART where BART_ID = @beitragArt

	select @bart_anzahl_beitrag = Sum(Beitrage_Summe),@bart_anzahl_kommentare=Sum(Kommentare_Summe),
	@bart_anzahl_likes=Sum(Likes_Summe), @bart_anzahl_shares=Sum(Shares_Summe) from FACT_BENUTZER_AGG_BART
	where BART_ID = @beitragArt and @beitragArt IS NOT NULL
	IF @DATUM_NOT_NULL = 1
	begin
	IF @zeitraumbegin <= @zeitraumende
	begin
		select @bart_anzahl_beitrag_zeitraum = Sum(ANZAHL_BEITRAGE),@bart_anzahl_kommentare_zeitraum=Sum(ANZAHL_KOMMENTARE),
		@bart_anzahl_likes_zeitraum=Sum(ANZAHL_LIKES), @bart_anzahl_shares_zeitraum=Sum(ANZAHL_SHARES) from FACT_BENUTZER f ,DIM_ZEIT z
		where BART_ID = @beitragArt and @beitragArt IS NOT NULL and z.ZEITNUMMER = f.ZEITNUMMER
		and z.DATUM between @zeitraumbegin and @zeitraumende
	end
	ELSE
	begin
		set @DATUM_NOT_NULL =0
	end
	end
--Erstellen der Variablen die auswertung Benutzer-Bart abkrüzung BenBart
	declare @benbart_anzahl_beitrag int, @benbart_anzahl_kommentare int,@benbart_anzahl_likes int,@benbart_anzahl_shares int,
	@benbart_anzahl_beitrag_zeitraum int, @benbart_anzahl_kommentare_zeitraum int, @benbart_anzahl_likes_zeitraum int, @benbart_anzahl_shares_zeitraum int
	
	select @benbart_anzahl_beitrag = Sum(Beitrage_Summe),@benbart_anzahl_kommentare=Sum(Kommentare_Summe),
	@benbart_anzahl_likes=Sum(Likes_Summe), @benbart_anzahl_shares=Sum(Shares_Summe) from FACT_BENUTZER_AGG_BART_BENUTZER
	where BART_ID = @beitragArt and @beitragArt IS NOT NULL and  Benutzer_ID = @benutzer and @benutzer is not null
	IF @DATUM_NOT_NULL = 1
	begin
	IF @zeitraumbegin <= @zeitraumende
	begin
		select @benbart_anzahl_beitrag_zeitraum = Sum(ANZAHL_BEITRAGE),@benbart_anzahl_kommentare_zeitraum=Sum(ANZAHL_KOMMENTARE),
		@benbart_anzahl_likes_zeitraum=Sum(ANZAHL_LIKES), @benbart_anzahl_shares_zeitraum=Sum(ANZAHL_SHARES) from FACT_BENUTZER f ,DIM_ZEIT z
		where BART_ID = @beitragArt and @beitragArt IS NOT NULL and Benutzer_ID = @benutzer and @benutzer is not null
		and z.ZEITNUMMER = f.ZEITNUMMER
		and z.DATUM between @zeitraumbegin and @zeitraumende
	end
	ELSE
	begin
		set @DATUM_NOT_NULL = 0
	end
	end
--Erstellen der Variablen die auswertung Gesamt 
	declare @gesamt_anzahl_beitrag int, @gesamt_anzahl_kommentare int,@gesamt_anzahl_likes int ,@gesamt_anzahl_shares int,
	@gesamt_anzahl_beitrag_zeitraum int,@gesamt_anzahl_kommentare_zeitraum int, @gesamt_anzahl_likes_zeitraum int,@gesamt_anzahl_shares_zeitraum int
	
	select @gesamt_anzahl_beitrag = Sum(Beitrage_Summe),@gesamt_anzahl_kommentare=Sum(Kommentare_Summe),
	@gesamt_anzahl_likes=Sum(Likes_Summe), @gesamt_anzahl_shares=Sum(Shares_Summe) from FACT_BENUTZER_AGG 
	IF @DATUM_NOT_NULL = 1
	begin
	IF @zeitraumbegin <= @zeitraumende
	begin
		select @gesamt_anzahl_beitrag_zeitraum = Sum(ANZAHL_BEITRAGE),@gesamt_anzahl_kommentare_zeitraum=Sum(ANZAHL_KOMMENTARE),
		@gesamt_anzahl_likes_zeitraum=Sum(ANZAHL_LIKES), @gesamt_anzahl_shares_zeitraum=Sum(ANZAHL_SHARES) from FACT_BENUTZER f,DIM_ZEIT z
		where z.ZEITNUMMER = f.ZEITNUMMER
		and z.DATUM between @zeitraumbegin and @zeitraumende
	end
	ELSE
	begin
		set @DATUM_NOT_NULL = 0
	end
	end

	if @benutzer is not null 
	begin
		print('------------------------------------BENUTZER AUSWERTUNG-------------------------------------------------------')
		print('VORNAME: '+ @benutzer_Vorname+'		BENUTZER: '+ @benutzer_Nachname+'		Kontoart: '+@benutzer_kontoart)
		print('GEBURTSTAG: '+cast(COALESCE(@benutzer_date,0) as varchar)+'		ANSCHRIFT: '+ @benutzer_anschrift)
		print('ANZAHL  FOLLOWER: '+cast(COALESCE(@benutzer_follower,0) as varchar)+'		ANZAHL ABONNENTEN: '+cast(COALESCE(@benutzer_abonnenten,0) as varchar))
		if @DATUM_NOT_NULL =1
		begin
		print('Zeitraum nach Auswertung Benutzer:')
		print('Anzahl Beitrage: '+cast(COALESCE(@benutzer_anzahl_beitrage_zeitraum,0) as varchar)+'		Anzahl Kommentare: '+cast(COALESCE(@benutzer_anzahl_kommentare_zeitraum,0) as varchar)
		+'		Anzahl Likes: '+cast(COALESCE(@benutzer_anzahl_likes_zeitraum,0) as varchar)+'		Anzahl Shares: '+cast(COALESCE(@benutzer_anzahl_shares_zeitraum,0) as varchar))
		end
		print('Gesammt nach Auswertung Benutzer:')
		print('Anzahl Beitrage: '+cast(COALESCE(@benutzer_anzahl_beitrag,0) as varchar)+'		Anzahl Kommentare: '+cast(COALESCE(@benutzer_anzahl_kommentare,0) as varchar)
		+'		Anzahl Likes : '+cast(COALESCE(@benutzer_anzahl_likes,0) as varchar)+'		Anzahl Shares: '+cast(COALESCE(@benutzer_anzahl_shares,0) as varchar))
		print('------------------------------------------------------------------------------------------------------------------')
   end 
   if @beitragArt is not null 
	begin
		print('------------------------------------BEITRAG ART AUSWERTUNG-------------------------------------------------------')
		print('Beitrag Abkürzung:'+ @beitragArt+'		Beitrag Vollername:'+ @bart_name)
		if @DATUM_NOT_NULL =1
		begin
		print('Zeitraum Auswertung nach Beitrag Art:')
		print('Anzahl Beitrage:'+cast(COALESCE(@bart_anzahl_beitrag_zeitraum,0) as varchar)+'		Anzahl Kommentare:'+cast(COALESCE(@bart_anzahl_kommentare_zeitraum,0) as varchar)
		+'		Anzahl Likes:'+cast(COALESCE(@bart_anzahl_likes_zeitraum,0) as varchar)+'		Anzahl Shares:'+cast(COALESCE(@bart_anzahl_shares_zeitraum,0) as varchar))
		end
		print('Gesammt Auswertung nach Beitrag Art:')
		print('Anzahl Beitrage:'+cast(COALESCE(@bart_anzahl_beitrag,0) as varchar)+'		Anzahl Kommentare:'+cast(COALESCE(@bart_anzahl_kommentare,0) as varchar)
		+'		Anzahl Likes:'+cast(COALESCE(@bart_anzahl_likes,0) as varchar)+'		Anzahl Shares:'+cast(COALESCE(@bart_anzahl_shares,0) as varchar))
		print('------------------------------------------------------------------------------------------------------------------')
   end
   if @benutzer is not null and @beitragArt is not null
	begin
		print('------------------------------------BENUTZER UND BEITRAG ART AUSWERTUNG-------------------------------------------------------')
		if @DATUM_NOT_NULL =1
		begin
		print('Zeitraum Auswertung Beitrag Art und Benutzer: ')
		print('Anzahl Beitrage:'+cast(COALESCE(@benbart_anzahl_beitrag_zeitraum,0) as varchar)+'		Anzahl Kommentare:'+cast(COALESCE(@benbart_anzahl_kommentare_zeitraum,0) as varchar))
		print('Anzahl Likes:'+cast(COALESCE(@benbart_anzahl_likes_zeitraum,0) as varchar)+'		Anzahl Shares:'+cast(COALESCE(@benbart_anzahl_shares_zeitraum,0) as varchar))
		end
		print('Gesammt Auswertung Beitrag Art und Benutzer:')
		print('Anzahl Beitrage:'+cast(COALESCE(@benbart_anzahl_beitrag,0) as varchar)+'		Anzahl Kommentare:'+cast(COALESCE(@benbart_anzahl_kommentare,0) as varchar))
		print('Anzahl Likes:'+cast(COALESCE(@benbart_anzahl_likes,0) as varchar)+'		Anzahl Shares:'+cast(COALESCE(@benbart_anzahl_shares,0) as varchar))
		print('------------------------------------------------------------------------------------------------------------------')
   end
   print('------------------------------------GESAMT AUSWERTUNG-------------------------------------------------------')
		if @DATUM_NOT_NULL =1
		begin
		print(' Gesamt Zeitraum Auswertung: ')
		print('Anzahl Beitrage:'+cast(COALESCE(@gesamt_anzahl_beitrag_zeitraum,0) as varchar)+'		Anzahl Kommentare:'+cast(COALESCE(@gesamt_anzahl_kommentare_zeitraum,0) as varchar))
		print('Anzahl Likes:'+cast(COALESCE(@gesamt_anzahl_likes_zeitraum,0) as varchar)+'		Anzahl Shares:'+cast(COALESCE(@gesamt_anzahl_shares_zeitraum,0) as varchar))
		end
		print('Gesammt Auswertung: ')
		print('Anzahl Beitrage:'+cast(COALESCE(@gesamt_anzahl_beitrag,0) as varchar)+'		Anzahl Kommentare:'+cast(COALESCE(@gesamt_anzahl_kommentare,0) as varchar))
		print('Anzahl Likes:'+cast(COALESCE(@gesamt_anzahl_likes,0) as varchar)+'		Anzahl Shares:'+cast(COALESCE(@gesamt_anzahl_shares,0) as varchar))
		print('------------------------------------------------------------------------------------------------------------------')
end

drop procedure DW_REPORT