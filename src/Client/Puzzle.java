//package puzzsol;

/* 
rappresentazione ad oggetti dell'intero insieme di tessere del puzzle.
All'inizio è non ordinato poi con un metodo ordina() che permette 
di riordinare tutti i pezzi all'interno.
*/

import java.rmi.*;


public class Puzzle {

	private Tile[] tessere=new Tile[0];
	private int rows=0;
	private int collumns=0;

	public Puzzle(){}

	public void readFromFile(String path){
		PuzzleFile inputPuzzle = PuzzleFileFactory.getPuzzleFile(PuzzleFileType.STRING,path);
		inputPuzzle.parse();
		int size= inputPuzzle.size();
		tessere=new Tile[size];
		for(int s=0; s<size; ++s){
			tessere[s]=inputPuzzle.getTile(s+1);
			if(tessere[s].westEmpty()) rows++;
			if(tessere[s].northEmpty()) collumns++;
		}
	}

	
	public void sort(ResPuzzleAlg rpa) throws RemoteException {
		tessere = rpa.sort(tessere,rows,collumns);
		return;
	}

	public void writeIntoFile(String path){
		PuzzleFile outputPuzzle = PuzzleFileFactory.getPuzzleFile(PuzzleFileType.STRING,path);
		String out="";
		for(int a=0; a<tessere.length; a++){
			out=out+tessere[a].getRawTile();
			//System.out.println(tessere[a]);
		}
		out=out+"\n\n";
		for(int a=0; a<tessere.length; a++){
			out=out+tessere[a].getRawTile();
			if(tessere[a].eastEmpty()) out=out+"\n";
		}
		out=out+"\n"+rows+" "+collumns;
		outputPuzzle.writeContent(out);
    }
}
