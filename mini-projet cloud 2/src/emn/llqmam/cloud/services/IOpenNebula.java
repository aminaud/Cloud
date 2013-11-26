package emn.llqmam.cloud.services;

import java.util.List;

import org.opennebula.client.host.Host;
import org.opennebula.client.vm.VirtualMachine;

import emn.llqmam.cloud.data.Vm;

public interface IOpenNebula {

	public void login(String user, String password, String ip_address);
	
	public String suspendVM(VirtualMachine vm);
	
	public String resumeVM(VirtualMachine vm);
	
	public String deleteVM(VirtualMachine vm);
	
	public String migrateVM(VirtualMachine vm, int nodeId);
	
	public boolean checkCompatibilityOCA();
	
	public List<Host> retrieveHosts();
	
	public List<Vm> retrieveVMs();
	
	public String getOpenNebulaVersion();
	
}