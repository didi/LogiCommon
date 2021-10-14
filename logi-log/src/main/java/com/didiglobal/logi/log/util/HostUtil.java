package com.didiglobal.logi.log.util;

import java.net.InetAddress;

/**
 * @author jinbinbin
 * @version $Id: HostUtil.java, v 0.1 2017年07月21日 16:26 jinbinbin Exp $
 */
public class HostUtil {

    private static volatile String HOST;

    static {
        try {
            HOST = InetAddress.getLocalHost().getHostName(); //获取本机计算机名称
        } catch (Exception e) {
            HOST = "UnknownHost";
        }
    }

    public static String getHostName() {
        return HOST;
    }
}
