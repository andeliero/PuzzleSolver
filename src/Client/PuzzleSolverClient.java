import puzzsol.PuzzleFactory;
import puzzsol.PuzzleType;
import puzzsol.PuzzleInterface;

import java.rmi.*;

public class PuzzleSolverClient {

	public static void main(String[] args) throws Exception {
		String inputFile = args[0];
		String outputFile = args[1];
		String host = args[2];
		PuzzleInterface puzz= PuzzleFactory.getPuzzle(PuzzleType.STRING);
		puzz.readFromFile(inputFile);
		puzz.sort(host);
		puzz.writeIntoFile(outputFile);
	}

}
