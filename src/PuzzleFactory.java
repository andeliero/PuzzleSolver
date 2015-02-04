package puzzsol;

public class PuzzleFactory{
	public static PuzzleInterface getPuzzle(PuzzleType type){
		PuzzleInterface pi = null;
		switch(type){
			case STRING:
				pi=new Puzzle();
			default:
				//lancia ecezzione
			break;
		}
		return pi;
	}
}