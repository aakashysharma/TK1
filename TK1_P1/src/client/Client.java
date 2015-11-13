package client;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import server.ApplicationWindow;

public class Client {

	public Client() {
		System.out.println("New Client....");
		initGUI();
	}

	private void initGUI() {
		ClientGUI clientGUI = new ClientGUI();
		clientGUI.setVisible(true);
	}

	public static void main(String[] args) {
		ApplicationWindow clientWindow = new ApplicationWindow();
		JFrame mainFrame = new JFrame();

		mainFrame.setResizable(false);
		mainFrame.setLayout(new BorderLayout());
		mainFrame.add(clientWindow, BorderLayout.CENTER);
		mainFrame.pack();
		mainFrame.setVisible(true);

	}

}
