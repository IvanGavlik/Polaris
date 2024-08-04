package polaris_autocomplete.handlers;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.custom.TreeEditor;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

public class TreeEditorButtonEdited {
  public static void main(String[] args) {
    Display display = new Display();
    final Shell shell = new Shell(display);
    shell.setText("Text Tree Editor");
    shell.setLayout(new FillLayout());

    final Tree tree = new Tree(shell, SWT.SINGLE);
    for (int i = 0; i < 3; i++) {
      TreeItem iItem = new TreeItem(tree, SWT.NONE);
      iItem.setText("Item " + (i + 1));
   //   iItem.setExpanded(true);
    }

    Browser browser = new Browser(shell, SWT.PUSH);
		browser.setJavascriptEnabled(true);
		browser.setText("<h1> radi </h1>", true);
		browser.setSize(50, 50);
    
    final TreeEditor editor = new TreeEditor(tree);
    editor.horizontalAlignment = SWT.LEFT;
    editor.grabHorizontal = true;
    editor.grabVertical = true;
    editor.minimumHeight = 40;

    tree.addMouseListener(new MouseAdapter() {
      public void mouseDown(MouseEvent event) {
        final TreeItem item = tree.getSelection()[0];

        
        browser.setText("<h1>"+ item.getText() + " radi </h1>", true);
	//	browser.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        
   /*    final Button bn = new Button(tree, SWT.NONE);
        bn.setText("click");
        bn.setFocus();

        bn.addSelectionListener(new SelectionListener() {

          public void widgetSelected(SelectionEvent arg0) {
            int style = SWT.ICON_QUESTION | SWT.YES | SWT.NO;

            MessageBox messageBox = new MessageBox(shell, style);
            messageBox.setMessage("Message");
            int rc = messageBox.open();

            item.setText(rc + "");
            bn.dispose();
          }

          public void widgetDefaultSelected(SelectionEvent arg0) {
          }
        });
*/
		item.setExpanded(true);
        
      }
    });

   
    
    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
    display.dispose();
  }
}
