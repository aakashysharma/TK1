package server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import impl.Controller;
import impl.HuntServerImpl;

public class Server {

	public static void main(String args[]) {
		try {
			HuntServerImpl huntServer = new HuntServerImpl();

			Registry registry = LocateRegistry.createRegistry(Controller.getPortNumber());
			registry.rebind("HuntingServer", huntServer);
			System.err.println("Server up and running");
		} catch (Exception e) {
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
		}
	}
}
