package emn.llqmam.cloud.application;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.opennebula.client.host.Host;

import emn.llqmam.cloud.data.Vm;
import emn.llqmam.cloud.services.OpenNebula;
import emn.llqmam.cloud.tools.Tools;
import emn.llqmam.cloud.views.IView;
import emn.llqmam.cloud.views.ViewFactory;

/**
 *
 * @author AM
 * @version 1.0 beta
 */
public class Application implements IApplication {

	private static IView view;

	private String name;

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
	public void connect(String username, String password) {
		if (username.isEmpty() && password.isEmpty()) {
			username="oneadmin";
			password="5bd7fcf39891cdff5896e10a79b7cd9e";
		}
		OpenNebula on = new OpenNebula();
		Vm vm = new Vm();
		vm = on.login(username,
				password, Tools.get_IP() + ":2633");
		String versionON = vm.get_version();
		List<Vm> listVM = vm.retrieveVMsInfo();
		List<Host> listHost = vm.retrieveNodesInfo();
		this.name = username;
		view.displayApplication(versionON, listVM, listHost);
		view.displayMessage("You are connected on OpenNebula as " + name + ".\nThe OCA version is ok.", JOptionPane.INFORMATION_MESSAGE);
	}

	public String getName() {
		return name;
	}
}
