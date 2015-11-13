package server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import impl.HuntServerImpl;

public class Server {

	 public static void main(String args[]) {
		// System.setSecurityManager( new RMISecurityManager() );
			try {
				HuntServerImpl huntServer = new HuntServerImpl();
			  
			  
			    Registry registry = LocateRegistry.createRegistry(1300);
			    registry.rebind("HuntServer", huntServer);

			    System.err.println("Server ready");
			} catch (Exception e) {
			    System.err.println("Server exception: " + e.toString());
			    e.printStackTrace();
			}
		    }
}
