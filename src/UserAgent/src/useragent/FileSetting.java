package useragent;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/*		*
 * 	File:						useragent/FileSetting.java
 * 
 * 	Use:						To Process the setting file. 
 * 
 * 	Update Date: 	2016. 2. 22
 * */

public class FileSetting {
	
	private String fileName; 
	private String userName; 
	private int maxConnect; 
	
	public String getUserName(){
		return userName; 
	}
	
	public int getMaxConnect(){
		return maxConnect; 
	}
	
	public boolean createFile(){
		// TODO: create the setting file
		
		return false; 
	}
	
	// [In] 
	// [Out] Boolean: readFile success or not 
	// [Use] To read the file and store the 
	public boolean readFile() {
		// TODO: read the setting file
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName)); 
			
			String str[] = new String[3]; 
			
			str[0] = br.readLine(); 
			str[1] = br.readLine(); 
			str[2] = br.readLine(); 
			
			if(str[2] == null && str[1] != null && str[0] != null) {
				userName = str[0]; 
				maxConnect = Integer.parseInt(str[1]);
				br.close();
				return true; 
			}	
			br.close(); 
			return false; 
		} catch (FileNotFoundException e) {
			createFile(); 
			e.printStackTrace();
			return false; 
		} catch (IOException e) {
			e.printStackTrace();
			return false; 
		} 	
	}

	
	@SuppressWarnings("resource")
	// [In] 
	// [Out] Boolean: writeFile success or not 
	// [Use] To write the variables into file
	public boolean writeFile(){
		// TODO: write into the setting file
		FileOutputStream fstream; 
		
		try{
			BufferedWriter bw = new BufferedWriter(new FileWriter(fileName)); 
			bw.write(userName);
			bw.newLine(); 
			bw.write(maxConnect); 
			bw.newLine(); 
		} catch (IOException e) {
			e.printStackTrace();
			return false; 
		}
		
		return false; 
	}
	
	public FileSetting(String name){
		fileName = name; 
		userName = ""; 
		maxConnect = 0; 
	}
}
