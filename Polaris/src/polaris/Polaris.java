package polaris;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.swt.widgets.Display;

import polaris.action.ActionManager;
import polaris.logging.PolarisLogger;
import polaris.net.EclipseServerException;
import polaris.preferences.PolarisPreferencePage;
import polaris.preferences.PreferenceConstants;
import polaris.speechrecognition.SpeechRecognition;
import polaris.speechrecognition.SpeechRecognitionException;

//TODO UPUTE -> ako se veè neka druga aplikacija vrti na tom portu 
// https://help.eclipse.org/kepler/index.jsp?topic=/org.eclipse.platform.doc.isv/guide/runtime_jobs.htm
public class Polaris {
	
	private Status status;
	private static Polaris instance;
	
	private Polaris() {
		status = Status.OFF;
	}

	public static Polaris getInstance() {
		if(instance == null) {
			synchronized (Polaris.class) {
				instance = new Polaris();
			}
		}
		return instance;
	}
	

	private boolean interrupted; 
	public void startPolaris() throws EclipseServerException, SpeechRecognitionException {
		PolarisLogger.log("startPolaris", status.name());
		if (status.equals(Status.OFF)) {
			
			Display.getDefault().asyncExec(new Runnable() {

				@Override
				public void run() { 
					// JOB  start server 
					
					new SpeechRecognition().start();
/*					
					DatagramSocket socket = null;
					try {
						int portNumber = Integer.valueOf(PolarisPreferencePage.getValue(PreferenceConstants.P_PORT));
						 socket = new DatagramSocket(portNumber);
					} catch (Exception e) {
						PolarisLogger.log("EclipseServerDatagram()", Constants.CANNOT_NEW_SERVER, e.getMessage());
						throw new EclipseServerException(Constants.CANNOT_NEW_SERVER + e.getMessage());
					}
					
					String received = null;
					interrupted = false;
					try {
						while (!interrupted && socket != null) { 					// receive request
							byte[] buf = new byte[12560];
							DatagramPacket packet = new DatagramPacket(buf, buf.length);
							socket.receive(packet);

							if (packet != null) {
								received = new String(packet.getData(), 0, packet.getLength(), "UTF-8");
								PolarisLogger.log("recived: ", received);
								ActionManager.findActionAndNotify(received);
							}

							// TODO DA LI MI OVO TREBA I ZAŠTO ANALIZIRAJ
							InetAddress address = packet.getAddress();
							int port = packet.getPort();
							packet = new DatagramPacket(received.toString().getBytes(),
									received.toString().getBytes().length, address, port);
							socket.send(packet);
							received = null;
						}
					} catch (Exception e) {
						throw new EclipseServerException(e.getMessage());
					} finally {
						// socket.close();
					}*/
					PolarisLogger.log("EclipseServerDatagram thread ended");
					// syncWithUI(); todo;
					status = Status.ON;
					PolarisLogger.log("startPolaris",status.name());
					
				}
			});
			
		}
	}
	
	public void stopPolaris() {
		PolarisLogger.log("stopPolaris",status.name());
		if(status.equals(Status.ON)) {
			
			Display.getDefault().asyncExec(new Runnable() {
				@Override
				public void run() { 
					new SpeechRecognition().stop();
					interrupted = true;
					status = Status.OFF;
					PolarisLogger.log("stopPolaris",status.name());
				}
			});
			
		}
		
	}
	
	private enum Status {
		ON,OFF;
	}
}
