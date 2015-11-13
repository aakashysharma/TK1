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
public class GUI extends JFrame {
	JToolBar tb = null;
	JButton btnLogin = null;
	JButton btnLogOut = null;
	JTextField tf = null;
	JLabel lblLogin = null;
	Controller controller = new Controller();
	ClientInfo clientInfo = null;
	
	 public GUI() {
		 System.out.println("GUI Constructor.....");
		
			try {
				clientInfo = new ClientInfo();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			init(clientInfo);
		  }
	 
	 private void init(ClientInfo client){
		    setTitle("Fly Hunter Client");
			setSize(400,400); // default size is 0,0
			setLocation(100,200); // default is 0,0 (top left corner)
			setResizable(false);
			setBackground(Color.WHITE);
		 JMenuBar menubar = new JMenuBar();
		 ImageIcon icon = new ImageIcon(getClass().getResource("/images/exit.png"));

	        JMenu fileMenu = new JMenu("File");
	        fileMenu.setMnemonic(KeyEvent.VK_F);

	        JMenuItem eMenuItem = new JMenuItem("Exit",icon);
	        eMenuItem.setMnemonic(KeyEvent.VK_E);
	        eMenuItem.setToolTipText("Exit application");
	        eMenuItem.addActionListener(new ActionListener() {
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
	 
	 private void initLoginComponent(){
		 JPanel p = new JPanel(new BorderLayout());

	         tb = new JToolBar();
	         tb.setFloatable(false);
	        p.add(tb, BorderLayout.PAGE_START);

	        lblLogin = new JLabel();
	       tf = new JTextField(15);
	        tb.add(tf);
	        tb.add(lblLogin);
	        tb.addSeparator();
	       
	        btnLogin=new JButton(" Login ");
	        tb.add(btnLogin);
	        btnLogin.addActionListener(new ActionListener() { 
	        	  public void actionPerformed(ActionEvent e) { 
	        		 
	        		login();
	        	} 
	        	} );
	        JToolBar vertical = new JToolBar(JToolBar.VERTICAL);
	        vertical.setFloatable(false);
	        vertical.setMargin(new Insets(10, 5, 5, 5));
	        JButton selectb = new JButton("Faisal");
	        selectb.setBorder(new EmptyBorder(3, 0, 3, 0));
	        vertical.add(selectb);
	       add(vertical,BorderLayout.WEST);
	        add(p);
	 }
	 
	 private boolean login(){
		
		btnLogOut= new JButton("Log Out");
		this.clientInfo.setPlayerName(tf.getText());
		 if ( controller.login(this.clientInfo).equals("success")) {
			tf.setVisible(false);
			btnLogin.setVisible(false);
			lblLogin.setText("Logged in  "+tf.getText());
			lblLogin.setVisible(true);
			tb.add(btnLogOut);
			
			btnLogOut.addActionListener(new ActionListener() { 
	        	  public void actionPerformed(ActionEvent e) { 
	        		 
	        		logOut();
	        	} 
	        	} );
			
		}
		 
		 
		 return true;
	 }
private void logOut(){
	controller.logOut(this.clientInfo);
	tf.setVisible(true);
	btnLogin.setVisible(true);
	
	lblLogin.setVisible(false);
	btnLogOut.setVisible(false);
	tf.setText("");
	lblLogin.setText("");
	
	
}
}
