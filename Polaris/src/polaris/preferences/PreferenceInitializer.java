package polaris.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import polaris.Activator;
import polaris.Constants;
import polaris.logging.PolarisLogger;

/**
 * Class used to initialize default preference values.
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {
	
	public void initializeDefaultPreferences() {
		PolarisLogger.log("initializeDefaultPreferences",  "4145", " ", "" ,  Constants.POLARIS_ENGINE_LOG, "",  Constants.POLARIS_LOG);
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		store.setDefault(PreferenceConstants.P_PORT, "4145");
		store.setDefault(PreferenceConstants.P_EMAIL,  Constants.SPACE);
	}
	


}
