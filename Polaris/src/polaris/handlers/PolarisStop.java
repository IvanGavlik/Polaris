package polaris.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import polaris.Constants;
import polaris.Polaris;

public class PolarisStop extends AbstractHandler  {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		Polaris.getInstance().stopPolaris();
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
		MessageDialog.openError(window.getShell(), Constants.POLARIS, "Polaris Stoped");
		return null;
	}


}
