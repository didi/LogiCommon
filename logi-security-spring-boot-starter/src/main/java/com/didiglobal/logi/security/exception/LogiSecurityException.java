package com.didiglobal.logi.security.exception;

/**
 * @author cjm
 *
 * 返回后端和前端商定的code（msg前端自己决定）
 */
public class LogiSecurityException extends RuntimeException {

    public LogiSecurityException() {}

    public LogiSecurityException(CodeMsg codeMsg) {
        super(codeMsg.getCode() + "-" + codeMsg.getMessage());
    }

    public LogiSecurityException(String message, Throwable cause) {
        super(message, cause);
    }
}
