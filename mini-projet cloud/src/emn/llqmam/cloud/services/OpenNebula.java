package emn.llqmam.cloud.services;

import org.opennebula.client.Client;
import org.opennebula.client.OneResponse;
import org.opennebula.client.vm.VirtualMachine;

import emn.llqmam.cloud.data.Vm;

public class OpenNebula implements IOpenNebula {

	/*
	 * login into nodes return HashMap<Client, int> (1 = connected, 0=failed)
	 */
	public Vm login(String vm_name, String user, String password, String ip_address) {

		Client oneClient;
		Vm vm = new Vm();
		vm.set_name(vm_name);

		try {
			oneClient = new Client(user+":"+password, "http://" + ip_address + "/RPC2");
			String vmTemplate = "NAME     = " + vm_name
					+ "    CPU = 0.1    MEMORY = 64\n";

			System.out.print("Logging in ... ");
			vm.set_client(oneClient);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return vm;
	}
}