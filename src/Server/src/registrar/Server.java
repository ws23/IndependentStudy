package registrar;

import java.io.*;
import java.lang.*; 
import java.net.*;

/*		*
 * 	File:						registrar/Server.java
 * 
 * 	Use:						The SIP registrar server. 
 * 								It will listen to wait Proxy Server or User Agent to connect.    
 * 
 * 	Update Date: 	2016.  3. 28
 * */

public class Server extends java.lang.Thread {
	private boolean outServer = false; 
	private ServerSocket server; 
	private int serverPort; 
	
	public Server(int port){
		try {
			serverPort = port; 
			server = new ServerSocket(serverPort);
		}
		catch (IOException e) {
			System.out.println("Socket start failed. "); 
			System.out.println("IOException: " + e.toString()); 
		}
	}
	
	public void run(){
		Socket socket; 
		BufferedInputStream in; 
		
		System.out.println("Server start!!"); 
		
		while(!outServer){
			socket = null; 
			try{
				synchronized(server){
					socket = server.accept(); 
				}
				System.out.println("Connect: InetAddress = " + socket.getInetAddress()); 
				socket.setSoTimeout(15000); 
				
				in = new BufferedInputStream(socket.getInputStream()); 
				byte[] b = new byte[1024]; 
				String data = ""; 
				int length; 
				while((length = in.read(b)) > 0){
					data += new String(b, 0, length); 
				}
				
				System.out.println("The data I got: " + data); 
				in.close(); 
				in = null; 
				socket.close(); 
			}
			catch(IOException e){
				System.out.println("Socket connection failed. "); 
				System.out.println("IOException: " + e.toString()); 
			}
		}
	}
	
		
}
