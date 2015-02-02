import java.rmi.*;

public class PuzzleSolverServer{
	private static final String HOST = "localhost";

	public static void main(String[] args) throws Exception {
		ResPuzzleAlg rpa = new ResPuzzleAlgImpl();
		String rmiObjName = "rmi://" + HOST + "/Resolutor";
		Naming.rebind(rmiObjName,rpa);
		System.out.println("Server Pronto!");
	}
}