package emn.llqmam.cloud.application;

/**
 * <p>This class allows to build an instance of IApplication. If the Application changes we just have to modify this
 * class.</p>
 * 
 * @author Aurélie Minaud
 * @version 1.0 beta
 */
public class ApplicationFactory {
	
	/**
	 * <p>This method allows to create a new instance of IApplication.</p>
	 * @param client the client connection.
	 * @return the new instance of IApplication.
	 */
	public static IApplication getNewApplication () {
		return new Application();
	}
}