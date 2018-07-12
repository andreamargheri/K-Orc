site orcNode = com.orcNode
site orcTuple = com.orcTuple

val client = orcNode("eNode",15000,"localhost",9998)
val c = orcTuple()

def addLocality() = 
 	client.addEnv("store1",11004)>>client.addEnv("store2",12004)>>client.addEnv("store3",13004)>>client.addEnv("lClient",14000)

def moveToStore1() = 
	client.in(c.tuple("id3",c.intFormal(),c.intFormal(),"reqId12"),c.locality("store1")) > x > 
	c.get(x,1)>z> 
	client.out(c.tuple("store1","reqId12",z),c.locality("lClient")) >> moveToStore1()


def moveToStore2() = 
	client.in(c.tuple("id3",c.intFormal(),c.intFormal(),"reqId12"),c.locality("store2")) > x > 
	c.get(x,1)>z>
	client.out(c.tuple("store2","reqId12",z),c.locality("lClient")) >> moveToStore2()


def moveToStore3() = 
	client.in(c.tuple("id3",c.intFormal(),c.intFormal(),"reqId12"),c.locality("store3")) > x > 
	c.get(x,1)>z> 
	client.out(c.tuple("store3","reqId12",z),c.locality("lClient")) >> moveToStore3()

def startSearch() = 
	c.loadProcess("FindItem","id3",200,"reqId12")> proc > client.eval(proc,c.locality("store1"))
	>> client.eval(proc,c.locality("store2")) >> client.eval(proc,c.locality("store3"))

def g() =
	client.out(c.tuple("sum","store1","reqId12",0),c.locality("lClient")) |
	client.out(c.tuple("sum","store2","reqId12",0),c.locality("lClient")) |
	client.out(c.tuple("sum","store3","reqId12",0),c.locality("lClient")) 
	|
	(if x = "store1ok" then 
	    print("\n \n Acquisto da store 1 \n \n") 
	else
	    (if x = "store2ok" then
		print("\n \n Acquisto da store 2 \n \n") 
	     else	
		(if x = "store3ok" then
		  print("\n \n Acquisto da store 3 \n \n") 
	    	)
	     )	
	)< x < (g1()| g2()| g3())

def g1() = 
	client.in(c.tuple("store1","reqId12",c.intFormal()),c.locality("lClient"))>x>
	c.getInt(x,2)> y > 
	client.in(c.tuple("sum","store1","reqId12",c.intFormal()),c.locality("lClient"))>x>
	c.getInt(x,3)> sum > 
	(if (y + sum >= 30) then 
		let("store1ok") 
	else 
		( client.out(c.tuple("sum","store1","reqId12",y + sum),c.locality("lClient"))>> g1() )
	)
	

def g2() = 
	client.in(c.tuple("store2","reqId12",c.intFormal()),c.locality("lClient"))>x>
	c.getInt(x,2)> y > 
	client.in(c.tuple("sum","store2","reqId12",c.intFormal()),c.locality("lClient"))>x>
	c.getInt(x,3)> sum > 
	(if (y + sum >= 30) then 
		let("store2ok") 
	else 
		( client.out(c.tuple("sum","store2","reqId12",y + sum),c.locality("lClient"))>> g2() )
	)
	
def g3() = 
	client.in(c.tuple("store3","reqId12",c.intFormal()),c.locality("lClient"))>x>
	c.getInt(x,2)> y > 
	client.in(c.tuple("sum","store3","reqId12",c.intFormal()),c.locality("lClient"))>x>
	c.getInt(x,3)> sum > 
	(if (y + sum >= 30) then 
		let("store3ok") 
	else 
		( client.out(c.tuple("sum","store3","reqId12",y + sum),c.locality("lClient"))>> g3() )
	)
	

addLocality() >> (

	startSearch() >> (moveToStore1() | moveToStore2() | moveToStore3() |g() )

		 ) 


