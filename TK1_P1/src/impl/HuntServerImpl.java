/**
 * 
 */
package impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import client.HuntClientInterface;
import server.HuntingServer;

/**
 * @param <CallbackClientIntf>
 *
 */
@SuppressWarnings("serial")
public class HuntServerImpl extends UnicastRemoteObject implements HuntingServer {
	protected ArrayList<HuntClientInterface> clients = null;
	private Map<String, Integer> scoreMap = null;

	public HuntServerImpl() throws RemoteException {
		scoreMap = new HashMap<String, Integer>();
		clients = new ArrayList<HuntClientInterface>();
	}

	@Override
	public String login(HuntClientInterface client) throws RemoteException {

		System.out.println("Login Request...." + client);

		if (null != scoreMap.get(client.getPlayerName())) {
			return "User Name Already exist please choose another !";
		}

		scoreMap.put(client.getPlayerName(), 0);
		clients.add(client);

		updateClients();
		return "success";

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see server.HuntingServer#logout(java.lang.String)
	 */
	@Override
	public void logout(HuntClientInterface client) throws RemoteException {

		scoreMap.remove(client.getPlayerName());
		System.out.println("index of client -- " + clients.indexOf(client));
		clients.remove(client);
		updateClients();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tu.edu.tk1.HuntServer#huntFly(java.lang.String)
	 */
	@Override
	public void huntFly(HuntClientInterface client) throws RemoteException {
		Integer score = 0;
		if (null != scoreMap.get(client.getPlayerName())) {
			score = (Integer) scoreMap.get(client.getPlayerName());
			scoreMap.put(client.getPlayerName(), ++score);
		} else {
			scoreMap.put(client.getPlayerName(), ++score);
		}

		updateClients();
	}

	public void updateClients() {
		Iterator<HuntClientInterface> it = clients.iterator();
		Random random = new Random();
		int x = random.nextInt(500);
		int y = random.nextInt(400);
		while (it.hasNext()) {
			HuntClientInterface client = (HuntClientInterface) it.next();
			try {

				client.updateMe(x, y, this.scoreMap);
			} catch (Exception e) {

				System.out.println("Could not update client " + client.toString());
			}
		}

	}

}
