package emn.llqmam.cloud.services;

import org.opennebula.client.Client;
import org.opennebula.client.OneResponse;
import org.opennebula.client.vm.VirtualMachine;

import emn.llqmam.cloud.data.Vm;

public class OpenNebula implements IOpenNebula {

	/*
	 * login into nodes
	 * return HashMap<Client, int> (1 = connected, 0=failed)
	 */
	public Vm login(String vm_name, String ip_address) {

		Client oneClient;
		Vm vm = new Vm();
		vm.set_name(vm_name);

		try
		{
			oneClient = new Client("oneadmin:5bd7fcf39891cdff5896e10a79b7cd9e", "http://"+ip_address+"/RPC2");
			String vmTemplate =
					"NAME     = "+vm_name+"    CPU = 0.1    MEMORY = 64\n";

			System.out.print("Atribution de la VM... ");
			OneResponse rc = VirtualMachine.allocate(oneClient, vmTemplate);

			if( rc.isError() )
			{
				System.out.println( "Echec!");
				throw new Exception( rc.getErrorMessage() );
			}

			// The response message is the new VM's ID
			int newVMID = Integer.parseInt(rc.getMessage());
			System.out.println("ok, ID " + newVMID + ".");
			vm.set_client(oneClient);

			// We can create a representation for the new VM, using the returned
			// VM-ID
			VirtualMachine open_nebula_vm = new VirtualMachine(newVMID, oneClient);

			// Let's hold the VM, so the scheduler won't try to deploy it
			rc = open_nebula_vm.hold();
			
			vm.set_open_nebula_vm(open_nebula_vm);
			if(rc.isError())
			{
				System.out.println("failed!");
				throw new Exception( rc.getErrorMessage() );
			}

		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}



		return vm;
	}
}