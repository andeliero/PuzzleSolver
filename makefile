#Java compiler
JC=javac
#Source folder
SRC=$(PWD)/src
#Binaries folder
BIN=$(PWD)/bin
#Javac flags
JF= -classpath $(BIN) -d $(BIN)

default: PuzzleSolver.class

PuzzleFile.class:
	$(JC) $(JF) $(SRC)/PuzzleFile.java $(SRC)/StringPuzzleFile.java $(SRC)/PuzzleFileType.java $(SRC)/PuzzleFileFactory.java

Tile.class:
	$(JC) $(JF) $(SRC)/Tile.java $(SRC)/StringTile.java $(SRC)/TileFactory.java $(SRC)/TileType.java

Puzzle.class: Tile.class PuzzleFile.class
	$(JC) $(JF) $(SRC)/Puzzle.java

PuzzleSolver.class:	Puzzle.class
	$(JC) $(JF) $(SRC)/PuzzleSolver.java

test:
	java -cp $(BIN) PuzzleSolver $(PWD)/iofile/file_input $(PWD)/iofile/file_output
clean:
	rm -r $(BIN)/*
