package useragent;

/*		*
 * 	File:						useragent/FrameMain.java
 * 
 * 	Use:						The main Frame of useragent. 
 * 
 * 	Update Date: 	2016. 4. 8
 * */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;




public class FrameMain extends JFrame{
	
	private PlayerPanel playerPanel; 
	
	
	private MenuBar setMainMenuBar(){
		
		MenuBar menuBar = new MenuBar(); 
		menuBar.setFont(new Font("sans", Font.BOLD, 16)); 
		
		// Stream
		Menu menuStream = new Menu(); 
		menuStream.setLabel("Stream"); 
		menuBar.add(menuStream); 
		
		// Stream > Create - Create a new real-time media streaming & create the seed.
		final MenuItem menuItemCreate = new MenuItem("Create"); 
		menuItemCreate.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e){
					// TODO: Action Event
					 playerPanel.onOpenCaptureDevice();
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
					// TODO: Action Event		
					playerPanel.onOpenFile();
					//playerPanel.onReceiveRTP();
				}
			}
			
		);
		menuStream.add(menuItemOpen); 
		
		// Stream > Close - Close the stream which is watching.
		final MenuItem menuItemClose = new MenuItem("Close"); 
		menuItemClose.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e){
					// TODO: Action Event
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
					// TODO: Action Event
				}
			}
		);			
		menuControl.add(menuItemPlay); 
		
  		// Control > Pause - To pause the stream if the streaming is player. (But the receive & forwarding will continue. )
		final MenuItem menuItemPause = new MenuItem("Pause"); 
		menuItemPause.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e){
					// TODO: Action Event
				}
			}
		);			
		menuControl.add(menuItemPause); 			
		
  		// Control > Stop - To stop connection. (The receive & forwarding will stop. )
		final MenuItem menuItemStop = new MenuItem("Stop"); 
		menuItemStop.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e){
					// TODO: Action Event
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
					// TODO: Action Event
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
					// TODO: Action Event
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
						// TODO: Action Event
					}
				}
			);			
		menuOther.add(menuItemAbout); 		 
	
		return menuBar; 

	}
	
	private void onExit(){
		this.dispose();
	}
	
	public FrameMain(){	
		
		this.setSize(1280, 720); 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(0, 0); 
		
		
		this.setMenuBar(setMainMenuBar()); 
		
		playerPanel = new PlayerPanel(); 
		this.add(playerPanel); 
		
		
		
		
		this.setVisible(true); 
		
	}
}
