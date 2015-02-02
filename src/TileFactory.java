package puzzsol;

class TileFactory{

	public static Tile getTile(TileType type,Object[] data){
		Tile tile=null;
		switch(type){
		case STRING:
			String[] sdata= (String[])data;
			tile=new StringTile(sdata[0],sdata[1],sdata[2],sdata[3],sdata[4],sdata[5]);
		default:
				//lancia ecezzione
			break;
		}
		return tile;
	}

}
