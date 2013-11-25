package emn.llqmam.cloud.application;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.opennebula.client.host.Host;

import emn.llqmam.cloud.data.Vm;
import emn.llqmam.cloud.views.IView;
import emn.llqmam.cloud.views.ViewFactory;

/**
 *
 * @author AM
 * @version 1.0 beta
 */
public class Application implements IApplication {
	
	private static IView view;
	
	public Application () {
		view = ViewFactory.getView(this, "Application name");
	}
	
	public void start() {
		view.displayConnectionDialog();
	}

	@Override
	public void finish() {
		System.exit(0);
	}

	@Override
	public void connect(String name, String password) {
		String versionON = "1.2.3";
		List<Vm> listVM = new ArrayList<>();
		List<Host> listHost = new ArrayList<>();
		// TODO recuperer les bonnes informations...
		view.displayApplication(versionON, listVM, listHost);
		view.displayMessage("You are connected on OpenNebula as " + name + ".\nThe OCA version is ok.", JOptionPane.INFORMATION_MESSAGE);
	}
	
}
