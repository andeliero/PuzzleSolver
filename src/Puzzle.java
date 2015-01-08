//package puzzsol;

/* 
rappresentazione ad oggetti dell'intero insieme di tessere del puzzle.
All'inizio è non ordinato poi con un metodo ordina() che permette 
di riordinare tutti i pezzi all'interno.
*/


public class Puzzle {

	private Tile[][] tessere=new Tile[0][0];

	public Puzzle(){}

    public void readFromFile(String path){
		PuzzleFile inputPuzzle = PuzzleFileFactory.getPuzzleFile(PuzzleFileType.STRING,path);
		inputPuzzle.parse();
		int row=inputPuzzle.getRow();
		int collumn=inputPuzzle.getCollumn();
		tessere=new Tile[row][collumn];
		for(int r=0; r<row; ++r){
			for(int c=0; c<collumn; ++c){
				tessere[r][c]=inputPuzzle.getTile((r*collumn)+(c+1));
			}
		}
	}

	public void sort(){

	}

    public void writeIntoFile(String path){
		PuzzleFile outputPuzzle = PuzzleFileFactory.getPuzzleFile(PuzzleFileType.STRING,path);
		String out="";
    	for(int i=0; i<tessere.length; i++){
			for(int j=0; j<tessere[i].length; j++){
				out=out+(tessere[i][j]).getRawTile();
			}
    	}
    	outputPuzzle.writeContent(out);
    }
}
