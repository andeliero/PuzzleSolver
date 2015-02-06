package puzzsol;
import java.rmi.*;

public interface ResPuzzleAlg extends Remote{

	public Tile[] sort(Tile[] ts, int r, int c) throws RemoteException;

}