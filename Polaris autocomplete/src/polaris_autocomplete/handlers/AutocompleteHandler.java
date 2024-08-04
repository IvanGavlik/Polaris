package polaris_autocomplete.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class AutocompleteHandler extends AbstractHandler {

	
// http://wiki.eclipse.org/JFaceSnippets#Snippet020_-_Customized_Control_Tooltips
//	http://wiki.eclipse.org/JFaceSnippets#Snippet020CustomizedControlTooltips
/**
 *  *
 * @see <a href="http://www.eclipse.org/swt/snippets/#control">Control snippets</a>
 * @see <a href="http://www.eclipse.org/swt/examples.php">SWT Example: ControlExample</a>
 * @see <a href="http://www.eclipse.org/swt/">Sample code and further information</a>	
 */
	// SWT.NO_TRIM
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
	
		Display display = Display.getDefault();
		display.asyncExec(new Runnable() {
			
			@Override
			public void run() {
				//TODO ON OPEN CLEAN 
				//TODO KEY BINDINGS EXCT, ITD
				ViewController vc = new ViewController(display);
				Shell shell = vc.getShell();
				
				while (!shell.isDisposed()) {
					if (!display.readAndDispatch())
						display.sleep();
				}
				
				
			}
		});
		
		return null;
	}
	
	
	
}
	
	

