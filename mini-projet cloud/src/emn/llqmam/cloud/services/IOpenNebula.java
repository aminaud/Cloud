package emn.llqmam.cloud.services;

import emn.llqmam.cloud.data.Vm;

public interface IOpenNebula {

	public Vm login(String vm_name, String user, String ip_address);
}
