package emn.llqmam.cloud.views.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import emn.llqmam.cloud.application.IApplication;
import emn.llqmam.cloud.views.components.ApplicationFrame;
import emn.llqmam.cloud.views.components.ConnectDialog;

/**
 * <p>
 * This class allows to act when the user wants to suspend a VM.
 * </p>
 * 
 * @author AM
 * @version 1.0 beta
 */
public class MigrateListener implements ActionListener {
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
	public MigrateListener(ApplicationFrame applicationFrame, IApplication clientApp) {
		this.applicationFrame = applicationFrame;
		this.application = clientApp;
	}

	// implemented method from ActionListener
	public void actionPerformed(ActionEvent e) {

		// TODO
	}
}