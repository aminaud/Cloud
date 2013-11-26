package emn.llqmam.cloud.views;

import emn.llqmam.cloud.application.IApplication;


/**
 * <p>This class allows to build an instance of IView. If the View changes we just have to modify this class.</p>
 * 
 * @author AM
 * @version 1.0 beta
 */
public class ViewFactory {
	
	/**
	 * <p>This method allows to create a new instance of IView.</p>
	 * @return the new instance of IView.
	 */
	public static IView getView (IApplication application, String name) {
		return new View(application, name);
	}
}