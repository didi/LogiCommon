package com.didiglobal.logi.log.common.json;

import com.alibaba.fastjson.JSON;

/**
 * @author jinbinbin
 * @version $Id: TunnelJsonUtil.java, v 0.1 2018年01月08日 20:30 jinbinbin Exp $
 */
public class TunnelJsonUtil {

    public static String toJSONString(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof String) {
            return (String) obj;
        } else {
            return JSON.toJSONString(obj);
        }
    }

}
