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
					
					// TODO: 開啟擷取影片之 Device 
					playerPanel.onOpenCaptureDevice();
					
					// TODO: 加入 P2P 網路
				
					
					// TODO: 將 P2P Server 位址與頻道 ID 製作成種子檔案
					
					
					
					
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
					
					// 選擇 Seed 後取得來源之 URI 
					onOpenFileAndGetURI();
					
					// TODO: 以 SIP 方式建立連線，並取得 RTP 串流資訊
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
					// TODO: 傳送 BYE 訊息給轉播端（SIP）
					
					// TODO: 離開 P2P 網路
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
					// TODO: 顯示使用教學視窗
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
					// TODO: 打開更改設定之視窗
					FrameSetting frameSetting =  new FrameSetting(); 
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
		// read the P2P file
		try { 
			BufferedReader br = new BufferedReader(new FileReader(fileName)); 
			String p2pIP = br.readLine(); 
			String p2pID = br.readLine();
			String tmp = br.readLine(); 
			br.close(); 
			if(tmp == null){
				// TODO: 與 P2P Server 建立連線，取得轉播 URI
				
				return "ws23@134.208.3.15"; // 暫時先回這個 URI 
			}
			else 
				return null;  
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null; 
		} catch (IOException e) {
			e.printStackTrace();
			return null; 
		} 	
	}
	
	private String onOpenFileAndGetURI()
	{
		final JFileChooser chooser = new JFileChooser();
		if (chooser.showOpenDialog(FrameMain.this) ==JFileChooser.APPROVE_OPTION) {
			final String urlStr = URLUtils.createUrlStr(chooser.getSelectedFile());
			String urlFileName = urlStr.substring(7);  
			System.out.println("urlFileName = " + urlFileName); 
			
			URI = readP2PFileToGetURI(urlFileName);
			System.out.println("URI = " + URI); 
			
			return URI; 
		}
		return null;
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
