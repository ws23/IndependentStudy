package useragent;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/*		*
 * 	File:						useragent/P2PTracker.java
 * 
 * 	Use:						A communicator to communicate with P2P tracking server. 
 * 
 * 	Update Date: 	2016. 5. 22
 * */

public class P2PTracker {
	private String address; 
	private int port;
	private String URI; 
	private int maxConnect; 
	private boolean status; 
	private String returnData; 
	
	
	public String getData(){
		return returnData; 
	}
	
	public boolean getState(){
		return status; 
	}
	
	// provider
	public P2PTracker(String addr, int prt, String uri, int max){
		address = addr; 
		port = prt; 
		URI = uri; 
		maxConnect = max; 
		status = true;
		
		Socket socket = new Socket(); 
		InetSocketAddress isa = new InetSocketAddress(this.address, this.port);
		BufferedInputStream in; 
		BufferedOutputStream out; 
		
		try {
			socket.connect(isa, 15000); 
			// send
			out = new BufferedOutputStream(socket.getOutputStream()); 
			out.write(("CREATE," + URI + "," + String.valueOf(maxConnect) ).getBytes());
			System.out.println("CREATE," + URI + "," + String.valueOf(maxConnect)); 
			out.flush(); 
			
			
			// receive
			in = new BufferedInputStream(socket.getInputStream()); 
			byte[] b = new byte[1024]; 
			int length =  in.read(b); 
			String data = new String(b, 0, length); 
			System.out.println("The data I got: " + data); 
			
			
			// parse data ( ERROR / CREATE,ACK,ID )
			String datas[] = data.split(",");
			if (datas[0].equals("ERROR")){
				status = false; 
			}
			else if(datas[0].equals("CREATE") && datas[1].equals("ACK")){
				 returnData = datas[2]; 
			}
			else {
				status = false; 
			}
			
			out.close(); 
			in.close();
			socket.close();
			out = null; 
			in = null;
			socket = null; 
			
		}
		catch(IOException e){
			System.out.println("Socket connection failed. "); 
			System.out.println("IOException: " + e.toString()); 
		}
	}
	
	// viewer
	public P2PTracker(String addr, int prt, String uri, int max, String streamID){
		address = addr; 
		port = prt; 
		URI = uri; 
		maxConnect = max; 
		status = true;
		
		Socket socket = new Socket(); 
		InetSocketAddress isa = new InetSocketAddress(this.address, this.port); 
		
		try {
			socket.connect(isa, 15000); 
			// send
			BufferedOutputStream out = new BufferedOutputStream(socket.getOutputStream()); 
			out.write(("JOIN," + URI + "," + String.valueOf(maxConnect) + "," + streamID ).getBytes()); 
			out.flush(); 
			
			// receive
			BufferedInputStream in = new BufferedInputStream(socket.getInputStream()); 
			byte[] b = new byte[1024];  
			int length = in.read(b); 
			String data = new String(b, 0, length); 
			System.out.println("The data I got: " + data); 
			

			
			// parse data ( JOIN / CREATE,ACK,ID )
			String datas[] = data.split(",");
			if (datas[0].equals("ERROR")){
				status = false; 
			}
			else if(datas[0].equals("JOIN") && datas[1].equals("ACK")){
				 returnData = datas[2]; 
			}
			else {
				status = false; 
			}
			
			out.close(); 
			in.close();
			socket.close();
			out = null; 
			in = null;
			socket = null; 
		}
		catch(IOException e){
			System.out.println("Socket connection failed. "); 
			System.out.println("IOException: " + e.toString()); 
		}
		
		
		
	}
	
}
