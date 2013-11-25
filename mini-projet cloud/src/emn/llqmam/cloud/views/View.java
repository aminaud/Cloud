package emn.llqmam.cloud.views;

import java.util.List;

import org.opennebula.client.host.Host;

import emn.llqmam.cloud.application.IApplication;
import emn.llqmam.cloud.data.Vm;
import emn.llqmam.cloud.views.components.ApplicationFrame;
import emn.llqmam.cloud.views.components.ConnectDialog;

/**
 *
 * @author AM
 * @version 1.0 beta
 */
public class View implements IView {
	
	private IApplication application;
	private String name;
	private ConnectDialog connectDialog;
	private ApplicationFrame frame;
	
	public View (IApplication application, String name) {
		this.application = application;
		this.name = name;
	}


	public void displayConnectionDialog() {
		if (frame != null)
			frame.showFrame(false);
		
		connectDialog = new ConnectDialog(frame, name, application);
		connectDialog.showDialog(true);
	}


	@Override
	public void displayApplication(String versionOpenNebula, List<Vm> listVM, List<Host> listNode) {
		// TODO listes
		if (connectDialog != null)
			connectDialog.showDialog(false);
		
		frame = new ApplicationFrame(application, versionOpenNebula);
		frame.showFrame(true);
	}


	@Override
	public void updatelistVM(List<Vm> listVM) {
		// TODO Auto-generated method stub
		
	}
	
}
