//package puzzsol;

/* 
rappresentazione ad oggetti dell'intero insieme di tessere del puzzle.
All'inizio è non ordinato poi con un metodo ordina() che permette 
di riordinare tutti i pezzi all'interno.
*/


public class Puzzle {

	private Tile[] tessere=new Tile[0];
	private int row=0;
	private int collumn=0;

	public Puzzle(){}

	public void readFromFile(String path){
		PuzzleFile inputPuzzle = PuzzleFileFactory.getPuzzleFile(PuzzleFileType.STRING,path);
		inputPuzzle.parse();
		int size= inputPuzzle.size();
		tessere=new Tile[size];
		for(int s=0; s<size; ++s){
			tessere[s]=inputPuzzle.getTile(s+1);
			if(tessere[s].westEmpty()) row++;
			if(tessere[s].northEmpty()) collumn++;
		}
	}

	public void sort(){
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
		if(tessere.length==0) return;
		//nuovo array dove inserire i riferimenti alle tessere nella posizione corretta
		Tile[] ntessere=new Tile[tessere.length];
		TileScout scout=new TileScout();
		for (int d=0; d<row*collumn; d++){
			int lastIndexCollumn=d%collumn;
			int lastIndexRow=d/(row-1);
			//cerco la tessere a est di ntessere[lastIndexCollumn], e quando ho terminayto la riga
			//cerco la tessere a sud di ntessere[lastIndexRow][0]
			if(d==0){
				//cerco la prima tessera da posizionare in ntessere[0]
				ntessere[d]=scout.findFirstTile(tessere);
			} else if(lastIndexCollumn==0){
				//cerco l'elemento più a sud
				ntessere[d] = scout.findSouthTile(tessere,ntessere[d-collumn]);
			}else{
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
		out=out+"\n"+row+" "+collumn;
		outputPuzzle.writeContent(out);
    }
}
