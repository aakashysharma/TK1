package impl;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import client.ClientInfo;
import server.HuntingServer;

public class Controller {

	HuntingServer server = null;

	public boolean connectServer() {

		try {

			Registry registry = LocateRegistry.getRegistry(8300);
			server = (HuntingServer) registry.lookup("HuntingServer");

		} catch (Exception e) {
			System.err.println("Connection Error exception: " + e.toString());
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean login(ClientInfo client) {

		try {
			if (server != null) {
				return server.login(client);
			}
		} catch (RemoteException e) {

			e.printStackTrace();
			return false;
		}
		return false;

	}

	public boolean logOut(ClientInfo client) {
		try {
			server.logout(client);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;

	}

	public int huntFly(ClientInfo client) {
		try {
			server.huntFly(client);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 1;
	}

}
