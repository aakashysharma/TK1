package client;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import server.ApplicationWindow;

public class Client {

	public Client() {

		init();
	}

	private void init() {
		ClientGUI clientGUI = new ClientGUI();
		clientGUI.setVisible(true);
	}

	public static void main(String[] args) {
		ApplicationWindow clientWindow = new ApplicationWindow();
		JFrame frame = new JFrame();
		frame.setTitle("Welcome to fly hunter game");
		frame.setResizable(false);
		frame.setLayout(new BorderLayout());
		frame.add(clientWindow, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
		frame.requestFocus();
		frame.setLocationRelativeTo(null);
	}
}
