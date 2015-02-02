//import puzzsol.Puzzle;

import java.rmi.*;

public class PuzzleSolverClient {
	private static final String HOST = "localhost";

	public static void main(String[] args) throws Exception {
		String inputFile = args[0];
		String outputFile = args[1];
		Puzzle puzz= new Puzzle();
		puzz.readFromFile(inputFile);
		try{
			ResPuzzleAlg rpa = (ResPuzzleAlg)Naming.lookup("rmi://"+HOST+"/Resolutor");
			puzz.sort(rpa);
		}
		catch(ConnectException e){
			System.out.println("problemi di connesione al server");
		}
		catch(Exception exc){
			exc.printStackTrace();
		}
		puzz.writeIntoFile(outputFile);
	}

}
