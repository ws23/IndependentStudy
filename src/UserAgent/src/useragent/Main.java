package useragent;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/*		*
 * 	File:						useragent/Main.java
 * 
 * 	Use:						The start point of useragent package. 
 * 								It is the client's (provider's and viewer's) interface.  
 * 
 * 	Update Date: 	2016. 5. 22
 * */

public class Main {
	/* Start of Constant  Variable*/
	private static int defaultMaxConnect = 2; 
	private static String userName; 
	private static int maxConnect; 
	
	private static String genUserName(){
		// To generate the SIP user's name
		String username = "";
		Random ran = new Random(); 
		username = String.valueOf((ran.nextInt(100000000)));  
		return username; 
	}
	
	private static void setInfo(String user, int max){
		userName = user; 
		maxConnect = max; 
	}
	
	private static void setInfo(){
		userName = genUserName(); 
		maxConnect = defaultMaxConnect; 
	}
	

	public static void main(String[] args) {
		FileSetting fileSetting = new FileSetting("usersetting");
		
		
		// Read the setting file ( userName, maxConnect )
		if (fileSetting.readFile()  != false){
			setInfo(fileSetting.getUserName(), fileSetting.getMaxConnect()); 
		}
		else {
			setInfo();  
		}
		// 發出註冊訊息
		
		
		  	// 與 Proxy Server 建立連線，並發出註冊訊息	

		
		  	// 收到 Proxy Server 的權限索取 response 後同意，再次發出註冊訊息
		
		  	// 收到 Proxy Server response 後結束與 Proxy Server 的連線
		
		
		// 註冊完畢，寫入資訊
		fileSetting.setMaxConnect(maxConnect); 
		fileSetting.setUserName(userName); 
		fileSetting.writeFile(); 

		// 開啟使用者介面
		JFrame ui = new FrameMain(userName, maxConnect);


		
	}

}
