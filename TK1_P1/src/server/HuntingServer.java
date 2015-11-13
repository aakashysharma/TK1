/**
 *
 */
package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import client.HuntingClient;

public interface HuntingServer extends Remote {
	boolean login(HuntingClient client) throws RemoteException;

	void logout(HuntingClient client) throws RemoteException;

	void huntFly(HuntingClient client) throws RemoteException;

}
