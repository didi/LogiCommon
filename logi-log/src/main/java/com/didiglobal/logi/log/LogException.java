package com.didiglobal.logi.log;

/**
 * 
 * @author zhangliang
 * @version $Id: LogException.java, v 0.1 Jun 30, 2014 4:17:13 PM zhangliang Exp
 *          $
 */
public class LogException extends RuntimeException {

    private static final long serialVersionUID = 1022924004852350942L;

    public LogException() {
        super();
    }

    public LogException(String message) {
        super(message);
    }

    public LogException(String message, Throwable cause) {
        super(message, cause);
    }

    public LogException(Throwable cause) {
        super(cause);
    }
}
