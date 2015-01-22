#Java compiler
JC=javac
#Source folder
SRC=src
#Binaries folder
BIN=bin
#Javac flags
JF= -g -classpath $(BIN) -d $(BIN)
#PWD è la variabile del path corrente
default: PuzzleSolver.class

PuzzleFile.class:
	$(JC) $(JF) $(SRC)/PuzzleFile.java $(SRC)/StringPuzzleFile.java $(SRC)/PuzzleFileType.java $(SRC)/PuzzleFileFactory.java

Tile.class:
	$(JC) $(JF) $(SRC)/Tile.java $(SRC)/StringTile.java $(SRC)/TileFactory.java $(SRC)/TileType.java

Puzzle.class: Tile.class PuzzleFile.class
	$(JC) $(JF) $(SRC)/Puzzle.java

PuzzleSolver.class:	Puzzle.class
	$(JC) $(JF) $(SRC)/PuzzleSolver.java

debug: default
	jdb -classpath $(BIN) -sourcepath $(SRC) PuzzleSolver iofile/file_input iofile/file_output

test1:
	java -cp $(BIN) PuzzleSolver iofile/file_input_giusto iofile/file_output

test2:
	java -cp $(BIN) PuzzleSolver iofile/input1.txt iofile/file_output

clean:
	rm -r $(BIN)/*
