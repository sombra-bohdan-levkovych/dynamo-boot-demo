package com.testmonkeys.demo.utils;


import org.slf4j.LoggerFactory;
import org.slf4j.spi.LocationAwareLogger;

public final class Logger {

    private static final String FQCN = Logger.class.getName();
    private static final ch.qos.logback.classic.Logger Log =
            (ch.qos.logback.classic.Logger) LoggerFactory.getLogger("SombraSpace");

    private Logger() {
        throw new IllegalStateException("Utility class");
    }

    public static void debug(String message) {
        Log.log(null, FQCN, LocationAwareLogger.DEBUG_INT, message, null, null);
    }

    public static void debug(String format, Object... args) {
        Log.log(null, FQCN, LocationAwareLogger.DEBUG_INT, format, args, null);
    }

    public static void warn(String message) {
        Log.log(null, FQCN, LocationAwareLogger.WARN_INT, message, null, null);
    }

    public static void warn(String format, Object... args) {
        Log.log(null, FQCN, LocationAwareLogger.WARN_INT, format, args, null);
    }

    public static void error(String message) {
        Log.log(null, FQCN, LocationAwareLogger.ERROR_INT, message, null, null);
    }

    public static void error(String message, Throwable error) {
        Log.log(null, FQCN, LocationAwareLogger.ERROR_INT, message, null, error);
    }

    public static void error(Throwable error, String format, Object... args) {
        Log.log(null, FQCN, LocationAwareLogger.ERROR_INT, format, args, error);
    }

    public static void error(String format, Object... args) {
        Log.log(null, FQCN, LocationAwareLogger.ERROR_INT, format, args, null);
    }

    public static void info(String message) {
        Log.log(null, FQCN, LocationAwareLogger.INFO_INT, message, null, null);
    }

    public static void info(String format, Object... args) {
        Log.log(null, FQCN, LocationAwareLogger.INFO_INT, format, args, null);
    }

    public static void log(String fqcn, int levelInt, String message) {
        Log.log(null, fqcn, levelInt, message, null, null);
    }

    public static void log(String fqcn, int levelInt, Throwable err) {
        Log.log(null, fqcn, levelInt, null, null, err);
    }

    public static void log(String fqcn, int levelInt, String message, Object... args) {
        Log.log(null, fqcn, levelInt, message, args, null);
    }

}

