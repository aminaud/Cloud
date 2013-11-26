package emn.llqmam.cloud.views;

import java.util.ArrayList;
import java.util.List;

import org.opennebula.client.Client;
import org.opennebula.client.host.Host;
import org.opennebula.client.vm.VirtualMachine;

import emn.llqmam.cloud.data.Vm;

public class TestVm extends Vm {

	Client client;
	Integer ID;
	String name;
	public VirtualMachine open_nebula_vm; // representation of the vm for
											// opennebula

	public String suspend() {
		return "ok";
	}

	public String resume() {
		return "ok";
	}

	public String delete() {
		return "ok";
	}

	/**
	 * Migrates the VM to the target node.
	 * 
	 * @param nodeId
	 *            The targeted node.
	 * @return A message indicating whether or not the migration has succeeded.
	 */
	public String migrate(int nodeId) {
		return "ok";
	}

	/**
	 * Checks whether or not OCA client is compatible with OpenNebula node
	 * version.
	 * 
	 * @return A boolean indicating the compatibility.
	 */
	public boolean checkCompatibility() {
		return true;
	}

	/**
	 * Retrieves the nodes.
	 * 
	 * @return A list containing all the nodes.
	 */
	public List<Host> retrieveNodesInfo() {
		List<Host> listHost = new ArrayList<>();
		return listHost;

	}

	/**
	 * Retrieves the VMs.
	 * 
	 * @return A list containing all the VMs.
	 */
	public List<Vm> retrieveVMsInfo() {
		List<Vm> listVM = new ArrayList<>();
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
		return "truc";
	}

	public void set_name(String name) {
		this.name = name;
	}

	public String get_name() {
		return this.name;
	}

	public VirtualMachine get_open_nebula_vm() {
		return this.open_nebula_vm;
	}

	public void set_open_nebula_vm(VirtualMachine open_nebula_vm) {
		this.open_nebula_vm = open_nebula_vm;
	}

	public String get_status() {
		return "LAUNCH";
	}

	public String get_host() {
		return "host";
	}

	public String get_usedUC() {
		return "12%";
	}

	public String get_usedMemory() {
		return "23%";
	}

}
