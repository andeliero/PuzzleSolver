package puzzsol;

/* 
implementazione ad oggetti dell'intero insieme di tessere del puzzle.
All'inizio è non ordinato poi con un metodo sort() che permette 
di riordinare tutti i pezzi all'interno.
La classe TileScout mette a disposizione dei metodi utili per il riordino delle tessere.
*/


class Puzzle implements PuzzleInterface {

	private Tile[] tessere=new Tile[0];//le tessere sono ordinate per riga
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

	class TileScout{
		Tile findEastTile(Tile[] mtrx, Tile foo){
			for (int i=0; i<mtrx.length; i++) {
				if(foo.Western(mtrx[i])){
					return mtrx[i];
				}
			}
			return null;
		}

		Tile findSouthTile(Tile[] mtrx, Tile foo){
			for (int i=0; i<mtrx.length; i++) {
				if(foo.Northern(mtrx[i])){
					return mtrx[i];
				}
			}
			return null;
		}

		Tile findFirstTile(Tile[] mtrx){
			for (int i=0; i<mtrx.length; i++) {
				Tile a=mtrx[i];
				if(a.northEmpty() && a.westEmpty()) {return a;}
			}
			return null;
		}
	}

	public void sort(){
		if(tessere.length==0) return;
		Tile[] ntessere=new Tile[tessere.length];//nuovo array dove inserire i riferimenti alle tessere nella posizione corretta
		TileScout scout=new TileScout();
		for (int d=0; d<rows*collumns; d++){
			int lastIndexCollumn=d%collumns;
			int lastIndexRow=d/(rows-1);
			if(d==0){//per il primo tassello non ho riferimenti quindi
				//cerco la prima tessera da posizionare in ntessere[0]
				ntessere[d]=scout.findFirstTile(tessere);
			} else if(lastIndexCollumn==0){//quando ho terminato la riga corrente
				//cerco l'elemento più a sud
				ntessere[d] = scout.findSouthTile(tessere,ntessere[d-collumns]);
			}else{//altrimenti
				//creco l'elemento più e est
				ntessere[d] = scout.findEastTile(tessere,ntessere[d-1]);
			}
		}
		tessere=ntessere;
		return;
	}

	public void writeIntoFile(String path){
		PuzzleFile outputPuzzle = PuzzleFileFactory.getPuzzleFile(PuzzleFileType.STRING,path);
		String out="";
		for(int a=0; a<tessere.length; a++){
			out=out+tessere[a].getRawTile();
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
