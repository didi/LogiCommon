package com.didiglobal.logi.log.facade;

import com.didiglobal.logi.log.ILog;
import com.didiglobal.logi.log.util.FlagWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

/**
 * 
 * @author jinbinbin
 * @version $Id: Slf4jFacade.java, v 0.1 2017年11月22日 22:04 jinbinbin Exp $
 */
public class Slf4jFacade implements ILog {

    private static Marker MARKER = MarkerFactory.getMarker("ILog");

    private Logger        logger;
    private Logger        errorLogger;

    public Slf4jFacade(String clazz){
        logger = LoggerFactory.getLogger(clazz);
        errorLogger = LoggerFactory.getLogger("errorLogger");
    }

    @Override
    public void trace(String format, Object arg) {
        logger.trace(FlagWrapper.wrapMessage(format), arg);
    }

    @Override
    public void trace(String format, Object arg1, Object arg2) {
        logger.trace(FlagWrapper.wrapMessage(format), arg1, arg2);
    }

    @Override
    public void trace(String format, Object... arguments) {
        logger.trace(FlagWrapper.wrapMessage(format), arguments);
    }

    @Override
    public void debug(String format, Object arg) {
        logger.debug(FlagWrapper.wrapMessage(format), arg);
    }

    @Override
    public void debug(String format, Object arg1, Object arg2) {
        logger.debug(FlagWrapper.wrapMessage(format), arg1, arg2);
    }

    @Override
    public void debug(String format, Object... arguments) {
        logger.debug(FlagWrapper.wrapMessage(format), arguments);
    }

    @Override
    public void info(String format, Object arg) {
        logger.info(FlagWrapper.wrapMessage(format), arg);
    }

    @Override
    public void info(String format, Object arg1, Object arg2) {
        logger.info(FlagWrapper.wrapMessage(format), arg1, arg2);
    }

    @Override
    public void info(String format, Object... arguments) {
        logger.info(FlagWrapper.wrapMessage(format), arguments);
    }

    @Override
    public void warn(String format, Object arg) {
        logger.warn(FlagWrapper.wrapMessage(format), arg);
    }

    @Override
    public void warn(String format, Object... arguments) {
        logger.warn(FlagWrapper.wrapMessage(format), arguments);
    }

    @Override
    public void warn(String format, Object arg1, Object arg2) {
        logger.warn(FlagWrapper.wrapMessage(format), arg1, arg2);
    }

    @Override
    public void error(String format, Object arg) {
        String wrapperMessage = FlagWrapper.wrapMessage(format);
        errorLogger.error(wrapperMessage, arg);
        logger.error(wrapperMessage, arg);
    }

    @Override
    public void error(String format, Object arg1, Object arg2) {
        String wrapperMessage = FlagWrapper.wrapMessage(format);
        errorLogger.error(wrapperMessage, arg1, arg2);
        logger.error(wrapperMessage, arg1, arg2);
    }

    @Override
    public void error(String format, Object... arguments) {
        String wrapperMessage = FlagWrapper.wrapMessage(format);
        errorLogger.error(wrapperMessage, arguments);
        logger.error(wrapperMessage, arguments);
    }

    @Override
    public boolean isTraceEnabled() {
        return logger.isTraceEnabled();
    }

    @Override
    public void trace(String message) {
        logger.trace(MARKER, FlagWrapper.wrapMessage(message));
    }

    @Override
    public void trace(String message, Throwable t) {
        logger.trace(MARKER, FlagWrapper.wrapMessage(message), t);
    }

    /**
     * @see ILog#isDebugEnabled()
     */
    @Override
    public boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }

    /**
     * @see ILog#isInfoEnabled()
     */
    @Override
    public boolean isInfoEnabled() {
        return logger.isInfoEnabled();
    }

    /**
     * @see ILog#error(String, Throwable)
     */
    @Override
    public void error(String message, Throwable e) {
        String msg = FlagWrapper.wrapExceptionMessage(message);
        errorLogger.error(MARKER, msg, e);
        logger.error(MARKER, msg, e);
    }

    /**
     * @see ILog#error(String)
     */
    @Override
    public void error(String message) {
        String msg = FlagWrapper.wrapExceptionMessage(message);
        errorLogger.error(MARKER, msg);
        logger.error(MARKER, msg);
    }

    /**
     * @see ILog#debug(String)
     */
    @Override
    public void debug(String message) {
        logger.debug(MARKER, FlagWrapper.wrapMessage(message));
    }

    /**
     * @see ILog#info(String)
     */
    @Override
    public void info(String message) {
        logger.info(MARKER, FlagWrapper.wrapMessage(message));
    }

    /**
     * @see ILog#warn(String)
     */
    @Override
    public void warn(String message) {
        logger.warn(MARKER, FlagWrapper.wrapMessage(message));
    }

    /**
     * @see ILog#warn(String,
     *      Throwable)
     */
    @Override
    public void warn(String message, Throwable e) {
        logger.warn(MARKER, FlagWrapper.wrapMessage(message), e);
    }

    @Override
    public boolean isErrorEnabled() {
        return logger.isErrorEnabled();
    }

    /**
     * @see ILog#debug(String,
     *      Throwable)
     */
    @Override
    public void debug(String message, Throwable e) {
        logger.debug(MARKER, FlagWrapper.wrapMessage(message), e);
    }

    /**
     * @see ILog#info(String,
     *      Throwable)
     */
    @Override
    public void info(String message, Throwable e) {
        logger.info(MARKER, FlagWrapper.wrapMessage(message), e);
    }

    @Override
    public boolean isWarnEnabled() {
        return logger.isWarnEnabled();
    }

}
