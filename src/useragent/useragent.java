package useragent;

/*	Class Name: useragent
 * 	Author: ws23 (linyutsu23@gmail.com)
 * 	Create Time: 2015. 3. 29   
 * 	Update Time: 2015. 3. 29 
 * */


import java.awt.BorderLayout;
import java.awt.CheckboxMenuItem;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javax.swing.JFrame;

import net.sf.fmj.media.RegistryDefaults;
import net.sf.fmj.utility.ClasspathChecker;
import net.sf.fmj.utility.JmfUtility;
import net.sf.fmj.utility.LoggerSingleton;
import net.sf.fmj.utility.OSUtils;





/* This is the main application of this project. */


public class useragent {
	private static final Logger logger = LoggerSingleton.logger;	


	private void runPreprocess(String[] args){
		// TODO: get some information
		/*	Read the configuration file if exist.  
		 * 		- Get the register server information. 
		 * 		- Get URI
		 * 		- Get the maximum number of forwarding client. 
		 * */
					
		/*	Connect to SIP register server
		 * 		- Register the SIP UA information. 
		 * 
		 * */
	}
	
	private JFrame frame; 
	private PlayerPanel playerPanel; 
	
	private void runInterface(String[] args){
		frame = new JFrame("User Agent"); 
		frame.setLocation(0, 0);
		frame.setSize(1280, 720); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

		Container contentPane = frame.getContentPane(); 
		playerPanel = new PlayerPanel(); 
		contentPane.add(playerPanel); 
		
		frame.setMenuBar(getMenuBar()); 
		
		// Resize frame whenever new component is added
		playerPanel.getVideoPanel().addContainerListener(
	            new ContainerListener() {
	                public void componentAdded(ContainerEvent e) {
	                    frame.pack();
	                }
	                public void componentRemoved(ContainerEvent e) {
	                    frame.pack();
	                }
	            }
	        );
		
		frame.setVisible(true); 
	}

	private final MenuBar getMenuBar(){
		MenuBar menuBar = new MenuBar();
		
		// Stream
		final Menu menuStream = new Menu(); 
		menuStream.setLabel("Stream"); 
		menuBar.add(menuStream); 
		
			// Stream > Create - Create a new real-time media streaming & create the seed.
			final MenuItem menuItemCreate = new MenuItem("Create"); 
			menuItemCreate.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						// playerPanel.onOpenCaptureDevice();
					}
				}
			); 
			menuStream.add(menuItemCreate); 
			
		menuStream.addSeparator(); 
			
	  		// Stream > Open - Open a seed to watch the stream.
			final MenuItem menuItemOpen = new MenuItem("Open"); 
			menuItemOpen.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						//playerPanel.onOpenFile();
						playerPanel.onReceiveRTP();
					}
				}
			);
			menuStream.add(menuItemOpen); 
			
			// Stream > Close - Close the stream which is watching.
			final MenuItem menuItemClose = new MenuItem("Close"); 
			menuItemClose.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						// Action Event
					}
				}
			);
			menuStream.add(menuItemClose); 
		
		menuStream.addSeparator(); 
		
	  		// Stream > Exit - To exit software.			
			final MenuItem menuItemExit = new MenuItem("Exit"); 
			menuItemExit.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						onExit(); 
					}
				}
			); 
			menuStream.add(menuItemExit); 
		
		// Control
		final Menu menuControl = new Menu(); 
		menuControl.setLabel("Control"); 
		menuBar.add(menuControl); 			
			
			// Control > Play - To play the stream if the seed was read. (When open a seed will auto to play it. )
			final MenuItem menuItemPlay = new MenuItem("Play"); 
			menuItemPlay.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						// Action Event
					}
				}
			);			
			menuControl.add(menuItemPlay); 
			
	  		// Control > Pause - To pause the stream if the streaming is player. (But the receive & forwarding will continue. )
			final MenuItem menuItemPause = new MenuItem("Pause"); 
			menuItemPause.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						// Action Event
					}
				}
			);			
			menuControl.add(menuItemPause); 			
			
	  		// Control > Stop - To stop connection. (The receive & forwarding will stop. )
			final MenuItem menuItemStop = new MenuItem("Stop"); 
			menuItemStop.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						// Action Event
					}
				}
			);			
			menuControl.add(menuItemStop); 			
			
	
		
		// Other
			final Menu menuOther = new Menu(); 
			menuOther.setLabel("Other"); 
			menuBar.add(menuOther); 	
			
	  		// Other > Help - To display the help pages.
			final MenuItem menuItemHelp = new MenuItem("Help"); 
			menuItemHelp.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						// Action Event
					}
				}
			);			
			menuOther.add(menuItemHelp); 				
		
		menuOther.addSeparator(); 
			
	  		// Other > Configuration - To display the configuration page.
			final MenuItem menuItemConfig = new MenuItem("Configuration"); 
			menuItemConfig.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						// Action Event
					}
				}
			);			
			menuOther.add(menuItemConfig); 				
			
		menuOther.addSeparator(); 
	  		// Other > About - To display 
			final MenuItem menuItemAbout = new MenuItem("About"); 
			menuItemAbout.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						// Action Event
					}
				}
			);			
			menuOther.add(menuItemAbout); 		 
		
		return menuBar; 
	}
	
	private void onExit(){
		frame.dispose();
	}
	
	
	public static void main(String[] args) {
	      	useragent main = new useragent();
	      	main.runPreprocess(args); 
	      	main.runInterface(args);
	}
}
