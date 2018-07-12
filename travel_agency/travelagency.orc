site orcNode = com.orcNode
site orcTuple = com.orcTuple
--site orcFinestra = com.orcFinestra

--val fin = orcFinestra()
val a = Ref[Integer](0)
val b = orcNode("orc1",11000,"localhost",9999)
val c = orcTuple()

def addNode() = 
	b.addEnv("hotel",11001) >> b.addEnv("volo",11002)>> b.addEnv("prenota",11003) >> b.addEnv("compensator",11004) 

def inserisciRichiesta() =
	a := a?+1 >>
	Prompt("Inserire Nome Volo")> x > 
	Prompt("Numero Posti Desiderati") > y > read(y) > z >
	b.out(c.tuple(a?,"volo",x,z),c.locality("self")) >> 
	Prompt("Inserire Tipo Stanza")> x > 
	Prompt("Numero Camere") > y > read(y) > z >
	b.out(c.tuple(a?,"hotel",x,z),c.locality("self")) 
	
def prenotazione() = 

	(
		b.in(c.tuple(c.intFormal(),"hotel",c.stringFormal(),c.intFormal()), c.locality("self")) > t >
		--il load process per la prenotazione dell'hotel
		c.get(t,0)> id > c.get(t,2)> richiesta > c.get(t,3)> num >
		c.loadProcess("prenotaHotel",id,richiesta,num)> proc > b.eval(proc,c.locality("hotel"))
	)

	|

	(
		b.in(c.tuple(c.intFormal(),"volo",c.stringFormal(),c.intFormal()), c.locality("self")) > t >  
		--il load process per la prenotazione del volo 
		c.get(t,0)> id > c.get(t,2)> richiesta > c.get(t,3)> num >
		c.loadProcess("prenotaVolo",id,richiesta,num)> proc1 > b.eval(proc1,c.locality("volo"))
	)
	|
	(
		esitoPrenotazione()
	)
def esitoPrenotazione() = 
	
		b.readp(c.tuple(a?,c.stringFormal(),c.intFormal()),c.locality("self"),2000) >b> (
		if b = true then pagamento() else esitoPrenotazione() )
		
def pagamento() = 
		
	--fin.add("\nESITO PRENOTAZIONE \n\n")>> 
	b.in(c.tuple(a?,c.stringFormal(),c.intFormal()),c.locality("self")) > t > c.get(t,2) > x > c.equals(x,0) > bol >
	( if bol = true then print("\n Posti Non Disponibili \n") 
		-->> fin.add("POSTI NON DISPONIBILI\n")
		>>stop
		else (	
			Prompt("Pagare " + x + " ? S/N") > y >
			(if y ="S" || y="s" then print("\n Prenotazione Eseguita \n")
				-->>fin.add("PRENOTAZIONE ESEGUITA\n")
				>> stop else 
				print("\n Prenotazione Cancellata\n") >>
				--fin.add("PAGAMENTO NON ESEGUITO\n") >> 
				b.out(c.tuple("start",a?), c.locality("compensator")) >> stop
			)
		)
	)

addNode() >> inserisciRichiesta() >> prenotazione()
--prova()

