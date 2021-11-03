package com.didiglobal.logi.elasticsearch.client.model.exception;

import java.io.IOException;

public class ESIndexNotFoundException extends RuntimeException {
    private Throwable t;

    public ESIndexNotFoundException(Throwable t) {
        this.t = t;
    }

    @Override
    public String getMessage() {
        return t.getMessage();
    }

    public static boolean check(Throwable t) {
        String str = t.getMessage();

        if (str != null && str.contains("index_not_found_exception")) {
            return true;
        } else {
            return false;
        }
    }
}
