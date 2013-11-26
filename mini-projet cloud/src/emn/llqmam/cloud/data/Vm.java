package emn.llqmam.cloud.data;

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

public class Vm {

	Client client;
	Integer ID;
	String name;
	public VirtualMachine openNebulaVm; // representation of the vm for
										// opennebula

	public void setClient(Client client) {
		this.client = client;
	}

	public Client getClient() {
		return this.client;
	}

	public int getID() {
		return this.ID;
	}

	public void setID(int id) {
		this.ID = id;
	}

	/**
	 * Gets OpenNebula node version.
	 * 
	 * @return The version of the OpenNebula node.
	 */
	public String getVersion() {
		// return this.client.getVersion().getMessage();
		OneSystem os = new OneSystem(client);
		return os.getOnedVersion().getMessage();
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public VirtualMachine getOpenNebulaVm() {
		return this.openNebulaVm;
	}

	public void setOpenNebulaVm(VirtualMachine openNebulaVm) {
		this.openNebulaVm = openNebulaVm;
	}

	public String suspend() {
		String result = "";
		OneResponse response = this.getOpenNebulaVm().suspend();
		if (response.isError()) {
			result = "failed to suspend the vm: " + response.getErrorMessage();
		} else {
			result = response.getMessage();
		}
		return result;
	}

	public String resume() {
		String result = "";
		OneResponse response = this.getOpenNebulaVm().resume();
		if (response.isError()) {
			result = "failed to resume the vm: " + response.getErrorMessage();
		} else {
			result = response.getMessage();
		}
		return result;
	}

	public String delete() {
		String result = "";
		OneResponse response = this.getOpenNebulaVm().finalizeVM();
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
	public String migrate(int nodeId) {
		String result = "";
		OneResponse response = this.openNebulaVm.migrate(nodeId);
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
	public boolean checkCompatibility() {
		OneSystem os = new OneSystem(client);
		return os.compatibleVersion();
	}

	/**
	 * Retrieves the nodes.
	 * 
	 * @return A list containing all the nodes.
	 */
	public List<Host> retrieveNodesInfo() {
		List<Host> listHost = new ArrayList<>();
		HostPool hp = new HostPool(client);
		// Loads the xml representation of all or part of the Virtual Machines
		// in the pool.
		/* OneResponse response = */hp.info();
		// System.out.println(response.getMessage());
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
	public List<Vm> retrieveVMsInfo() {
		List<Vm> listVM = new ArrayList<>();
		VirtualMachinePool vmp = new VirtualMachinePool(client);
		// Loads the xml representation of all or part of the Virtual Machines
		// in the pool.
		/* OneResponse response = */vmp.info();
		// System.out.println(response.getMessage());
		Iterator<VirtualMachine> i = vmp.iterator();
		while (i.hasNext()) {
			VirtualMachine vm = i.next();
			Vm myVm = new Vm();
			myVm.openNebulaVm = vm;
			listVM.add(myVm);
		}
		return listVM;
	}

	public String getStatus() {
		return openNebulaVm.status();
	}

}
