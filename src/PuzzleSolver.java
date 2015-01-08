//import puzzsol.Puzzle;

public class PuzzleSolver {

	public static void main(String[] args) {
		String inputFile = args[0];
		String outputFile = args[1];
		Puzzle puzz= new Puzzle();
		puzz.readFromFile(inputFile);
		/*puzz.sort();*/
		puzz.writeIntoFile(outputFile);
	}

}
