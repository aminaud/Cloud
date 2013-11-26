package emn.llqmam.cloud.application;

import java.util.List;

import javax.swing.JOptionPane;

import org.opennebula.client.host.Host;

import emn.llqmam.cloud.data.Vm;
import emn.llqmam.cloud.services.IOpenNebula;
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

	private String username;
	
	private IOpenNebula on;

	public Application () {
		view = ViewFactory.getView(this, "Application name");
		on = new OpenNebula();
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
		
		on.login(username, password, Tools.get_IP() + ":2633");
		String versionON = on.getOpenNebulaVersion();
		List<Vm> listVM = on.retrieveVMs();
		List<Host> listHost = on.retrieveHosts();
		this.username = username;
		if (on.checkCompatibilityOCA()) {
			view.displayApplication(versionON, listVM, listHost);
			view.displayMessage("You are connected on OpenNebula as " + username
					+ ".\nOCA version is compatible with OpenNebula version " + versionON,
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			view.displayMessage("Sorry we are not able to establish a connection. OCA version is not compatible with OpenNebula version.", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public String getUsername() {
		return username;
	}
	
	@Override
	public void remove(Vm vm) {
		String result = on.deleteVM(vm.getOnVM());
		try {
			Integer.parseInt(result);
			view.displayMessage("VM "+ vm.displayName() + " removed", JOptionPane.INFORMATION_MESSAGE);
			view.updatelistVM(on.retrieveVMs());
		}
		catch (NumberFormatException e) {
			view.displayMessage("Failed to modify, maybe you are not authorized to manage the VM "+ vm.displayName(), JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void resume(Vm vm) {
		String result = on.resumeVM(vm.getOnVM());
		try {
			Integer.parseInt(result);
			view.displayMessage("VM "+ vm.displayName() + " resumed", JOptionPane.INFORMATION_MESSAGE);
			view.updatelistVM(on.retrieveVMs());
		}
		catch (NumberFormatException e) {
			view.displayMessage("Failed to modify, maybe you are not authorized to manage the VM "+ vm.displayName(), JOptionPane.ERROR_MESSAGE);
		}
	}
	
	@Override
	public void suspend(Vm vm) {
		String result = on.suspendVM(vm.getOnVM());
		try {
			Integer.parseInt(result);
			Thread.sleep(2000);
			view.displayMessage("VM "+ vm.displayName() + " suspended", JOptionPane.INFORMATION_MESSAGE);
			view.updatelistVM(on.retrieveVMs());
		}
		catch (NumberFormatException e) {
			view.displayMessage("Failed to modify, maybe you are not authorized to manage the VM "+ vm.displayName(), JOptionPane.ERROR_MESSAGE);
		}
		catch (Exception e) {}
	}

	@Override
	public void migrate(Vm vm, Host node) {
		String result = on.migrateVM(vm.getOnVM(), node.gid());
		try {
			Integer.parseInt(result);
			view.displayMessage("VM "+ vm.displayName() + " migrated", JOptionPane.INFORMATION_MESSAGE);
			view.updatelistVM(on.retrieveVMs());
		}
		catch (NumberFormatException e) {
			view.displayMessage("Failed to modify, maybe you are not authorized to manage the VM "+ vm.displayName(), JOptionPane.ERROR_MESSAGE);
		}
	}
}
