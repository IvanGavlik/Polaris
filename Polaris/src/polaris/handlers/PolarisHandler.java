package polaris.handlers;

import java.io.File;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.handlers.IHandlerService;

import polaris.Constants;
import polaris.Polaris;
import polaris.action.Action;
import polaris.action.ActionListener;
import polaris.action.ActionManager;
import polaris.logging.PolarisLogger;
import polaris.net.EclipseServerException;
import polaris.preferences.PolarisPreferencePage;
import polaris.preferences.PreferenceConstants;
import polaris.speechrecognition.SpeechRecognitionException;

public class PolarisHandler extends AbstractHandler implements ActionListener {

	private ExecutionEvent event;
	{
		ActionManager.addListener(this);
	}
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		this.event = event;
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
		if(chechPreference()) {
			try {
				Polaris.getInstance().startPolaris();
				MessageDialog.openInformation(window.getShell(), Constants.POLARIS, "Polaris started!");
			} catch (EclipseServerException ese) {
				PolarisLogger.log(Constants.ECLIPSE_NOT_STARTED, ese.getMessage());
				displayErrorMsg(window, Constants.ECLIPSE_NOT_STARTED);
			} catch (SpeechRecognitionException sre) {
				PolarisLogger.log(Constants.POLARIS_ENGINE_NOT_STARTED, sre.getMessage());
				displayErrorMsg(window, Constants.POLARIS_ENGINE_NOT_STARTED);
			}
		} else {
			displayErrorMsg(window, Constants.POLARIS_CANNOT_START);
		}
		return null;
	}

	private void displayErrorMsg(IWorkbenchWindow window, String msg) {
		MessageDialog.openError(window.getShell(), Constants.POLARIS, msg);
	}
	
	/**
	 * @return true if preferences are ok, else false 
	 */
	private boolean chechPreference() {
		StringBuilder path = new StringBuilder();
		path.append(PolarisPreferencePage.getValue(PreferenceConstants.P_PATH));
		path.append(Constants.POLARIS_ENGINE);
		File f = new File(path.toString());
		return f.exists();
	}
	
	@Override
	public void action(Action action) {
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				try {
					IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
					IHandlerService handlerService = (IHandlerService) window.getService(IHandlerService.class);
					handlerService.executeCommand(action.getEclipseId(), null);
				} catch (Exception e) {
					PolarisLogger.log(Constants.CAN_NOT_START_ACTION, action.formatCommand(), e.getMessage());
				}
			}
		});
	}
}
