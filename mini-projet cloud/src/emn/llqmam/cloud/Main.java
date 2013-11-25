package emn.llqmam.cloud;

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

		OpenNebula on = new OpenNebula();
		Vm vm = new Vm();
		vm = on.login("node1_1", "oneadmin",
				"5bd7fcf39891cdff5896e10a79b7cd9e", Tools.get_IP() + ":2633");
		System.out.println(vm.get_version() + " " + vm.get_name());
		System.out.println(vm.checkCompatibility());
		System.out.println(vm.retrieveVMsInfo());
		System.out.println(vm.retrieveNodesInfo());
	}
}
