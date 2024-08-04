package polaris_autocomplete.handlers;
import org.eclipse.swt.*;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.*;

public class Snippet18 {
	public static void main (String [] args) {
		Shell shell = new Shell ();
		ToolBar bar = new ToolBar (shell, SWT.BORDER);
		for (int i=0; i<8; i++) {
			ToolItem item = new ToolItem (bar, SWT.PUSH);
			item.setText ("Item " + i);
		}
		Rectangle clientArea = shell.getClientArea ();
		bar.setLocation (clientArea.x, clientArea.y);
		bar.pack ();
		shell.open ();
		Display display = shell.getDisplay ();
		while (!shell.isDisposed ()) {
			if (!display.readAndDispatch ()) display.sleep ();
		}
		display.dispose ();
	}
}
