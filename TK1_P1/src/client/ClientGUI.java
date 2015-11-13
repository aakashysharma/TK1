/**
 *
 */
package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.rmi.RemoteException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;

import impl.Controller;

@SuppressWarnings("serial")
public class ClientGUI extends JFrame {
	private JToolBar toolbar = null;
	private JButton btnLogin = null;
	private JButton btnLogOut = null;
	private JTextField textField = null;
	private JLabel lblLogin = null;
	private Controller controller = new Controller();
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
		setTitle("Fly Hunter Client");
		setSize(400, 400); // default size is 0,0
		setLocation(100, 200); // default is 0,0 (top left corner)
		setResizable(false);
		setBackground(Color.WHITE);
		JMenuBar menubar = new JMenuBar();
		ImageIcon icon = new ImageIcon(getClass().getResource("/images/exit.png"));

		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);

		JMenuItem eMenuItem = new JMenuItem("Exit", icon);
		eMenuItem.setMnemonic(KeyEvent.VK_E);
		eMenuItem.setToolTipText("Exit application");
		eMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});

		fileMenu.add(eMenuItem);

		menubar.add(fileMenu);

		setJMenuBar(menubar);
		controller.connectServer();
		initLoginComponent();
	}

	private void initLoginComponent() {
		JPanel panel = new JPanel(new BorderLayout());

		toolbar = new JToolBar();
		toolbar.setFloatable(false);
		panel.add(toolbar, BorderLayout.PAGE_START);

		lblLogin = new JLabel();
		textField = new JTextField(15);
		toolbar.add(textField);
		toolbar.add(lblLogin);
		toolbar.addSeparator();

		btnLogin = new JButton(" Login ");
		toolbar.add(btnLogin);
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				login();
			}
		});
		JToolBar vertical = new JToolBar(JToolBar.VERTICAL);
		vertical.setFloatable(false);
		vertical.setMargin(new Insets(10, 5, 5, 5));
		JButton selectb = new JButton("Faisal");
		selectb.setBorder(new EmptyBorder(3, 0, 3, 0));
		vertical.add(selectb);
		add(vertical, BorderLayout.WEST);
		add(panel);
	}

	private boolean login() {

		btnLogOut = new JButton("Logout");
		this.clientInfo.setPlayerName(textField.getText());
		if (controller.login(this.clientInfo)) {
			textField.setVisible(false);
			btnLogin.setVisible(false);
			lblLogin.setText("Logged in  " + textField.getText());
			lblLogin.setVisible(true);
			toolbar.add(btnLogOut);

			btnLogOut.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					logOut();
				}
			});

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
