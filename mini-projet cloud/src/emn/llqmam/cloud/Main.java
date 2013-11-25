package emn.llqmam.cloud;

import emn.llqmam.cloud.application.ApplicationFactory;
import emn.llqmam.cloud.application.IApplication;
import emn.llqmam.cloud.services.OpenNebula;



/**
 * 
 * @author AM
 * @version 1.0 beta
 */
public class Main {

	private static IApplication application;
	private static String IPLOIC = "";
	private static String IPAURELIE = "169.254.222.102";
	private static String IPQUENTIN = "169.254.166.243";

	public static void main( String[] args ) {

		application = ApplicationFactory.getNewApplication();

		application.start();
		
		OpenNebula on = new OpenNebula();
		on.login("node1_1", getIP()+":2633");
	}
	
	public static String getIP() {
		return IPAURELIE;
	}
}
