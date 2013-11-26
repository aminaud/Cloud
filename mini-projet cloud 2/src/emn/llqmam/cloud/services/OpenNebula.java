package emn.llqmam.cloud.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.opennebula.client.Client;
import org.opennebula.client.OneResponse;
import org.opennebula.client.OneSystem;
import org.opennebula.client.host.Host;
import org.opennebula.client.host.HostPool;
import org.opennebula.client.vm.VirtualMachine;
import org.opennebula.client.vm.VirtualMachinePool;

import emn.llqmam.cloud.data.Vm;

public class OpenNebula implements IOpenNebula {
	
	Client client;

	/*
	 * login into nodes return HashMap<Client, int> (1 = connected, 0=failed)
	 */
	public void login(String user, String password, String ip_address) {

		try {
			this.client = new Client(user+":"+password, "http://" + ip_address + "/RPC2");
			System.out.print("Logging in...");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	public String suspendVM(VirtualMachine vm) {
		String result = "";
		OneResponse response = vm.suspend();
		if (response.isError()) {
			result = "failed to suspend the vm: " + response.getErrorMessage();
		} else {
			result = response.getMessage();
		}
		return result;
	}
	
	
	public String resumeVM(VirtualMachine vm) {
		String result = "";
		OneResponse response = vm.resume();
		if (response.isError()) {
			result = "failed to resume the vm: " + response.getErrorMessage();
		} else {
			result = response.getMessage();
		}
		return result;
	}
	
	public String deleteVM(VirtualMachine vm) {
		String result = "";
		OneResponse response = vm.delete();
		if (response.isError()) {
			result = "failed to delete the vm: " + response.getErrorMessage();
		} else {
			result = response.getMessage();
		}
		return result;
	}
	
	/**
	 * Migrates the VM to the target node.
	 * 
	 * @param nodeId
	 *            The targeted node.
	 * @return A message indicating whether or not the migration has succeeded.
	 */
	public String migrateVM(VirtualMachine vm, int nodeId) {
		String result = "";
		OneResponse response = vm.migrate(nodeId);
		if (response.isError()) {
			result = "failed to migrate the vm: " + response.getErrorMessage();
		} else {
			result = response.getMessage();
		}
		return result;
	}
	
	/**
	 * Checks whether or not OCA client is compatible with OpenNebula node
	 * version.
	 * 
	 * @return A boolean indicating the compatibility.
	 */
	public boolean checkCompatibilityOCA() {
		OneSystem os = new OneSystem(client);
		return os.compatibleVersion();
	}
	
	/**
	 * Retrieves the nodes.
	 * 
	 * @return A list containing all the nodes.
	 */
	public List<Host> retrieveHosts() {
		List<Host> listHost = new ArrayList<>();
		HostPool hp = new HostPool(client);
		hp.info();
		Iterator<Host> i = hp.iterator();
		while (i.hasNext()) {
			Host h = i.next();
			listHost.add(h);
		}
		return listHost;

	}
	
	/**
	 * Retrieves the VMs.
	 * 
	 * @return A list containing all the VMs.
	 */
	public List<Vm> retrieveVMs() {
		List<Vm> listVM = new ArrayList<>();
		VirtualMachinePool vmp = new VirtualMachinePool(client, VirtualMachinePool.ALL_VM);
		vmp.info();
		Iterator<VirtualMachine> i = vmp.iterator();
		while (i.hasNext()) {
			VirtualMachine vm = i.next();
			Vm myVm = new Vm(vm);
			listVM.add(myVm);
		}
		return listVM;
	}
	
	/**
	 * Gets OpenNebula node version.
	 * 
	 * @return The version of the OpenNebula node.
	 */
	public String getOpenNebulaVersion() {
		// return this.client.get_version().getMessage();
		OneSystem os = new OneSystem(client);
		return os.getOnedVersion().getMessage();
	}

}