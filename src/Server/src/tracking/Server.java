package tracking;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/*		*
 * 	File:						tracking/Server.java
 * 
 * 	Use:						The P2P tracking server. 
 * 								It will maintain the p2p network searching tree.     
 * 
 * 	Update Date: 	2016.  5. 21
 * */

public class Server extends java.lang.Thread {
		private boolean outServer = false; 
		private ServerSocket server; 
		private int serverPort; 
		
		private ArrayList<SearchingTree> trees; 
		
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
		
		private String createVideo(){
			// provider
			SearchingTree tmp = new SearchingTree(); 
			trees.add(tmp); 			
			return  tmp.getStreamID(); 
		}
		
		private SearchingTree findTree(String id){
			for(int i=0; i<trees.size(); i++){
				if(trees.get(i).getStreamID() == id)
					return trees.get(i); 
			}
			return null; 
		}
		
		private String findParent(String id){
			// viewer 
			// to get the parent's URI
			SearchingTree tmp = findTree(id);  
			
			return true; 
		}
		
		private boolean joinP2P(String id){
			// viewer
			// TODO: 
			
			
			return true; 
		}
		
		public void run(){
			Socket socket; 
			BufferedInputStream in; 
			BufferedOutputStream out; 
			
			System.out.println("Server start!!"); 
			
			while(!outServer){
				socket = null; 
				try{
					synchronized(server){
						socket = server.accept(); 
					}
					System.out.println("Connect: InetAddress = " + socket.getInetAddress()); 
					socket.setSoTimeout(15000); 
					
					// TODO: 
					
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
