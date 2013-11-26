package emn.llqmam.cloud.tools;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Tools {

	/*
	 * Retrieve specific ip adress @properties folder
	 */
	public static String get_IP() {
		String ligne ="";
		String fichier ="./properties/open_nebula_ip";

		//read ip from file
		try{
			InputStream ips=new FileInputStream(fichier); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			
			ligne=br.readLine();
			br.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
		return ligne;
	}
}
