import puzzsol.PuzzleFactory;
import puzzsol.PuzzleType;
import puzzsol.PuzzleInterface;

public class PuzzleSolver {

	public static void main(String[] args) {
		String inputFile = args[0];
		String outputFile = args[1];
		PuzzleInterface puzz= PuzzleFactory.getPuzzle(PuzzleType.STRING);
		puzz.readFromFile(inputFile);
		puzz.sort();
		puzz.writeIntoFile(outputFile);
	}

}
