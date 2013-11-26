package emn.llqmam.cloud.application;

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

	private String username;
	
	private Vm vmToRetrieve;

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
		vm = on.login(username, password, Tools.get_IP() + ":2633");
		this.vmToRetrieve = vm;
		String versionON = vm.get_version();
		List<Vm> listVM = vm.retrieveVMsInfo();
		List<Host> listHost = vm.retrieveNodesInfo();
		this.username = username;
		if (vm.checkCompatibility()) {
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
		String result = vm.delete();
		try {
			Integer.parseInt(result);
			view.displayMessage("VM "+ vm.get_name() + " removed", JOptionPane.INFORMATION_MESSAGE);
			view.updatelistVM(vmToRetrieve.retrieveVMsInfo());
		}
		catch (NumberFormatException e) {
			view.displayMessage(result, JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void resume(Vm vm) {
		String result = vm.resume();
		try {
			Integer.parseInt(result);
			view.displayMessage("VM "+ vm.get_name() + " resumed", JOptionPane.INFORMATION_MESSAGE);
			view.updatelistVM(vmToRetrieve.retrieveVMsInfo());
		}
		catch (NumberFormatException e) {
			view.displayMessage(result, JOptionPane.ERROR_MESSAGE);
		}
	}
	
	@Override
	public void suspend(Vm vm) {
		String result = vm.suspend();
		try {
			Integer.parseInt(result);
			Thread.sleep(2000);
			view.displayMessage("VM "+ vm.get_name() + " suspended", JOptionPane.INFORMATION_MESSAGE);
			view.updatelistVM(vmToRetrieve.retrieveVMsInfo());
		}
		catch (NumberFormatException e) {
			view.displayMessage(result, JOptionPane.ERROR_MESSAGE);
		}
		catch (Exception e) {}
	}

	@Override
	public void migrate(Vm vm, Host node) {
		String result = vm.migrate(node.gid());
		try {
			Integer.parseInt(result);
			view.displayMessage("VM "+ vm.get_name() + " migrated", JOptionPane.INFORMATION_MESSAGE);
			view.updatelistVM(vmToRetrieve.retrieveVMsInfo());
		}
		catch (NumberFormatException e) {
			view.displayMessage(result, JOptionPane.ERROR_MESSAGE);
		}
	}
}
