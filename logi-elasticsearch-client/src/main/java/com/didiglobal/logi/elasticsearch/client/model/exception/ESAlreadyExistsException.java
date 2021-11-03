package com.didiglobal.logi.elasticsearch.client.model.exception;

import java.io.IOException;

public class ESAlreadyExistsException extends RuntimeException {
    private Throwable t;

    public ESAlreadyExistsException(Throwable t) {
        this.t = t;
    }

    @Override
    public String getMessage() {
        return t.getMessage();
    }

    public static boolean check(Throwable t) {
        String str = t.getMessage();

        if (str != null && (str.contains("index_already_exists_exception") ||
                str.contains("resource_already_exists_exception"))) {
            return true;
        } else {
            return false;
        }
    }
}
