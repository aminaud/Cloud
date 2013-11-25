package emn.llqmam.cloud.application;

/**
 * <p>This interface allows to control the application. All classes which implements this interface are the core
 * of the application: when two modules want to communicate they have to pass by the main controller. Two modules
 * don't have to have an instance of each just the main controller.</p>
 * 
 * @author Aurélie Minaud
 * @version 1.0 beta
 */
public interface IApplication {

	/**
	 * <p>This method allows to start the application.</p>
	 */
	public void start();
	
	/**
	 * 
	 */
	public void finish();
	
	public void connect(String name, String password);

}