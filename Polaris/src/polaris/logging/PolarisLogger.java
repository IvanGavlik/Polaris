package polaris.logging;

import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.stream.Stream;

import polaris.Constants;
import polaris.net.EclipseLogClinet;

public class PolarisLogger {

//	private Logger logger = Logger.getLogger(PolarisLogger.class.getName());
//	
//	private PolarisLogger() {
//		try {
//			FileHandler fh = new FileHandler(Constants.POLARIS_LOG);
//			fh.setFormatter(new Formatter() {
//				@Override
//				public String format(LogRecord record) {
//					StringBuilder recordBuilder = new StringBuilder();
//					recordBuilder.append(
//							record.getThreadID()).append(Constants.COLON)
//							.append(new Date(record.getMillis())).append(Constants.COLON)
//							.append(record.getMessage()).append(System.lineSeparator());
//					return recordBuilder.toString();
//				}
//			});
//			logger.addHandler(fh);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		logger.setLevel(Level.ALL);
//
//	}
//
//	private void dolog(String... msgs)  {
//		StringBuilder sb = new StringBuilder();
//		for(String value : msgs) {
//			sb.append(value).append(Constants.SPACE);
//		}
//		logger.log(Level.INFO, sb.toString());
//		EclipseLogClinet.send(sb.toString());
//	}
//	
//	private static PolarisLogger instance;
//	public static void log(String...msgs) {
//		if(instance == null) {
//			synchronized (PolarisLogger.class) {
//				if(instance == null) {
//					instance = new PolarisLogger();
//				}
//			}
//		}
//		instance.dolog(msgs);
//	}
	
	public static void log(String...msgs) {
		Stream.of(msgs).forEach(System.out::print);
	}
}
