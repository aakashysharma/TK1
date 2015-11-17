package server;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import client.ClientInfo;
import impl.Controller;

public class ApplicationWindow extends javax.swing.JPanel {
	private int x;
	private int y;
	private Controller controller = new Controller();
	private ClientInfo clientInfo = null;
	private boolean isLoggedIn;
	// Variables declaration - do not modify
	private javax.swing.JButton btnLogin;
	private javax.swing.JButton btnLogout;
	private javax.swing.JLabel enterNameLabel;
	private javax.swing.JPanel panel;
	private javax.swing.JScrollPane scoreScrollPanel;
	private javax.swing.JTable scoreTable;
	private javax.swing.JLabel lblUsername;
	private javax.swing.JLabel lblCurrentUsername;
	private javax.swing.JTextField userNameTextField;
	private JLabel flyImage;
	private ImageIcon flyImg;

	/**
	 * Creates new form ApplicationWindow
	 *
	 * @throws InterruptedException
	 */
	public ApplicationWindow() {
		initComponents();
		controller.connectServer();
		try {
			clientInfo = new ClientInfo();
			clientInfo.setGUI(this);
		} catch (RemoteException e) {

			e.printStackTrace();
		}

	}

	@SuppressWarnings("unchecked")
	private void initComponents() {
		InputStream input = ClassLoader.getSystemResourceAsStream("resources/fly.jpg");
		Image fly = null;

		try {
			fly = ImageIO.read(input);
		} catch (IOException e) {
			e.printStackTrace();
		}

		flyImg = new ImageIcon(fly);

		flyImage = new JLabel(flyImg);
		flyImage.addMouseListener(mouseListener);

		panel = new javax.swing.JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				InputStream input = ClassLoader.getSystemResourceAsStream("resources/fly.jpg");
				Image fly = null;
				try {
					fly = ImageIO.read(input);
				} catch (IOException e) {
					e.printStackTrace();
				}

				g.drawImage(fly, x - 2, y - 2, Color.RED, null);
				panel.repaint();
				panel.validate();
			}

		};
		btnLogin = new javax.swing.JButton();
		userNameTextField = new javax.swing.JTextField();
		btnLogout = new javax.swing.JButton();
		enterNameLabel = new javax.swing.JLabel();
		scoreScrollPanel = new javax.swing.JScrollPane();
		scoreTable = new javax.swing.JTable();
		lblUsername = new javax.swing.JLabel();
		lblCurrentUsername = new javax.swing.JLabel();

		panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
		panel.setCursor(new java.awt.Cursor(java.awt.Cursor.CROSSHAIR_CURSOR));

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(panel);
		panel.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 558, Short.MAX_VALUE));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 421, Short.MAX_VALUE));

		btnLogin.setText("Login");
		btnLogin.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnLoginActionPerformed(evt);
			}
		});

		btnLogout.setVisible(false);
		btnLogout.setText("Logout");
		btnLogout.setOpaque(false);
		btnLogout.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnLogoutActionPerformed(evt);
			}
		});

		enterNameLabel.setText("Enter Name:");

		scoreTable.setModel(new javax.swing.table.DefaultTableModel(new Object[][] { { "", 0 } },
				new String[] { "Username", "Score" }) {
			Class[] types = new Class[] { java.lang.String.class, java.lang.Integer.class };
			boolean[] canEdit = new boolean[] { false, false };

			@Override
			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}

			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		scoreScrollPanel.setViewportView(scoreTable);

		lblUsername.setVisible(true);
		lblUsername.setText("Username:");

		lblCurrentUsername.setVisible(false);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup().addComponent(enterNameLabel)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(userNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 155,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(18, 18, 18).addComponent(btnLogin))
								.addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addComponent(scoreScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 287,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(40, Short.MAX_VALUE))
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
								layout.createSequentialGroup().addComponent(lblUsername)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(lblCurrentUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 129,
												javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41,
										Short.MAX_VALUE).addComponent(btnLogout).addGap(28, 28, 28)))));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				javax.swing.GroupLayout.Alignment.TRAILING,
				layout.createSequentialGroup().addContainerGap()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(btnLogin, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblUsername))
								.addComponent(btnLogout, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblCurrentUsername, javax.swing.GroupLayout.Alignment.TRAILING,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(enterNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 19,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(userNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								.addComponent(scoreScrollPanel, javax.swing.GroupLayout.Alignment.TRAILING, 0, 0,
										Short.MAX_VALUE)
								.addComponent(panel, javax.swing.GroupLayout.Alignment.TRAILING,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE))
						.addContainerGap()));
	}

	private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {

		login();

	}

	private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {
		logout();
	}

	public void login() {
		if (getUserName().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "Please enter user name !", null, JOptionPane.INFORMATION_MESSAGE);

		} else {
			clientInfo.setPlayerName(getUserName().trim());
			if (controller.login(this.clientInfo)) {
				isLoggedIn = true;
				enterNameLabel.setVisible(false);
				userNameTextField.setVisible(false);
				btnLogin.setVisible(false);

				lblUsername.setVisible(true);
				lblCurrentUsername.setText(getUserName());
				lblCurrentUsername.setVisible(true);
				btnLogout.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(null, "Server not up and running or user already exists on server", null,
						JOptionPane.INFORMATION_MESSAGE);
			}

		}
	}

	public void logout() {
		isLoggedIn = false;
		controller.logOut(this.clientInfo);
		lblUsername.setVisible(false);
		lblCurrentUsername.setVisible(false);
		btnLogout.setVisible(false);

		enterNameLabel.setVisible(true);
		userNameTextField.setVisible(true);
		userNameTextField.setText("");
		btnLogin.setVisible(true);
	}

	public String getUserName() {
		return userNameTextField.getText();
	}

	public void updateScoresTable(Map<String, Integer> scoreMap) {

		DefaultTableModel defaultModel = (DefaultTableModel) scoreTable.getModel();
		defaultModel.setRowCount(0);
		int i = 0;
		for (Iterator<String> iteratorOverScoreTable = scoreMap.keySet().iterator(); iteratorOverScoreTable
				.hasNext();) {
			String key = iteratorOverScoreTable.next();
			Integer value = scoreMap.get(key);
			defaultModel.addRow(new Object[] { key, value });
			i++;
		}

	}

	public void setFlyPosition(int x, int y) {
		this.x = x;
		this.y = y;

		flyImage.setBounds(x, y, flyImg.getIconWidth(), flyImg.getIconHeight());
		panel.add(flyImage);

	}

	private MouseListener mouseListener = new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getButton() == MouseEvent.BUTTON1) {
				huntFly();
			}
		}
	};

	private void huntFly() {
		if (isLoggedIn) {
			controller.huntFly(this.clientInfo);
		} else {
			JOptionPane.showMessageDialog(null, "Please login to play!", null, JOptionPane.INFORMATION_MESSAGE);
		}

	}
}
