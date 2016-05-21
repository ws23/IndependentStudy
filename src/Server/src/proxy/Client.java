package proxy;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/*		*
 * 	File:						proxy/Client.java
 * 
 * 	Use:						The SIP proxy server. 
 * 								It will according the SIP method to tranfer the packet to registrar server or redirect server								
 * 
 * 	Update Date: 	2016.  3. 28
 * */


public class Client {

	public Client(String addr, int prt){
		String address = addr; 
		int port = prt; 
		
		Socket client = new Socket(); 
		InetSocketAddress isa = new InetSocketAddress(address, port); 
		
		try{
			client.connect(isa, 10000); 
			BufferedOutputStream out = new BufferedOutputStream(client.getOutputStream()); 
			out.write("Send From Client".getBytes()); 
			out.flush(); 
			out.close(); 
			out = null; 
			client.close(); 
			client = null; 
		}
		catch(IOException e){
			System.out.println("Socket connection falied. "); 
			System.out.println("IOException: " + e.toString()); 
		}
	}	
}
