package useragent;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/*		*
 * 	File:						useragent/SIPCommunicator.java
 * 
 * 	Use:						A communicator to communicate with SIP proxy servers. 
 * 
 * 	Update Date: 	2016. 3. 28
 * */

public class SIPCommunicator {

	
	private String address; 
	private int port; 
	
	public SIPCommunicator(String addr, int prt){
		address = addr; 
		port = prt; 
		
		Socket client = new Socket(); 
		InetSocketAddress isa = new InetSocketAddress(address, port); 
		try {
			client.connect(isa, 10000); 
			BufferedOutputStream out = new BufferedOutputStream(client.getOutputStream()); 
			out.write("Send From UserAgent. ".getBytes()); 
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
