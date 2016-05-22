package proxy;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/*		*
 * 	File:						proxy/Server.java
 * 
 * 	Use:						The SIP proxy server. 
 * 								It will listen to wait User Agent to connect.
 * 								And pass the packet to proxy/Client.java to do something    
 * 
 * 	Update Date: 	2016.  5. 23
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
				int length = in.read(b); 
				String data = new String(b, 0, length); 
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