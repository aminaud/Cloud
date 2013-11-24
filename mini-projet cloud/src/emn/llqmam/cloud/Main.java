package emn.llqmam.cloud;

import emn.llqmam.cloud.application.ApplicationFactory;
import emn.llqmam.cloud.application.IApplication;



/**
 * 
 * @author AM
 * @version 1.0 beta
 */
public class Main {

	private static IApplication application;

	public static void main( String[] args ) {

		application = ApplicationFactory.getNewApplication();

		application.start();
	}
}
