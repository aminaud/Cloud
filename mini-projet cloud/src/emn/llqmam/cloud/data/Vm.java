package emn.llqmam.cloud.data;

import org.opennebula.client.Client;

public class Vm {

	Client client;
	Integer ID;
	String name;
	
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
		return get_client().get_version().getMessage();
	}
	
	public void set_name(String name) {
		this.name = name;
	}
	
	public String get_name() {
		return this.name;
	}
}
