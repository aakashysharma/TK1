/**
 * 
 */
package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import client.HuntClientInterface;

public interface HuntingServer extends Remote {
	String login(HuntClientInterface client) throws RemoteException;

	void logout(HuntClientInterface client) throws RemoteException;

	void huntFly(HuntClientInterface client) throws RemoteException;

}
