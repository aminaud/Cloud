package emn.llqmam.cloud;

import org.opennebula.client.host.Host;
import org.opennebula.client.vm.VirtualMachine;

import emn.llqmam.cloud.application.ApplicationFactory;
import emn.llqmam.cloud.application.IApplication;
import emn.llqmam.cloud.data.Vm;
import emn.llqmam.cloud.services.OpenNebula;
import emn.llqmam.cloud.tools.Tools;

/**
 * 
 * @author AM
 * @version 1.0 beta    	
 */
public class Main {

	private static IApplication application;

	public static void main(String[] args) {

		application = ApplicationFactory.getNewApplication();

		application.start();

		
	}
}
