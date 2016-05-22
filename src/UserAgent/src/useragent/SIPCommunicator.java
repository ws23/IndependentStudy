package useragent;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/*		*
 * 	File:						useragent/SIPCommunicator.java
 * 
 * 	Use:						A communicator to communicate with SIP proxy servers. 
 * 
 * 	Update Date: 	2016. 5. 23
 * */

public class SIPCommunicator {
	
	private String address; 
	private int port; 
	private String userName; 
	
	
	public SIPCommunicator(String addr, int prt, String username){
		address = addr; 
		port = prt; 
		userName = username; 
		
		Socket client = new Socket(); 
		InetSocketAddress isa = new InetSocketAddress(address, port);
		BufferedOutputStream out; 
		BufferedInputStream in; 
		try {
			client.connect(isa, 15000);

			
			out = new BufferedOutputStream(client.getOutputStream()); 
			out.write(("REGISTER," + userName).getBytes()); 
			out.flush(); 
			
			
			
			out.close(); 
			out = null; 
			client.close(); 
			client = null; 
		}
		catch (IOException e){
			System.out.println("Socket Connection Failed. "); 
			System.out.println("IOException: " + e.toString()); 
		}
	}
}
