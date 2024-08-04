package polaris_autocomplete.handlers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.ToLongBiFunction;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.ProgressListener;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolTip;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javafx.scene.control.Control;
import polaris_autocomplete.model.Item;
// TODO COPYPAste
public class ViewController {
	
	private Display display;
	private Shell shell;

	
	public ViewController(Display display) {
		this.display = display;
		shell = new Shell(display, SWT.APPLICATION_MODAL);	
		shell.setLayout(new GridLayout(1, false));
		
		Text txt = initText(display);

	    ExpandBar bar = initExpandBar();
    //	bar.setToolTipText("No found data");
    	ToolTip tooltip = new ToolTip(shell, SWT.NONE);
    	tooltip.setText("cnlscns");
    //	tooltip.setLocation(bar.getLocation()); TODO 
    	tooltip.setAutoHide(true);
    	
		txt.addKeyListener(new KeyListener() {
			
			@Override
			public void keyReleased(KeyEvent e) {}

			@Override
			public void keyPressed(KeyEvent e) {
				
				// enter
				if (e.keyCode == 13 && e.keyLocation == 0 && txt.getText().length() > 3) {
		
					ExpandItem[] expandItems = bar.getItems();
				    for (int i = expandItems.length - 1; i >= 0; i--) {
				    	expandItems[i].getControl().dispose();
				    	expandItems[i].dispose();
				    }	
					
				    
				    Collection<Item> items = null; //query(txt.getText());
				    if(items == null) {
				    	tooltip.setVisible(true);
				    	
				    	return;
				    }
				    Iterator<Item> itemIterator = items.iterator(); 
					while(itemIterator.hasNext()) {
						// TODO TITLE TOLONG 
						Item item = itemIterator.next();
						displayItem(display, bar, item.getTitle(), item.getContent());
					}
				
					bar.setSpacing(8);
					
				}
				// esc
				if (e.keyCode == 27 && e.keyLocation == 0) {
					shell.dispose(); //TODO TESTIRAI NA ELIPSU 
				}
			}
		});
		
		
		
		shell.setSize(400, 350);
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	private ExpandBar initExpandBar() {
		ExpandBar bar = new ExpandBar(shell, SWT.V_SCROLL);
	    bar.setBackgroundMode(SWT.INHERIT_FORCE);
	    bar.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_TRANSPARENT));
    	bar.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		return bar;
	}

	private Text initText(Display display) {
		Text txt = new Text(shell, SWT.NONE);
		txt.setText("");
		txt.setFocus();
		txt.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_TRANSPARENT));
		FontData[] fD = txt.getFont().getFontData();
		fD[0].setHeight(10);
		txt.setFont(new Font(display,fD[0]));	
		txt.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		return txt;
	}
	
	public Shell getShell() {
		return this.shell;
	}
	
	private Collection<Item> query(String title) {
	/*	HttpURLConnection con = null;
		BufferedReader in  = null;
		StringBuffer content = new StringBuffer();
		try {
			String encodeTitle = java.net.URLEncoder.encode(title, "UTF-8");
			String urlTmp ="http://127.0.0.1:8080/search?title="+encodeTitle;
			
			URL url = new URL(urlTmp);
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");	
			
			in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			
			while ((inputLine = in.readLine()) != null) {
				content.append(inputLine);
			}
			in.close();
		} 
		catch (Exception e) {
			content = new StringBuffer();
			e.printStackTrace();
		} finally {
			if(in != null) {
				try {
					in.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				con.disconnect();
			}
		}
		
		Gson gson = new Gson();
		Type collectionType = new TypeToken<Collection<Item>>(){}.getType();
		return gson.fromJson(content.toString(), collectionType);
		*/
		// DUMMY DATA
		Collection<Item> collection = new ArrayList<>();
		Item i = new Item();
		i.setTitle("T1");
		i.setContent("<h1> fjdlkafam </h1>");
		collection.add(i);
		Item i2 = new Item();
		i2.setTitle("T2");
		i2.setContent("<h1> fjdlkafam 2 </h1>");
		collection.add(i2);
		return collection;
	}
	
	private void displayItem(Display display ,ExpandBar bar,final String title, final String content) {
		Group composite = new Group(bar, SWT.NONE);
		GridLayout layout = new GridLayout (1, true);
		composite.setLayout(layout);
		
		Browser browser = new Browser(composite, SWT.PUSH);
		browser.setJavascriptEnabled(true);
		browser.setText(content, true);
		browser.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		//browser.setSize(,content.length());
		
		ExpandItem item = new ExpandItem(bar,  SWT.PUSH, 0);
		item.setText(title);
		item.setHeight(250); //TODO
		item.setControl(composite);	
	}
	
	
	public static void main(String[] args) {
		Display display = new Display();
		ViewController vc = new ViewController(display);
		Shell shell = vc.getShell();
		
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}


}
