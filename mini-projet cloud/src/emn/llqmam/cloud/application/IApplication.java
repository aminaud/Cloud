package emn.llqmam.cloud.application;

import org.opennebula.client.host.Host;

import emn.llqmam.cloud.data.Vm;

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
	
	public void connect(String username, String password);
	
	public String getName();
	
	public void remove(Vm vm);
	
	public void resume(Vm vm);
	
	public void suspend(Vm vm);
	
	public void migrate(Vm vm, Host node);

}