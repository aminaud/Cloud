package emn.llqmam.cloud.views;

import java.util.List;

import org.opennebula.client.host.Host;

import emn.llqmam.cloud.data.Vm;

/**
 * <p>This interface allows to communication with the view.<:p>
 * 
 * @author AM
 * @version 1.0 beta
 */
public interface IView {
	/**
	 * <p>This method allows to launch the view and display it.</p>
	 */
	public void displayConnectionDialog();
	
	/**
	 * 
	 */
	public void displayApplication(String versionOpenNebula, List<Vm> listVM, List<Host> listNode);
	
	public void updatelistVM(List<Vm> listVM);
	
	public void displayMessage(String message, int type);
}
