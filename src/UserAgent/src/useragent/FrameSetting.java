package useragent;

/*		*
 * 	File:						useragent/FrameSetting.java
 * 
 * 	Use:						A window let user can look and change the properties.  
 * 
 * 	Update Date: 	2015. 12. 24
 * */

import java.awt.GridBagConstraints;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class FrameSetting  extends JFrame {
	private String userName; 
	private int maxConnect; 

	public FrameSetting() {
		FileSetting fileSetting = new FileSetting("usersetting"); 
		
		fileSetting.readFile();
		userName = fileSetting.getUserName(); 
		maxConnect = fileSetting.getMaxConnect(); 
		System.out.println("User Name: " + userName); 
		System.out.println("Max Connect: " + maxConnect); 
	
		this.setSize(800, 600); 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		this.setLocation(0, 0); 
		
		JLabel labelUserName = new JLabel();
		JTextField textUserName = new JTextField(); 
		JLabel labelMaxConnect = new JLabel();
		JTextField textMaxConnect = new JTextField(); 
		
		labelUserName.setText("User Name: ");
		textUserName.setText(userName); 
		textUserName.setEditable(false); 
		labelMaxConnect.setText("Maximum amount of allowable connections: "); 
		textMaxConnect.setText(String.valueOf(maxConnect)); 
		
		GridBagConstraints c0 = new GridBagConstraints(); 
		c0.gridx = 0; 
		c0.gridy = 0; 
		c0.gridwidth = 1; 
		c0.gridheight = 1; 
		c0.fill = GridBagConstraints.BOTH; 
		c0.anchor = GridBagConstraints.WEST; 
		this.add(labelUserName, c0); 
		this.add(textUserName, c0); 
		
		GridBagConstraints c1 = new GridBagConstraints(); 
		c1.gridx = 0; 
		c1.gridy = 1; 
		c1.gridwidth = 1; 
		c1.gridheight = 1; 
		c1.fill = GridBagConstraints.BOTH; 
		c1.anchor = GridBagConstraints.WEST; 
		this.add(labelMaxConnect, c1); 
		this.add(textMaxConnect, c1); 	

		
		this.setVisible(true); 
		
		
	}
	
}
