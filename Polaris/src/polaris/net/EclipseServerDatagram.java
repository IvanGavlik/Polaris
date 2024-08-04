package polaris.net;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import polaris.Constants;
import polaris.action.ActionManager;
import polaris.logging.PolarisLogger;
import polaris.preferences.PolarisPreferencePage;
import polaris.preferences.PreferenceConstants;

public class EclipseServerDatagram {

	private DatagramSocket socket = null;
	private volatile boolean interrupted = false;

	public EclipseServerDatagram() {
		
	}

	private Runnable createRunnable() {
		Runnable r = new Runnable() {
			public void run() {
				
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
				}
				PolarisLogger.log("EclipseServerDatagram thread ended");
			}
			
		};
		return r;
	}

	public void run() {
		new Thread(createRunnable()).start();
	}

	public void stop() {
		interrupted = true;
		if (socket != null && !socket.isClosed()) {
			socket.close();
		}
	}
}
