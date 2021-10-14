package com.didiglobal.logi.log.common.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jinbinbin
 * @version $Id: TunnelLists.java, v 0.1 2018年01月08日 20:29 jinbinbin Exp $
 */
public class TunnelLists<T> {

    public static <T> List<T> ensureNotNullList(List<T> list) {
        if (list == null) {
            return new ArrayList<T>();
        }
        return list;
    }
}
