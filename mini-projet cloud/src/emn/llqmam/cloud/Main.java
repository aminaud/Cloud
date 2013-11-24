package emn.llqmam.cloud;

import emn.llqmam.cloud.application.ApplicationFactory;
import emn.llqmam.cloud.application.IApplication;
import emn.llqmam.cloud.data.Vm;
import emn.llqmam.cloud.services.OpenNebula;

/**
 * 
 * @author AM
 * @version 1.0 beta
 */
public class Main {

	private static IApplication application;

	private static String oneadmin = "oneadmin:5bd7fcf39891cdff5896e10a79b7cd9e";

	public static void main(String[] args) {

		application = ApplicationFactory.getNewApplication();

		application.start();

		OpenNebula on = new OpenNebula();
		Vm vm = new Vm();
		vm = on.login("node1_1", oneadmin, "169.254.166.243:2633");
		System.out.println(vm.get_version() + " " + vm.get_name());
		System.out.println(vm.checkCompatibility());
	}
}
