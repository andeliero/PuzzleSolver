package puzzsol;
/*rappresentazione generica di una tessera di un puzzle rettangolare*/

interface Tile{
	Object north();
	Object south();
	Object east();
	Object west();
	boolean northEmpty();
	boolean southEmpty();
	boolean eastEmpty();
	boolean westEmpty();
	boolean Northern(Tile til);
	boolean Southern(Tile til);
	boolean Western(Tile til);
	boolean Eastern(Tile til);
	String getRawTile();//restituisce la stringa della tessera
}
