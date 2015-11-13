package server;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ServerGUI extends JFrame {

	JButton btnStart = null;
	JButton btnShutdown = null;
	JPanel panel = null;
	Server server ;
	
	
	public ServerGUI() 	throws HeadlessException {
		super();
		this.setResizable(false);
		this.btnStart = new JButton("Run Server");
		this.btnShutdown =new JButton("Shut Down Server");
		this.btnStart.setVisible(true);
		this.btnShutdown.setVisible(true);
		this.btnShutdown.setEnabled(false);
		panel = new JPanel();
	    panel.setLayout( new BorderLayout() );
	  
	    JPanel subPanel = new JPanel();

	    subPanel.add( btnStart);
	    subPanel.add( btnShutdown);
	    panel.add(subPanel, BorderLayout.NORTH);
		this.add(panel);
		startServer();
		 btnStart.addActionListener(new ActionListener() { 
		   	  public void actionPerformed(ActionEvent e) { 
		   		 
		   		startServer();
		   	} 
		   	} );
		 
		 btnShutdown.addActionListener(new ActionListener() { 
		   	  public void actionPerformed(ActionEvent e) { 
		   		 
		   		stopServer();
		   	} 
		   	} );
		
	}

	private void startServer(){
		server = new Server();
		//server.start();
		btnStart.setEnabled(false);
		btnShutdown.setEnabled(true);
		
	}

	private void stopServer(){
		if (null != server) {
			//server.stopServer();
			server = null;
			btnStart.setEnabled(true);
			btnShutdown.setEnabled(false);
		}else 
			JOptionPane.showMessageDialog(null, "Please start server first!", null, JOptionPane.INFORMATION_MESSAGE); 
		
		
	}
	
	  public static void main(String[] args) {
		  ServerGUI serverGUI= new ServerGUI();
			serverGUI.setSize(250, 70);
			serverGUI.setVisible(true);
	  }

}
