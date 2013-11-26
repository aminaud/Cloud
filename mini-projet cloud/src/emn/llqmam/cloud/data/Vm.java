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
	public VirtualMachine open_nebula_vm; // representation of the vm for
											// opennebula

	public String suspend() {
		String result = "";
		OneResponse response = this.get_open_nebula_vm().suspend();
		if (response.isError()) {
			result = "failed to suspend the vm: " + response.getErrorMessage();
		} else {
			result = response.getMessage();
		}
		return result;
	}

	public String resume() {
		String result = "";
		OneResponse response = this.get_open_nebula_vm().resume();
		if (response.isError()) {
			result = "failed to resume the vm: " + response.getErrorMessage();
		} else {
			result = response.getMessage();
		}
		return result;
	}

	public String delete() {
		String result = "";
		OneResponse response = this.get_open_nebula_vm().finalizeVM();
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
		OneResponse response = this.open_nebula_vm.migrate(nodeId);
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
			myVm.open_nebula_vm = vm;
			listVM.add(myVm);
		}
		return listVM;
	}

	public void set_client(Client client) {
		this.client = client;
	}

	public Client get_client() {
		return this.client;
	}

	public int get_ID() {
		return this.ID;
	}

	public void set_ID(int id) {
		this.ID = id;
	}

	/**
	 * Gets OpenNebula node version.
	 * 
	 * @return The version of the OpenNebula node.
	 */
	public String get_version() {
		// return this.client.get_version().getMessage();
		OneSystem os = new OneSystem(client);
		return os.getOnedVersion().getMessage();
	}

	public void set_name(String name) {
		this.name = name;
	}

	public String get_name() {
		return open_nebula_vm.getName();
	}

	public VirtualMachine get_open_nebula_vm() {
		return this.open_nebula_vm;
	}

	public void set_open_nebula_vm(VirtualMachine open_nebula_vm) {
		this.open_nebula_vm = open_nebula_vm;
	}

	public String get_status() {
		return open_nebula_vm.status();
	}

	public String get_host() {
		return open_nebula_vm.xpath("HISTORY_RECORDS/HISTORY[last()]/HOSTNAME")
				+ " " + open_nebula_vm.xpath("HISTORY_RECORDS/HISTORY[last()]/HID");
	}

	public String get_usedUC() {
		return open_nebula_vm.xpath("TEMPLATE/CPU");
	}

	public String get_usedMemory() {
		return open_nebula_vm.xpath("TEMPLATE/MEMORY");
	}

}
