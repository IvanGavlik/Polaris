package polaris.speechrecognition;

import java.util.Arrays;

import polaris.Constants;
import polaris.logging.PolarisLogger;
import polaris.preferences.PolarisPreferencePage;
import polaris.preferences.PreferenceConstants;

public class SpeechRecognition {
	
	private Process process; 
	private final String[] PROGRAM_START = new String[5];
	private final String[] PROGRAM_STOP = new String[6];
	
	public SpeechRecognition() {
		
		PROGRAM_START[0] = Constants.CMD;
		PROGRAM_START[1] = Constants.C_DIR;
		PROGRAM_START[2] = getSpeechRecognitionAppPath();
		PROGRAM_START[3] = PolarisPreferencePage.getValue(PreferenceConstants.P_PORT);
		PROGRAM_START[4] = getSpeechRecognitionLogPath();
		
	//	rt.exec(new String[] { "cmd.exe", "/c", "taskkill", "/PID", "", "SpeachRecognitionApp.exe" });
		PROGRAM_STOP[0] = Constants.CMD;
		PROGRAM_STOP[1] = Constants.C_DIR;
		PROGRAM_STOP[2] = Constants.TASKKILL;
		PROGRAM_STOP[3] = Constants.FORCE;
		PROGRAM_STOP[4] = Constants.IM;
		PROGRAM_STOP[5] = Constants.POLARIS_ENGINE.substring(1);
	}
	
	public void start() {
		try {
			Runtime run = Runtime.getRuntime();
			process = run.exec(PROGRAM_START);
			boolean aliive = process.isAlive();
			PolarisLogger.log(String.valueOf(aliive), Arrays.asList(PROGRAM_START).toString()); 
			if (!aliive) { 
				throw new SpeechRecognitionException(Constants.PROCESS_NOT_ALIVE);
			}
		} catch (Exception e) {
			throw new SpeechRecognitionException(e.getMessage());
		}
	}

	
	private String getSpeechRecognitionLogPath() {
		StringBuilder path = new StringBuilder();
		path.append(Constants.POLARIS_ENGINE_LOG.replaceAll("/", "_"));
		return path.toString();
	}

	private String getSpeechRecognitionAppPath() {
		StringBuilder path = new StringBuilder();
		path.append(Constants.QUOTE);
		path.append(PolarisPreferencePage.getValue(PreferenceConstants.P_PATH));
		path.append(Constants.POLARIS_ENGINE);
		path.append(Constants.QUOTE);
		return path.toString();
	}
	
	public void stop() {
		try {
			Runtime.getRuntime().exec(PROGRAM_STOP);
			PolarisLogger.log(Arrays.asList(PROGRAM_STOP).toString());
		} catch (Exception e) {
			PolarisLogger.log(Constants.PROCESS_STILL_ALIVE, e.getMessage());
			throw new SpeechRecognitionException(Constants.POLARIS_CANNOT_STOP + e.getMessage());
		}
		process.destroy();	
	}
	
}
