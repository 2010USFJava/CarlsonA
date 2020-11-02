package com.Revature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogThis {
	static Logger logger = LogManager.getLogger();
	
	public static void LogIt(String level, String message) {
//		Added to handled weird capitalization and white space
		level=level.toLowerCase().trim();
		switch(level) {
		case "debug":
			logger.debug(message);
			break;
		case "warn":
			logger.warn(message);
			break;
		case "error":
			logger.error(message);
			break;
		case "fatal":
			logger.fatal(message);
			break;
		case "info":
			logger.info(message);
			break;
		case "trace":
			logger.trace(message);
			break;
		default:
			System.err.println("What are you doing to me, man?\n"+message+" isn't a thing I know how to deal with");
		
		}
	}

}
