import puzzsol.*;
import java.rmi.*;

public class PuzzleSolverServer{
	private static final String HOST = "localhost";

	public static void main(String[] args) throws Exception {
		String host = args[0];
		ResPuzzleAlg rpa = new ResPuzzleAlgImpl();
		String rmiObjName = "rmi://" + host + "/Resolutor";
		Naming.rebind(rmiObjName,rpa);
		System.out.println("Server Pronto!");
	}
}