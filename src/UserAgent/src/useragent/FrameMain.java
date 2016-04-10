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
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.*;

import net.sf.fmj.utility.URLUtils;




public class FrameMain extends JFrame{
	
	private PlayerPanel playerPanel; 
	private String  URI; 
	
	
	private MenuBar setMainMenuBar(){
		
		MenuBar menuBar = new MenuBar(); 
		menuBar.setFont(new Font("sans", Font.BOLD, 16)); 
		
		// Stream
		Menu menuStream = new Menu(); 
		menuStream.setLabel("Stream"); 
		menuBar.add(menuStream); 
		
		// Stream > Create - Create a new real-time media streaming & create the seed.
		MenuItem menuItemCreate = new MenuItem("Create"); 
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
		MenuItem menuItemOpen = new MenuItem("Open"); 
		menuItemOpen.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e){
					// TODO: Action Event		
					onOpenFile();
					
					//playerPanel.onReceiveRTP();
				}
			}
			
		);
		menuStream.add(menuItemOpen); 
		
		// Stream > Close - Close the stream which is watching.
		MenuItem menuItemClose = new MenuItem("Close"); 
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
	/*
		// Control
		Menu menuControl = new Menu(); 
		menuControl.setLabel("Control"); 
		menuBar.add(menuControl); 			
			
		// Control > Play - To play the stream if the seed was read. (When open a seed will auto to play it. )
		MenuItem menuItemPlay = new MenuItem("Play"); 
		menuItemPlay.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e){
					// TODO: Action Event
				}
			}
		);			
		menuControl.add(menuItemPlay); 
		
  		// Control > Pause - To pause the stream if the streaming is player. (But the receive & forwarding will continue. )
		MenuItem menuItemPause = new MenuItem("Pause"); 
		menuItemPause.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e){
					// TODO: Action Event
				}
			}
		);			
		menuControl.add(menuItemPause); 			
		
  		// Control > Stop - To stop connection. (The receive & forwarding will stop. )
		MenuItem menuItemStop = new MenuItem("Stop"); 
		menuItemStop.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e){
					// TODO: Action Event
				}
			}
		);			
		menuControl.add(menuItemStop); 			
		*/
		// Other
		Menu menuOther = new Menu(); 
		menuOther.setLabel("Other"); 
		menuBar.add(menuOther); 	
		
  		// Other > Help - To display the help pages.
		MenuItem menuItemHelp = new MenuItem("Help"); 
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
		MenuItem menuItemConfig = new MenuItem("Configuration"); 
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
		MenuItem menuItemAbout = new MenuItem("About"); 
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
	
	private String readP2PFileToGetURI(String fileName) {
		// TODO: read the P2P file
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName)); 
			
			String str = br.readLine(); 
			
			br.close(); 
			return str; 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null; 
		} catch (IOException e) {
			e.printStackTrace();
			return null; 
		} 	
	}
	
	private void onOpenFile()
	{
		final JFileChooser chooser = new JFileChooser();
		if (chooser.showOpenDialog(FrameMain.this) ==JFileChooser.APPROVE_OPTION) {
			final String urlStr = URLUtils.createUrlStr(chooser.getSelectedFile());
			System.out.println("urlStr = " + urlStr); 
			
			URI = readP2PFileToGetURI(urlStr);

		}
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
