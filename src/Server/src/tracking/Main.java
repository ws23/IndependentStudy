package tracking;

import registrar.Server;

/*		*
 * 	File:						tracking/Main.java
 * 
 * 	Use:						The start point of tracking package. 
 * 								It is a P2P tracking server. 
 * 
 * 	Update Date: 	2016. 5. 21
 * */

public class Main {

	public static void main(String[] args) {
		(new Server(13579)).start(); 
	}
}

