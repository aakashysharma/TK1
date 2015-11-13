package impl;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import client.ClientInfo;
import server.HuntServer;

public class Controller {

	HuntServer server = null;

	public boolean connectServer(){
		
		try {
		
		    Registry registry = LocateRegistry.getRegistry(1300);
		    server = (HuntServer) registry.lookup("HuntServer");
		    
		
		} catch (Exception e) {
		    System.err.println("Connection Error exception: " + e.toString());
		    e.printStackTrace();
		    return false;
		}
		return true;
	}
	
	public String login(ClientInfo client) {
		String status ;
		try {

			if (null != server) {
				status = server.login(client);
				System.out.println("Login Status --"+status);
			}else{
				status = "Server not connected..";
			}
			 
			 
			

		} catch (RemoteException e) {
			
			e.printStackTrace();
			return "Server not connected..";
		}
		return status;
	}
	
	public boolean logOut(ClientInfo client){
		 try {
				server.logout(client);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		
	}
	
	public int huntFly(ClientInfo client){
		 try {
				server.huntFly(client);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 1;
	}
	
	
}
