package com.didiglobal.logi.log;

import com.didiglobal.logi.log.common.TraceContext;
import com.didiglobal.logi.log.facade.Slf4jFacade;
import com.didiglobal.logi.log.util.FlagGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;

/**
 * 统一日志记录记录方式工具类，（主要包括框架类 log4j2和slf4j api）方便做一些公共的切面工作
 * 
 * @author zhangliang
 * @version $Id: LogFactory.java, v 0.1 Jun 27, 2014 2:52:33 PM zhangliang Exp $
 *               v 0.2 2017年12月19日 22:26 jinbinbin Exp
 */
public class LogFactory {

    private final static Logger                LOGGER = LoggerFactory.getLogger(LogFactory.class);
    private static Constructor<? extends ILog> logConstructor;
    private static ThreadLocal<String>         flag   = new ThreadLocal<String>();
    private static ThreadLocal<TraceContext>   trace  = new ThreadLocal<TraceContext>();
    static {
        try {
            useSlf4jLogging();
        } catch (Throwable t) {
            throw new LogException(t);
        }
    }

    private static synchronized void useSlf4jLogging() throws Exception {
        Class<? extends ILog> clazz = Slf4jFacade.class;
        try {
            logConstructor = clazz.getConstructor(String.class);
            LOGGER.info("Logging initialized using '" + clazz + "' adapter.");
        } catch (Throwable t) {
            LOGGER.error("Error setting Log implementation.  Cause: " + t, t);
            throw new LogException("Error setting Log implementation.  Cause: " + t, t);
        }
    }

    /**
     * disable construction
     */
    private LogFactory(){
    }

    public static ILog getLog(Class<?> aClass) {
        return getLog(aClass.getName());
    }

    public static ILog getLog(String logger) {
        try {
            return logConstructor.newInstance(logger);
        } catch (Exception e) {
            LOGGER.error("Error creating logger for logger " + logger + ".  Cause: " + e, e);
            throw new LogException("Error creating logger for logger " + logger + ".  Cause: " + e, e);
        }
    }

    /**
     * Setter method for property <tt>flag</tt>.
     * 
     * @param value  value to be assigned to property flag
     */
    public static void setFlag(String value) {
        flag.set(value);
    }

    public static void setTrace(TraceContext traceContext) {
        trace.set(traceContext);
    }

    /**
     * set a unique flag
     */
    public static void setUniqueFlag() {
        flag.set(getUniqueFlag());
    }

    /**
     * get method for property <tt>flag</tt>.
     * @return String
     */
    public static String getFlag() {
        return flag.get();
    }

    public static TraceContext getTrace() {
        return trace.get();
    }

    public static void removeFlag() {
        flag.remove();
    }

    public static String getUniqueFlag() {
        return FlagGenerator.get().toStringBabble();
    }
}
