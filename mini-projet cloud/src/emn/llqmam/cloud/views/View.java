package emn.llqmam.cloud.views;

import emn.llqmam.cloud.application.IApplication;
import emn.llqmam.cloud.data.Information;
import emn.llqmam.cloud.views.components.ConnectDialog;

/**
 *
 * @author AM
 * @version 1.0 beta
 */
public class View implements IView {
	
	private IApplication application;
	private String name;
	
	public View (IApplication application, String name) {
		this.application = application;
		this.name = name;
	}
	

	public void start() {
		ConnectDialog dialog = new ConnectDialog(null, name, application);
	}

	public void updateView() {
		// TODO
	}

	public void updateView(Information information) {
		// TODO
	}
	
}
