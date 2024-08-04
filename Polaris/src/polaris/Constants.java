package polaris;

import org.eclipse.core.runtime.Platform;

/**
 * Constants for application 
 * <b> Once declared should not be changed later</b>
 * @author Ivan Gavlik
 *
 */
public class Constants {
	
	public static final String COLON = "::";
	public static final String QUOTE = "\"";
	public static final String C_DIR = "/c";
	public static final String CMD = "cmd";
	public static final String SPACE = " ";
	public static final String IM = "/IM";
	public static final String FORCE = "/F";
	public static final String TASKKILL = "taskkill";
	public static final String PORT = "49151";
	public static final String PID = "/PID";	

	public static final String ECLIPSE_NOT_STARTED = "Polaris speach recognition not started!"; 
	public static final String POLARIS_ENGINE_NOT_STARTED = "Polaris speach recognition engine not started";
	public static final String POLARIS_EMAIL = "Enter email to receive last news";
	public static final String POLARIS_ENGINE_LOG_PATH = "Engine log path:";
	public static final String POLARIS_LOG_PATH = "Polaris log path:";
	public static final String POLARIS = "Polaris Speach Recognition";	
	public static final String POLARIS_CANNOT_START = "Polaris cannot start!";
	public static final String POLARIS_CANNOT_STOP = "Polaris cannot stop! "; 
	public static final String POLARIS_LOG =  getDefaultInstalationDir() + "Polaris.log"; 
	public static final String POLARIS_ENGINE_LOG = getDefaultInstalationDir() +"PolarisEngine.log"; 
	public static final String POLARIS_PREFERENCE_PAGE = "Polaris speach recognition preference page";
	public static final String POLARIS_ENGINE_DIR  = "&Engine directory:";
	public static final String ECLIPSE_PORT_NUMBER= "&Eclipse port number:";
	public static final String PORT_NUMBER_RANGE= "Port number must be in range from 1024 to 4915";
	public static final String POLARIS_ENGINE= "\\Commander.exe";
	public static final String POLARIS_ENGINE_DIR_MSG = "Engine path must point to instalation directory of Polaris speach recognition egine!";
	public static final String PROCESS_NOT_ALIVE = "Process is not alive";	
	public static final String CAN_NOT_START_ACTION = "Can not start action:";
	public static final String CANNOT_NEW_SERVER = "Can not create new Eclipse datagram server ";
	public static final String PROCESS_STILL_ALIVE = "Kill proces, try secoond time";
	public static final String PROCESS_STILL_ALIVE_2 = "Kill proces, try third time";
	public static final String SLEEP_FAILED = "Sleep_failed";	
	//public static final String SEND_EMAIL = "Please, report error by sending logs at "+EMAIL+". Paths to log files can be found in Polaris Preference";
	public static final String VALUE_CANNOT_BE_CHANGED = "Value cannot be changed!";
	
	public static final String ECLIPSE_SERVER_LOG_IP = "192.168.1.2";
	public static final int ECLIPSE_SERVER_LOG_PORT = 59898;
	
	private static final String getDefaultInstalationDir() {
		String value = Platform.getInstallLocation().getURL().getPath();
		return value.substring(1);
	}
}
