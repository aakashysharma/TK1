/**
 * 
 */
package client;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

public interface HuntClientInterface extends Remote {

	public void updateMe(int x, int y, Map<String, Integer> scores) throws RemoteException;

	public String getPlayerName() throws RemoteException;
}
