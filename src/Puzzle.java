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
		if(tessere.length==0 && tessere[0].length==0) return;
		int row=tessere.length;
		int collumn=tessere[0].length;
		
		//nuovo array dove inserire i riferimenti alle tessere nella posizione corretta
		Tile[][] ntessere=new Tile[row][collumn];

		//cerco la prima tessera
		Tile first=null;
		int i=-1;
		while (first==null){
			i++;
			Tile aux=tessere[i/collumn][i%collumn];
			if(aux.northEmpty() && aux.westEmpty()){ ntessere=aux; }
		}
		System.out.println(i);

		last=ntessere[0][0];
		while(!(last.southEmpty&&last.westEmpty) ){
			//search tile
			//creo un metodo che gli passa un'id e ritorna il riferimento al tile
			//nel caso abbia completato la riga ricerco il south del primo della riga
		}
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
