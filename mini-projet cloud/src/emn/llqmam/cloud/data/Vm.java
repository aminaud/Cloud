package emn.llqmam.cloud.data;

import org.opennebula.client.Client;
import org.opennebula.client.OneResponse;
import org.opennebula.client.OneSystem;
import org.opennebula.client.vm.VirtualMachine;

public class Vm {

	Client client;
	Integer ID;
	String name;
	VirtualMachine open_nebula_vm; // representation of the vm for opennebula

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

	public String get_version() {
		OneSystem os = new OneSystem(client);
		return os.getOnedVersion().getMessage();
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

	public boolean checkCompatibility() {
		OneSystem os = new OneSystem(client);
		return os.compatibleVersion();
	}
}
