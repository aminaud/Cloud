package emn.llqmam.cloud.data;

import org.opennebula.client.vm.VirtualMachine;

public class Vm {

	private String ID 			= "";
	private String name 		= "";
	private String status 		= "";
	private String lcmStatus 	= "";
	private String hostname 	= "";
	private String hostID		= "";
	private String usedUC 		= "";
	private String usedMemory 	= "";
	private VirtualMachine onVM		; // representation of the vm for opennebula

	public Vm (VirtualMachine vm) {
		this.ID 		= onVM.getId() + "";
		this.name 		= onVM.getName();
		this.status 	= onVM.stateStr();
		this.lcmStatus 	= onVM.lcmStateStr();
		this.hostname	= onVM.xpath("HISTORY_RECORDS/HISTORY[last()]/HOSTNAME");
		this.hostID		= onVM.xpath("HISTORY_RECORDS/HISTORY[last()]/HID");
		this.usedUC		= onVM.xpath("TEMPLATE/CPU");
		this.usedMemory = onVM.xpath("TEMPLATE/MEMORY");
	}
	
	public String displayName () {
		return name + " (" + ID + ")";
	}
	
	public String displayStatus () {
		return status + " / "+ lcmStatus;
	}
	
	public String displayHost () {
		return hostname + " (" + hostID + ")";
	}
	
	public String getID() {
		return this.ID;
	}
	
	public VirtualMachine getOnVM() {
		return this.onVM;
	}

	public void setUsedUC(String usedUC) {
		this.usedUC = usedUC;
	}
	
	public String getUsedUC() {
		return this.usedUC;
	}

	public String getUsedMemory() {
		return this.usedMemory;
	}

}
