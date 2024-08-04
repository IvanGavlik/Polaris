package polaris_autocomplete.handlers;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class SnipetBrowiser {
	


	private static String loadIndexPage() {
		StringBuilder indexPage = new StringBuilder();
		
	//	ClassLoader classLoader = SnipetBrowiser.class.getClassLoader();
	//	File file = new File(classLoader.getResource("/resource/index.html").getFile());
		
		File file = new File("C:\\EclipseCommitersWorkspace\\Polaris autocomplete\\resource\\index.html");
		
		if (!file.exists()) {
			throw new RuntimeException("File not exist!");
		}
		try (Scanner scanner = new Scanner(file)) {

			while (scanner.hasNextLine()) {
				indexPage.append(scanner.nextLine());
			}

			scanner.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	
	
		return indexPage.toString();
	}
	
	public static void main(String[] args) throws Exception {
		
		Display display = new Display();

		final Shell shell = new Shell(display, SWT.SHELL_TRIM);
		shell.setLayout(new FillLayout());
		Browser browser = new Browser(shell, SWT.NONE);
		
		browser.setJavascriptEnabled(true);
	//	browser.setText(indexHtml(), true);
		
		browser.setBounds(0, 40, 400, 400);
		shell.pack();
		shell.open();
//		browser.setUrl("http://localhost:8080/search");
		while (!shell.isDisposed())
			if (!display.readAndDispatch())
				display.sleep();
		
		
	}		
	
	
	private static String indexHtml() throws Exception {
		URL url = new URL("http://127.0.0.1:8080/search");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer content = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			content.append(inputLine);
		}
		in.close();
		System.out.println(content);
		con.disconnect();
		return content.toString();
	}
}
