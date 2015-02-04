package puzzsol;
/*implementazione di una tessera del puzzle con un oggetto stringa al proprio interno*/

class StringTile extends Tile{

	private String Lbl;
	
	StringTile(String i, String l, String n, String e, String s, String o){
		super(i,n,e,s,o);
		Lbl=l;
	}

	public String getRawTile(){
		return Lbl;
	}
}
