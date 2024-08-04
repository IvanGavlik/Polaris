package polaris.preferences;

import java.io.File;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.eclipse.jface.preference.DirectoryFieldEditor;
import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import polaris.Activator;
import polaris.Constants;
import polaris.net.EclipseLogClinet;

/**
 * This class represents a preference page that
 * is contributed to the Preferences dialog. By 
 * subclassing <samp>FieldEditorPreferencePage</samp>, we
 * can use the field support built into JFace that allows
 * us to create a page that is small and knows how to 
 * save, restore and apply itself.
 * <p>
 * This page is used to modify preferences only. They
 * are stored in the preference store that belongs to
 * the main plug-in class. That way, preferences can
 * be accessed directly via the preference store.
 */

public class PolarisPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	private DirectoryFieldEditor speachRecognitionEnginePath; 
	private StringFieldEditor portNumber; 
	private StringFieldEditor email; 
	
	
	public PolarisPreferencePage() {
		super(GRID);
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription(Constants.SPACE);
	}
	
	/**
	 * Creates the field editors. Field editors are abstractions of
	 * the common GUI blocks needed to manipulate various types
	 * of preferences. Each field editor knows how to save and
	 * restore itself.
	 */
	public void createFieldEditors() {
		speachRecognitionEnginePath = new DirectoryFieldEditor(PreferenceConstants.P_PATH, 
				Constants.POLARIS_ENGINE_DIR, getFieldEditorParent());
		addField(speachRecognitionEnginePath);
		
		portNumber = new StringFieldEditor(PreferenceConstants.P_PORT, 
				Constants.ECLIPSE_PORT_NUMBER, getFieldEditorParent());
		addField(portNumber);
		
		email = new StringFieldEditor(PreferenceConstants.P_EMAIL, 
				Constants.POLARIS_EMAIL, getFieldEditorParent());
		addField(email);
	}

	protected void checkState() {
        super.checkState();
        int portNumberTmp = Integer.MIN_VALUE;        
        try {
        	portNumberTmp = Integer.valueOf(portNumber.getStringValue());
        } catch (Exception e) {
		}
        if(portNumberTmp > 1023 && portNumberTmp < 49152) {
            setValid(true);
        } else {
            setErrorMessage(Constants.PORT_NUMBER_RANGE);
        	setValid(false);
        }
        
        StringBuilder path = new StringBuilder();
        path.append(speachRecognitionEnginePath.getStringValue());
        path.append(Constants.POLARIS_ENGINE);
        File f = new File(path.toString());
        if(f.exists()) {
        	setValid(true);
        } else {
            setErrorMessage(Constants.POLARIS_ENGINE_DIR_MSG);
        	setValid(false);
        }
        /*
        String emailAddres = email.getStringValue();
        if(isValidEmailAddress(emailAddres )) {
        	EclipseLogClinet.send("My Email is:" + emailAddres);
        }*/
        
	}
	
	private boolean isValidEmailAddress(String email) {
		boolean result = true;
		try {
			InternetAddress emailAddr = new InternetAddress(email);
			emailAddr.validate();
		} catch (AddressException ex) {
			result = false;
		}
		return result;
	}
	
	public void propertyChange(PropertyChangeEvent event) {
        super.propertyChange(event);
        if (event.getProperty().equals(FieldEditor.VALUE)) {
        	checkState();
        } 

	}
	
	public void init(IWorkbench workbench) {
	}
	
	public static String getValue(String constant) {
		return Activator.getDefault().getPreferenceStore().getString(constant);
	}

	
}