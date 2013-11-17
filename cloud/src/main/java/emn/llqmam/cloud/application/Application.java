package emn.llqmam.cloud.application;

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
		view = ViewFactory.getView();
	}
	
	public void start() {
		view.start();
	}
	
}
