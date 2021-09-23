package com.didiglobal.logi.security.util;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author cjm
 */
public class NetworkUtil {

    private static final String UNKNOWN = "unknown";

    /**
     * 获取http发起客户端真实地址
     * @return ip地址
     */
    public static String getRealIpAddress() {
        RequestAttributes ra = RequestContextHolder.currentRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        return getRealIpAddress(request);
    }

    private static boolean isNotOk(String ipAddress) {
        return ipAddress == null || ipAddress.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddress);
    }

    /**
     * 获取http发起客户端真实地址
     * @param request 请求信息
     * @return ip地址
     */
    public static String getRealIpAddress(HttpServletRequest request) {
        String ipAddress = request.getHeader("x-forwarded-for");
        if (isNotOk(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (isNotOk(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (isNotOk(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            String localIp = "127.0.0.1";
            String localIpv6 = "0:0:0:0:0:0:0:1";
            if (ipAddress.equals(localIp) || ipAddress.equals(localIpv6)) {
                // 根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                    ipAddress = inet.getHostAddress();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
            }
        }
        // 对于通过多个代理的情况，第一个IP为客户端真实IP，多个IP按照','分割
        String ipSeparate = ",";
        int ipLength = 15;
        if (ipAddress != null && ipAddress.length() > ipLength && ipAddress.contains(ipSeparate)) {
            ipAddress = ipAddress.substring(0, ipAddress.indexOf(ipSeparate));
        }
        return ipAddress;
    }

    private NetworkUtil() {}
}
