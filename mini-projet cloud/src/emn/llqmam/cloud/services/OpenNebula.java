package emn.llqmam.cloud.services;

import org.opennebula.client.Client;
import org.opennebula.client.OneResponse;
import org.opennebula.client.vm.VirtualMachine;

import emn.llqmam.cloud.data.Vm;

public class OpenNebula implements IOpenNebula {

	/*
	 * login into nodes return HashMap<Client, int> (1 = connected, 0=failed)
	 */
	public Vm login(String user, String password, String ip_address) {

		Client oneClient;
		Vm vm = new Vm();


		try {
			oneClient = new Client(user+":"+password, "http://" + ip_address + "/RPC2");
			
			System.out.print("Logging in ... ");
			vm.set_client(oneClient);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return vm;
	}
}