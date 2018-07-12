site orcNode = com.orcNode
site orcTuple = com.orcTuple

val client = orcNode("eNode",15000,"localhost",9999)
val c = orcTuple()

def addLocality() = 
 	client.addEnv("store1",11004)>>client.addEnv("store2",12004)>>client.addEnv("store3",13004)>>client.addEnv("lClient",14000)

def moveToStore1() = 
	client.in(c.tuple("id3",c.intFormal(),c.intFormal(),"reqId12"),c.locality("store1")) > x > 
	c.get(x,1)>z> 
	client.out(c.tuple("store1",z,"reqId12"),c.locality("lClient")) >> moveToStore1


def moveToStore2() = 
	client.in(c.tuple("id3",c.intFormal(),c.intFormal(),"reqId12"),c.locality("store2")) > x > 
	c.get(x,1)>z>
	client.out(c.tuple("store2",z,"reqId12"),c.locality("lClient")) >> moveToStore2


def moveToStore3() = 
	client.in(c.tuple("id3",c.intFormal(),c.intFormal(),"reqId12"),c.locality("store3")) > x > 
	c.get(x,1)>z> 
	client.out(c.tuple("store3",z,"reqId12"),c.locality("lClient")) >> moveToStore3

def startSearch() = 
	c.loadProcess("FindItem","id3",200,"reqId12")> proc > client.eval(proc,c.locality("store1"))
	>> client.eval(proc,c.locality("store2")) >> client.eval(proc,c.locality("store3"))

def g() =
	client.out(c.tuple("sum","store1","reqId12",0),c.locality("store1")) |
	client.out(c.tuple("sum","store2","reqId12",0),c.locality("store2")) |
	client.out(c.tuple("sum","store3","reqId12",0),c.locality("store3")) 
	|
	(if x = "store1ok" then print("Acquisto da store 1"))< x < (g1 | g2 | g3)
	
def g1() = 
	let("store1ok")

def g2() = 
	let("store2ok")

def g3() = 
	let("store3ok")

addLocality() >> startSearch() >> (moveToStore1() | moveToStore2() | moveToStore3() ) >> g 

