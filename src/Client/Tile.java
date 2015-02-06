package puzzsol;

import java.io.Serializable;
/*rappresentazione generica di una tessera di un puzzle rettangolare*/

abstract public class Tile implements Serializable{

	private String Id;
	private String Nord;
	private String Sud;
	private String Est;
	private String Ovest;	

	Tile(String i, String n, String e, String s, String o){
		Id=i; Nord=n; Est=e; Sud=s; Ovest=o;
	}

	public String north(){
		return Nord;
	}

	public String south(){
		return Sud;
	}

	public String east(){
		return Est;
	}

	public String west(){
		return Ovest;
	}

	public boolean northEmpty(){
		return Nord.equals("VUOTO");
	}

	public boolean southEmpty(){
		return Sud.equals("VUOTO");
	}

	public boolean eastEmpty(){
		return Est.equals("VUOTO");
	}

	public boolean westEmpty(){
		return Ovest.equals("VUOTO");
	}

	public boolean Northern(Tile til){
		if(Id.equals(til.Nord)) return true;
		return false;
	}

	public boolean Southern(Tile til){
		if(Id.equals(til.Sud)) return true;
		return false;
	}

	public boolean Western(Tile til){
		if(Id.equals(til.Ovest)) return true;
		return false;
	}

	public boolean Eastern(Tile til){
		if(Id.equals(til.Est)) return true;
		return false;
	}

	abstract String getRawTile();//restituisce la stringa della tessera
}
