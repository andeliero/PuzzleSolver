//package puzzsol;
import java.io.*;

abstract class PuzzleFile extends File{
	protected int row=0;
	protected int collumn=0;
	
	PuzzleFile(String file){
		super(file);
	}

	abstract void parse();

	abstract void writeContent(String content);
	
	int size(){
		return row*collumn;
	}

	int getRow(){
		return row;
	}

	int getCollumn(){
		return collumn;
	}
	
	abstract Tile getTile(int n);
} 

