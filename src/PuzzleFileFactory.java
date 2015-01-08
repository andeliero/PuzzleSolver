//package puzzsol;
import java.io.*;

class PuzzleFileFactory{
	public static PuzzleFile getPuzzleFile(PuzzleFileType type, String path){
		PuzzleFile file=null;
		switch(type){
		case STRING:
			file=new StringPuzzleFile(path);
		default:
			//throw Type incompatibile;
			break;
		}
		return file;
	}
}
