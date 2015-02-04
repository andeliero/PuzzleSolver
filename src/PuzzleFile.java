package puzzsol;
import java.io.*;

abstract class PuzzleFile extends File{

	protected int size=0;
	
	PuzzleFile(String file){
		super(file);
	}

	int size(){
		return size;
	}

	abstract void parse();

	abstract void writeContent(String content);

	abstract Tile getTile(int n);

} 

