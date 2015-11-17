/**
 *
 */
package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import impl.Controller;

@SuppressWarnings("serial")
public class ClientGUI extends JFrame {
	private JToolBar toolbar = null;
	private JButton btnLogin = null;
	private JButton btnLogOut = null;
	private JTextField textField = null;
	private JLabel lblLogin = null;
	private Controller controller = new Controller();
	private boolean serverUpAndRunning;
	private ClientInfo clientInfo = null;

	public ClientGUI() {
		try {
			clientInfo = new ClientInfo();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		init(clientInfo);
	}

	private void init(ClientInfo client) {
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setTitle("Fly Hunter Client");
		setSize(640, 640);
		setLocation(100, 200);
		setResizable(false);
		setBackground(Color.GRAY);
		initLoginComponent();
	}

	private void initLoginComponent() {
		JPanel panel = new JPanel(new BorderLayout());
		toolbar = new JToolBar();
		toolbar.setFloatable(false);
		panel.add(toolbar, BorderLayout.PAGE_START);

		lblLogin = new JLabel();
		textField = new JTextField(20);
		toolbar.add(textField);
		toolbar.add(lblLogin);
		toolbar.addSeparator();

		btnLogin = new JButton("Login");
		toolbar.add(btnLogin);
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
		add(panel);
	}

	private boolean login() {

		btnLogOut = new JButton("Logout");
		this.clientInfo.setPlayerName(textField.getText());
		// if (!serverUpAndRunning) {
		// serverUpAndRunning = controller.connectServer();
		// }
		if (controller.login(this.clientInfo)) {
			textField.setVisible(false);
			btnLogin.setVisible(false);
			lblLogin.setText("Welcome " + textField.getText());
			lblLogin.setVisible(true);
			toolbar.add(lblLogin);
			toolbar.add(btnLogOut);

			btnLogOut.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					logOut();
				}
			});
			add(toolbar);
		}
		return true;
	}

	private void logOut() {
		controller.logOut(this.clientInfo);
		textField.setVisible(true);
		btnLogin.setVisible(true);

		lblLogin.setVisible(false);
		btnLogOut.setVisible(false);
		textField.setText("");
		lblLogin.setText("");

	}
}
