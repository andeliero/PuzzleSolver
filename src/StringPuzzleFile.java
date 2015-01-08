//package puzzsol;
import java.io.*;

class StringPuzzleFile extends PuzzleFile{

	
	//definire un'eccezione per incompatibilità di file
	public StringPuzzleFile(String file){
		super(file);
	}

	void parse(){
		//controllo il contenuto del file che sia nel 
		//formato corretto per un puzzle rettangolare
		//quindi so a priori com'è costituito un tassello
		try{
			BufferedReader reader = new BufferedReader(new FileReader(this));
			String line = null;
			while((line = reader.readLine()) != null){
				if(!line.isEmpty()){
    			String[] arrtxt=line.split("\t");//essendo separati da un carattere di tabulazione
				//potrei fare un controllo + ferreo sui tasselli
				//if(arrtxt.length!=6) throw File non compatibile;
				//[0]id_pezzo [1]carattere [2]id_nord [3]id_est [4]id_sud [5]id_ovest
				String idPezzo=new String("");
				String carattere=new String("");
				String idNord=new String("");
				if(arrtxt.length>=3) idNord=arrtxt[2];
				String idEst=new String("");
				if(arrtxt.length>=4) idEst=arrtxt[3];
				String idSud=new String("");
				if(arrtxt.length>=5) idSud=arrtxt[4];
				String idOvest=new String("");
				if(arrtxt.length>=6) idOvest=arrtxt[5];
				if(idNord.isEmpty())	++collumn;
				if(idEst.isEmpty())	++row;
				System.out.println(row+" "+collumn);
				}
    		}
		}catch(IOException e){
    		System.err.println(e);
		}
	}

	Tile getTile(int n){
		//if(n>size()) throw Errore;
		String line = null;
		try{
			BufferedReader reader = new BufferedReader(new FileReader(this));
			int i=0;
			while(i!=n){
				line = reader.readLine();
				++i;
			}
		}catch(IOException e){
			System.err.println(e);
		}
		String[] arrtxt = line.split("\t");
		String idPezzo=new String("");
		idPezzo=arrtxt[0];
		String carattere=new String("");
		carattere=arrtxt[1];
		String idNord=new String("");
		if(arrtxt.length>=3) idNord=arrtxt[2];
		String idEst=new String("");
		if(arrtxt.length>=4) idEst=arrtxt[3];
		String idSud=new String("");
		if(arrtxt.length>=5) idSud=arrtxt[4];
		String idOvest=new String("");
		if(arrtxt.length>=6) idOvest=arrtxt[5];
		String[] arrtil= {idPezzo,carattere,idNord,idEst,idSud,idOvest};
		Tile foo=TileFactory.getTile(TileType.STRING,arrtil);
		return foo;
	}

	void writeContent(String content){
    	try{
			BufferedWriter writer = new BufferedWriter(new FileWriter(this));
    		writer.write(content);
			writer.close();
    	}catch(IOException e){
    		System.err.println(e);
    	}
    }
}

