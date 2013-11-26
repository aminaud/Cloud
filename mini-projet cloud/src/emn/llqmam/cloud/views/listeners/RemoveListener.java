package emn.llqmam.cloud.views.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.opennebula.client.host.Host;

import emn.llqmam.cloud.application.IApplication;
import emn.llqmam.cloud.data.Vm;
import emn.llqmam.cloud.views.components.ApplicationFrame;

/**
 * <p>
 * This class allows to act when the user wants to suspend a VM.
 * </p>
 * 
 * @author AM
 * @version 1.0 beta
 */
public class RemoveListener implements ActionListener {
	/**
	 * <p>
	 * The instance of the dialog which call this listener.
	 * </p>
	 */
	private ApplicationFrame applicationFrame;
	/**
	 * <p>
	 * The application main controller.
	 * </p>
	 */
	private IApplication application;

	/**
	 * <p>
	 * The constructor for this listener.
	 * </p>
	 * 
	 * @param connectDialog
	 *            the dialog where we call this listener.
	 * @param clientApp
	 *            the instance of the main controller of this application.
	 */
	public RemoveListener(ApplicationFrame applicationFrame, IApplication clientApp) {
		this.applicationFrame = applicationFrame;
		this.application = clientApp;
	}

	// implemented method from ActionListener
	public void actionPerformed(ActionEvent e) {
		application.remove(applicationFrame.getSelectedVm());
	}
}