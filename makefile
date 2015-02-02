#Java compiler
JC=javac
#Client Source folder
CSRC=src/Client
#Server Source folder
SSRC=src/Server
#Client Binaries folder
CBIN=bin/Client
#Server Binaries folder
SBIN=bin/Server
#Javac flags for Client
CJF= -g -classpath $(CBIN) -d $(CBIN)
#Javac flags for Server
SJF= -g -classpath $(SBIN) -d $(SBIN)
#PWD è la variabile del path corrente

default: Client Server


#Compilazione per il Client
CResPuzzleAlg.class:
	$(JC) $(CJF) $(CSRC)/ResPuzzleAlg.java

CPuzzleFile.class:
	$(JC) $(CJF) $(CSRC)/PuzzleFile.java $(CSRC)/StringPuzzleFile.java $(CSRC)/PuzzleFileType.java $(CSRC)/PuzzleFileFactory.java

CTile.class:
	$(JC) $(CJF) $(CSRC)/Tile.java $(CSRC)/StringTile.java $(CSRC)/TileFactory.java $(CSRC)/TileType.java

CPuzzle.class: CTile.class CPuzzleFile.class CResPuzzleAlg.class
	$(JC) $(CJF) $(CSRC)/Puzzle.java

PuzzleSolverClient.class:	CPuzzle.class
	$(JC) $(CJF) $(CSRC)/PuzzleSolverClient.java

Client: PuzzleSolverClient.class


#Compilazione per il Server
STile.class:
	$(JC) $(SJF) $(SSRC)/Tile.java $(SSRC)/StringTile.java

ResPuzzleAlg.class:	STile.class
	$(JC) $(SJF) $(SSRC)/ResPuzzleAlg.java $(SSRC)/ResPuzzleAlgImpl.java

PuzzleSolverServer.class: ResPuzzleAlg.class
	$(JC) $(SJF) $(SSRC)/PuzzleSolverServer.java

Server: PuzzleSolverServer.class

#other 
debug: default
	jdb -classpath $(BIN) -sourcepath $(SRC) PuzzleSolver iofile/file_input iofile/file_output

test1:
	java -cp $(BIN) PuzzleSolver iofile/file_input_giusto iofile/file_output

test2:
	java -cp $(BIN) PuzzleSolver iofile/input1.txt iofile/file_output

clean:
	rm -r $(SBIN)/*
