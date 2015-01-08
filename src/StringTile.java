//package puzzsol;
/*rappresentazione generica di una tessera del puzzle*/

class StringTile implements Tile{
	private String Lbl;
	private String Id;
	private String Nord;
	private String Sud;
	private String Est;
	private String Ovest;	

	StringTile(String i, String l, String n, String s, String e, String o){
		Id=i; Lbl=l; Nord=n; Sud=s; Est=e; Ovest=o;
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
		return Nord.isEmpty();
	}

	public boolean southEmpty(){
		return Sud.isEmpty();
	}

	public boolean eastEmpty(){
		return Est.isEmpty();
	}

	public boolean westEmpty(){
		return Ovest.isEmpty();
	}

	public boolean Northern(Tile til){
		if(!(til instanceof StringTile)) return false;
		StringTile stil=(StringTile)til;
		if(Id.equals(stil.Nord)) return true;
		return false;
	}

	public boolean Southern(Tile til){
		if(!(til instanceof StringTile)) return false;
		StringTile stil=(StringTile)til;
		if(Id.equals(stil.Sud)) return true;
		return false;
	}

	public boolean Western(Tile til){
		if(!(til instanceof StringTile)) return false;
		StringTile stil=(StringTile)til;
		if(Id.equals(stil.Ovest)) return true;
		return false;
	}

	public boolean Eastern(Tile til){
		if(!(til instanceof StringTile)) return false;
		StringTile stil=(StringTile)til;
		if(Id.equals(stil.Est)) return true;
		return false;
	}

	public String getRawTile(){
		return Id+"\t"+Lbl+"\t"+Nord+"\t"+Est+"\t"+Sud+"\t"+Ovest+"\n";
	}
}
