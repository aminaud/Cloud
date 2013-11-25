package emn.llqmam.cloud.views;

import emn.llqmam.cloud.data.Information;

/**
 * <p>This interface allows to communication with the view.<:p>
 * 
 * @author AM
 * @version 1.0 beta
 */
public interface IView {
	/**
	 * <p>This method allows to launch the view and display it.</p>
	 */
	public void start();
	
	/**
	 * <p>This method allows to update the view after a treatment into the aplication.</p>
	 */
	public void updateView();
	
	/**
	 * <p> This method allows to update the view and gives it some information for its update.</p>
	 * @param information	information the view needs for its update.
	 */
	public void updateView(Information information);
	
	public void startApplication();
}
