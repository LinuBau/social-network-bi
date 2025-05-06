create trigger Update_Anzahl
on ED_Interaktion
After Insert 
as 
Begin
	declare @Beitrag_ID varchar(10),@IArt_Id varchar(1)
	select @Beitrag_ID = Beitrag_ID,@IArt_Id = IART_ID from inserted
	begin transaction Update_Anzahl
	if @IArt_Id = 'L'
	begin
		update ED_BEITRAG set Anzahl_Likes = Anzahl_Likes + 1 where @Beitrag_ID = Beitrag_ID
	end
	if @IArt_Id = 'C'
	begin
		update ED_BEITRAG set Anzahl_Kommentare = Anzahl_Kommentare +1 where @Beitrag_ID = Beitrag_ID
	end
	if @IArt_Id = 'S'
	begin 
		update ED_BEITRAG set Anzahl_Shares = Anzahl_Shares+1 where @Beitrag_ID = Beitrag_ID
	end
	commit transaction Update_Anzahl
End;

drop trigger Update_Anzahl