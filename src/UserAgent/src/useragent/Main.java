package useragent;

/*		*
 * 	File:						useragent/Main.java
 * 
 * 	Use:						The start point of useragent package. 
 * 								It is the client's (provider's and viewer's) interface.  
 * 
 * 	Update Date: 	2015. 12. 24
 * */

public class Main {
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
	public static void main(String[] args) {
		
	}

}
