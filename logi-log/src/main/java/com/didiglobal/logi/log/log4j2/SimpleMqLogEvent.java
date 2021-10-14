package com.didiglobal.logi.log.log4j2;

import com.alibaba.fastjson.JSON;
import com.didiglobal.logi.log.util.HostUtil;

/**
 * @author jinbinbin
 * @version $Id: SimpleMqLogEvent.java, v 0.1 2017年11月22日 11:49 jinbinbin Exp $
 */
class SimpleMqLogEvent {

    private static final String NEW_LINE    = "\n";
    static final String         FLAG_STRING = "||flag=";

    /** 所属应用 */
    private String              appName;
    /** 所在主机名 */
    private String              hostName;
    /** 日志内容 */
    private String              content;
    /** 本条日志生成时间 */
    private Long                timestamp;
    /** 日志名称 */
    private String              logName;
    /**
     * 日志级别：warn，error，info，trace
     */
    private String              logLevel;
    /**
     * 追踪flag
     */
    private String              flag;

    public String getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }

    public SimpleMqLogEvent(){
    }

    public SimpleMqLogEvent(String appName){
        this.appName = appName;
        this.hostName = HostUtil.getHostName();
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        String c;
        if (content.endsWith(NEW_LINE)) {
            c = content.substring(0, content.length() - 1);
        } else {
            c = content;
        }
        this.content = c;
        String[] cArr = c.split(FLAG_STRING);
        if (cArr.length == 2) {
            this.flag = cArr[1];
        }
    }

    public String getFlag() {
        return flag;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getLogName() {
        return logName;
    }

    public void setLogName(String logName) {
        this.logName = logName;
    }

    public StringBuilder toStringBuilder() {
        return new StringBuilder(JSON.toJSONString(this));
    }

    @Override
    public String toString() {
        return "{\"SimpleMqLogEvent\":{" + "\"appName\":\"" + appName + "\"" + "," + "\"content\":\"" + content + "\""
               + "," + "\"hostName\":\"" + hostName + "\"" + "," + "\"logLevel\":\"" + logLevel + "\"" + ","
               + "\"logName\":\"" + logName + "\"" + "," + "\"timestamp\":\"" + timestamp + "\"" + "}}";
    }
}
