import java.rmi.*;
import java.rmi.server.*;

public class ResPuzzleAlgImpl extends UnicastRemoteObject implements ResPuzzleAlg{
	Tile[] tessere=new Tile[0];
	int rows=0;
	int collumns=0;

	public int getRows(){
		return rows;
	}

	public int getCollumns(){
		return collumns;
	}

	public Tile[] getTessere(){
		return tessere;
	}

	public ResPuzzleAlgImpl() throws RemoteException {}

	class RowTileScout extends Thread{
		private ResPuzzleAlgImpl puzz;
		private Tile[] ntessere;
		private int row=-1;//a seconda del numero cambia il comportamento

		RowTileScout(ResPuzzleAlgImpl p, Tile[] dest, int r){
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
					if(ntessere[row*ncol+i]==null){
						ntessere[row*ncol+i]=findEastTile(ntessere[row*ncol+i-1]);
					}
				}
			}
		}
	}

	class CollumnTileScout extends Thread{
		private ResPuzzleAlgImpl puzz;
		private Tile[] ntessere;
		private int collumn=-1;//a seconda del numero cambia il comportamento

		CollumnTileScout(ResPuzzleAlgImpl p, Tile[] dest, int c){
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
					if(tessere[collumn+i*ncol]==null){
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


	public Tile[] sort(Tile[] ts, int r, int c){
		tessere=ts;
		rows=r;
		collumns=c;

		if(tessere.length==0) return tessere;
		//nuovo array dove inserire i riferimenti alle tessere nella posizione corretta
		Tile[] ntessere = new Tile[tessere.length];
		RowTileScout rscout = new RowTileScout(this,ntessere,0);
		CollumnTileScout cscout = new CollumnTileScout(this,ntessere,0);
		ntessere[0]=findFirstTile();
		rscout.start();
		cscout.start();
		try{
			rscout.join();
			cscout.join();
		}catch(InterruptedException e){
			System.err.println(e);
		}
		return ntessere;
	}

}