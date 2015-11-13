package client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import server.Window;


public class ClientInfo  implements HuntClientInterface {
	
	
	private String playerName = "";
	
	private int xPosition;
	private int yPostiion;
	private ArrayList<ClientInfo> clients= null;
	private Window gui ;
	
	
	
	 public ClientInfo() throws RemoteException {
		 
		super();
		UnicastRemoteObject.exportObject (this, 0);
		// TODO Auto-generated constructor stub
	}
	@Override
		public void updateMe(int x,int y,Map<String,Integer> scores) throws RemoteException {
			 this.xPosition = x;
			 this.yPostiion = y;
			
			    gui.updateTable(scores);
			    gui.setFlyPosition(x, y);
			 System.out.println("Update"+this.playerName);
			 System.out.println("Update"+this.xPosition);
			 System.out.println("Update"+this.yPostiion);
				
			
		}
	
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
	public int getxPosition() {
		return xPosition;
	}
	public void setxPosition(int xPosition) {
		this.xPosition = xPosition;
	}
	public int getyPostiion() {
		return yPostiion;
	}
	public void setyPostiion(int yPostiion) {
		this.yPostiion = yPostiion;
	}
	void receiveFlyHunted(String playerName, int newPoints){
		
	}
	void receiveFlyPosition(int x, int y){
		
	}
	public ArrayList<ClientInfo> getClients() {
		return clients;
	}
	public void setClients(ArrayList<ClientInfo> clients) {
		this.clients = clients;
	}
	
	
	
	public Window getGui() {
		return gui;
	}
	public void setGui(Window gui) {
		this.gui = gui;
	}
	@Override
	public String toString() {
		return "ClientInfo [playerName=" + playerName 
				 + ", xPosition=" + xPosition + ", yPostiion="
				+ yPostiion + ", clients=" + clients + ", getPlayerName()="
				+ ", toString()=" + super.toString() + "]";
	}
	
	
}
