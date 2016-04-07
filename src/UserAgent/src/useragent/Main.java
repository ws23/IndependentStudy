package useragent;

import javax.swing.JFrame;

/*		*
 * 	File:						useragent/Main.java
 * 
 * 	Use:						The start point of useragent package. 
 * 								It is the client's (provider's and viewer's) interface.  
 * 
 * 	Update Date: 	2016. 3. 14
 * */

public class Main {
	/* Start of Constant  Variable*/
	private static int defaultMaxConnect = 3; 
	private static String userName; 
	private static int maxConnect; 
	
	/* End of Constant Variable */
/*
	public void tmp() throws IOException {
        String host = "";
        int port = 5987;
        Socket socket = null;
        Scanner consoleInput = new Scanner( System.in );
        System.out.println("請輸入Server端位址");
        host = consoleInput.nextLine();
        try
        {
            socket = new Socket( host, port );
            DataInputStream input = null;
            DataOutputStream output = null;
            
            try
            {
                input = new DataInputStream( socket.getInputStream() );
                output = new DataOutputStream( socket.getOutputStream() );
                while ( true )
                {
                    System.out.println( input.readUTF() );
                    break;
                }
            }
            catch ( IOException e )
            {
            }
            finally 
            {
                if ( input != null )
                    input.close();
                if ( output != null )
                    output.close();
            }
        }
        catch ( IOException e )
        {
            e.printStackTrace();
        }
        finally
        {
            if ( socket != null )
                socket.close();
            if ( consoleInput != null )
                consoleInput.close();
        }

	}
	*/
	
	private static String genUserName(){
		return ""; 
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
		FileSetting fileSetting = new FileSetting(".usersetting"); 
		
		// Read the setting file ( userName, maxConnect )
		/*if (fileSetting.readFile()  != false){
			setInfo(fileSetting.getUserName(), fileSetting.getMaxConnect()); 
		}
		else {
			setInfo(); 
			
		}*/
		// 發出註冊訊息
		  	// 與 Proxy Server 建立連線，並發出註冊訊息
		  	// 收到 Proxy Server 的權限索取 response 後同意，再次發出註冊訊息
		  	// 收到 Proxy Server response 後結束與
		
		// 開啟使用者介面
		JFrame ui = new FrameMain();

		
		
	}

}
