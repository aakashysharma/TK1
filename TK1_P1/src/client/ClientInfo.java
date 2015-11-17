package client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Map;

import server.ApplicationWindow;

public class ClientInfo implements HuntingClient {

	private String playerName = "";

	private int xPosition;
	private int yPostiion;
	private ArrayList<ClientInfo> otherClients = null;
	private ApplicationWindow clientWindow;

	public ClientInfo() throws RemoteException {

		super();
		UnicastRemoteObject.exportObject(this, 0);
	}

	@Override
	public void updateMe(int x, int y, Map<String, Integer> scores) throws RemoteException {
		this.xPosition = x;
		this.yPostiion = y;

		clientWindow.updateScoresTable(scores);
		clientWindow.setFlyPosition(x, y);

	}

	@Override
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

	void receiveFlyHunted(String playerName, int newPoints) {

	}

	void receiveFlyPosition(int x, int y) {

	}

	public ArrayList<ClientInfo> getClients() {
		return otherClients;
	}

	public void setClients(ArrayList<ClientInfo> clients) {
		this.otherClients = clients;
	}

	public ApplicationWindow getGui() {
		return clientWindow;
	}

	public void setGUI(ApplicationWindow gui) {
		this.clientWindow = gui;
	}

	@Override
	public String toString() {

		return this.playerName;
	}

}
