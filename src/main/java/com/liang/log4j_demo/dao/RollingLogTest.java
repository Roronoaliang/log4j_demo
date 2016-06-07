package com.liang.log4j_demo.dao;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class RollingLogTest {

	private static final Logger logger = Logger.getLogger(RollingLogTest.class);

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
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
}
