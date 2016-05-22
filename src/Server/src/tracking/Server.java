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
 * 	Update Date: 	2016.  5. 22
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
		
		private String createVideo(String uri, int max){
			// provider
			SearchingTree tmp = new SearchingTree(); 
			trees.add(tmp); 			
			tmp.add(uri, max); 
			return  tmp.getStreamID(); 
		}
		
		private SearchingTree findTree(String id){
			for(int i=0; i<trees.size(); i++){
				if(trees.get(i).getStreamID() == id)
					return trees.get(i); 
			}
			return null; 
		}
		
		private String findParent(String id, String uri, int max){
			// viewer 
			// to get the parent's URI
			SearchingTree tmp = findTree(id);
			String parent = tmp.add(uri, max); 
			
			return parent; 
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
							
					// get data from client ( command, uri, max, streamID )
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

				
					// parse data ( command, uri, max, streamID )
					String[] datas = data.split(",");
					int cmd = Integer.parseInt(datas[0], 10); 
					String URI = datas[1]; 
					int maxConnect = Integer.parseInt(datas[2], 10);
					String streamID = ""; 

					if(cmd == 1){
						streamID = datas[3];
					}
					// process command ( 0: provider, 1: viewer )
					String parentURI = ""; 
					if ( cmd == 0 ) {
						streamID = createVideo(URI, maxConnect); 
					}
					else if ( cmd == 1 ) {
						parentURI = findParent(streamID, URI, maxConnect);
					}
					
					// send data to client (response)
					
					out = new BufferedOutputStream(socket.getOutputStream());
					if( cmd ==0 ){
						out.write(streamID.getBytes()); 
					}
					else if ( cmd == 1 ) {
						out.write(parentURI.getBytes()); 
					} 
					out.flush(); 
					out.close(); 
					out = null; 
					
					socket.close(); 
				}
				catch(IOException e){
					System.out.println("Socket connection failed. "); 
					System.out.println("IOException: " + e.toString()); 
				}
			}
		}	
	}
