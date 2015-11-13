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

	/**
	 * Creates new form ApplicationWindow
	 *
	 * @throws InterruptedException
	 */
	public ApplicationWindow() {
		initComponents();

		controller.connectServer();
		System.out.println("Connection established ....");
		try {
			clientInfo = new ClientInfo();
			clientInfo.setGUI(this);
		} catch (RemoteException e) {

			e.printStackTrace();
		}

	}

	@SuppressWarnings("unchecked")
	private void initComponents() {
		InputStream input = ClassLoader.getSystemResourceAsStream("resources/a.jpg");
		Image logo = null;

		try {
			logo = ImageIO.read(input);
		} catch (IOException e) {
			e.printStackTrace();
		}

		flyImg = new ImageIcon(logo);

		flyImage = new JLabel(flyImg);
		flyImage.addMouseListener(mouseListener);

		panel = new javax.swing.JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				// g.setColor(Color.red);
				InputStream input = ClassLoader.getSystemResourceAsStream("resources/a.jpg");
				Image logo = null;
				try {
					logo = ImageIO.read(input);
				} catch (IOException e) {
					e.printStackTrace();
				}

				g.drawImage(logo, x - 2, y - 2, Color.RED, null);
				panel.repaint();
				panel.validate();
			}

		};
		btnLogin = new javax.swing.JButton();
		userNameTextField = new javax.swing.JTextField();
		btnLogout = new javax.swing.JButton();
		enterNameLabel = new javax.swing.JLabel();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTable1 = new javax.swing.JTable();
		unameLabel = new javax.swing.JLabel();
		userNameLabel = new javax.swing.JLabel();

		panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
		panel.setCursor(new java.awt.Cursor(java.awt.Cursor.CROSSHAIR_CURSOR));
		// panel.addMouseListener(mouseListener);

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

		jTable1.setModel(new javax.swing.table.DefaultTableModel(new Object[][] { { "", 0 } },
				new String[] { "User Name", "Score" }) {
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
		jScrollPane1.setViewportView(jTable1);

		unameLabel.setVisible(false);
		unameLabel.setText("User Name:");

		userNameLabel.setVisible(false);

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
								.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 287,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(40, Short.MAX_VALUE))
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
								layout.createSequentialGroup().addComponent(unameLabel)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(userNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 129,
												javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41,
										Short.MAX_VALUE).addComponent(btnLogout).addGap(28, 28, 28)))));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				javax.swing.GroupLayout.Alignment.TRAILING,
				layout.createSequentialGroup().addContainerGap().addGroup(layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(btnLogin, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(unameLabel))
						.addComponent(btnLogout, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(userNameLabel, javax.swing.GroupLayout.Alignment.TRAILING,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(enterNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 19,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(userNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								.addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, 0, 0,
										Short.MAX_VALUE)
								.addComponent(panel, javax.swing.GroupLayout.Alignment.TRAILING,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE))
						.addContainerGap()));
	}// </editor-fold>

	private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {

		login();

	}

	private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {
		logout();
	}

	// Variables declaration - do not modify
	private javax.swing.JButton btnLogin;
	private javax.swing.JButton btnLogout;
	private javax.swing.JLabel enterNameLabel;
	private javax.swing.JPanel panel;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTable jTable1;
	private javax.swing.JLabel unameLabel;
	private javax.swing.JLabel userNameLabel;
	private javax.swing.JTextField userNameTextField;
	private JLabel flyImage;
	ImageIcon flyImg;
	// End of variables declaration

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

				unameLabel.setVisible(true);
				userNameLabel.setText(getUserName());
				userNameLabel.setVisible(true);
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
		unameLabel.setVisible(false);
		userNameLabel.setVisible(false);
		btnLogout.setVisible(false);

		enterNameLabel.setVisible(true);
		userNameTextField.setVisible(true);
		userNameTextField.setText("");
		btnLogin.setVisible(true);
	}

	public String getUserName() {
		return userNameTextField.getText();
	}

	public void updateTable(Map<String, Integer> scoreMap) {

		DefaultTableModel defaultModel = (DefaultTableModel) jTable1.getModel();
		defaultModel.setRowCount(0);
		System.out.println("map size ---" + scoreMap.size());

		int i = 0;

		for (Iterator<String> it = scoreMap.keySet().iterator(); it.hasNext();) {

			System.out.println("Inner iteration number ---" + i);
			String key = it.next();
			Integer value = scoreMap.get(key);

			defaultModel.addRow(new Object[] { key, value });
			System.out.println(key + "----" + value);

			i++;

		}

	}

	public void setFlyPosition(int x, int y) {
		System.out.println("updating fly position .....");
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
