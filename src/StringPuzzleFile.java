package puzzsol;
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
    				size++;
				}
    		}
		}catch(IOException e){
    		System.err.println(e);
		}
	}

	Tile getTile(int n){
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
		String idPezzo=arrtxt[0];
		String carattere=arrtxt[1];
		String idNord=arrtxt[2];
		String idEst=arrtxt[3];
		String idSud=arrtxt[4];
		String idOvest=arrtxt[5];
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

