//package puzzsol;

/* 
rappresentazione ad oggetti dell'intero insieme di tessere del puzzle.
All'inizio è non ordinato poi con un metodo ordina() che permette 
di riordinare tutti i pezzi all'interno.
*/


public class Puzzle {

	private Tile[] tessere=new Tile[0];
	private int rows=0;
	private int collumns=0;

	public int getRows(){
		return rows;
	}
	public int getCollumns(){
		return collumns;
	}

	public Tile[] getTessere(){
		return tessere;
	}

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

	class RowTileScout extends Thread{
		private Puzzle puzz;
		private Tile[] ntessere;
		private int row=-1;//a seconda del numero cambia il comportamento

		RowTileScout(Puzzle p, Tile[] dest, int r){
			puzz=p;
			ntessere=dest;
			row=r;
		}

		private Tile findEastTile(Tile a){
			Tile[] tessere=puzz.getTessere();
			for (int i=0; i<tessere.length; i++) {
				if(tessere[i].Eastern(a)){
					return tessere[i];
				}
			}
			return null;
		}

		public void run(){
			Tile[] tessere=puzz.getTessere();
			int ncol=puzz.getCollumns();
			int nrow=puzz.getRows();
			if(row==0){
				Thread[] trd= new Thread[ncol-1];
				for (int i=1; i<ncol; i++) {
					ntessere[i]=findEastTile(ntessere[i-1]);
					CollumnTileScout csc= new CollumnTileScout(puzz,ntessere,i);
					csc.start();
					trd[i-1]=csc;
				}
				for (int i=0; i<trd.length; i++) {
					try{
						trd[i].join();
					}catch(InterruptedException e){
						System.err.println(e);
					}
				}
			}else{
				for (int i=1; i<ncol; i++) {
					if(ntessere[row*ncol+i]!=null){
						ntessere[row*ncol+i]=findEastTile(ntessere[row*ncol+i-1]);
					}
				}
			}
		}
	}

	class CollumnTileScout extends Thread{
		private Puzzle puzz;
		private Tile[] ntessere;
		private int collumn=-1;//a seconda del numero cambia il comportamento

		CollumnTileScout(Puzzle p, Tile[] dest, int c){
			puzz=p;
			ntessere=dest;
			collumn=c;
		}

		private Tile findSouthTile(Tile a){//index indica la riga in cui cercare
			Tile[] tessere=puzz.getTessere();
			for (int i=0; i<tessere.length; i++) {
				if(tessere[i].Southern(a)){
					return tessere[i];
				}
			}
			return null;
		}

		public void run(){
			Tile[] tessere=puzz.getTessere();
			int ncol=puzz.getCollumns();
			int nrow=puzz.getRows();
			if(collumn==0){//la prima colonna procede a cercare le tessere a sud e lancia i thread che fanno la ricerca nelle righe corrispondenti
				Thread[] trd= new Thread[nrow-1];
				for (int i=1; i<nrow; i++) {
					ntessere[i*ncol]=findSouthTile(ntessere[(i-1)*ncol]);
					RowTileScout rsc=new RowTileScout(puzz,ntessere,i);
					rsc.start();
					trd[i-1]=rsc;
				}
				for (int i=0; i<trd.length; i++) {
					try{
						trd[i].join();
					}catch(InterruptedException e){
						System.err.println(e);
					}
				}
			}else{//sono un thread lanciato dalla ricerca nella prima riga e cerco le tessere nelle colonne
				for (int i=1; i<nrow; i++) {
					if(tessere[collumn+i*ncol]!=null){
						ntessere[collumn+i*ncol]=findSouthTile(ntessere[collumn+(i-1)*ncol]);
					}
				}
			}
		
		}
	}

	Tile findFirstTile(){
		for (int i=0; i<tessere.length; i++) {
			Tile a=tessere[i];
			if(a.northEmpty() && a.westEmpty()) {return a;}
		}
		return null;
	}

	public void sort(){
		if(tessere.length==0) return;
		//nuovo array dove inserire i riferimenti alle tessere nella posizione corretta
		Tile[] ntessere = new Tile[tessere.length];
		RowTileScout rscout = new RowTileScout(this,ntessere,0);//cambiare i parametri
		CollumnTileScout cscout = new CollumnTileScout(this,ntessere,0);//cambare i parametri
		ntessere[0]=findFirstTile();
		rscout.start();
		cscout.start();
		try{
			rscout.join();
			cscout.join();
		}catch(InterruptedException e){
			System.err.println(e);
		}
		//non posso ritornare finchè l'ordinamento non è terminato, uso la join thread
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
