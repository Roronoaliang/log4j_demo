package com.liang.log4j_demo;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * Hello world!
 *
 */
public class App {
	
	public static final Logger logger = Logger.getRootLogger();

	public static void main(String[] args) {

		if (logger.isEnabledFor(Level.TRACE)) {
			logger.trace("this is a log with tarce level...");
		}
		if (logger.isEnabledFor(Level.DEBUG)) {
			logger.debug("this is a log with debug level...");
		}
		if (logger.isEnabledFor(Level.INFO)) {
			logger.info("this is a log with info level...");
		}
		if (logger.isEnabledFor(Level.WARN)) {
			logger.warn("this is a log with warn level...");
		}
		if (logger.isEnabledFor(Level.ERROR)) {
			logger.error("this is a log with error level...");
		}
		if (logger.isEnabledFor(Level.FATAL)) {
			logger.fatal("this is a log with fatal level...");
		}

	}

}
