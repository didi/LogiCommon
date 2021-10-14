package com.didiglobal.logi.log.common.enums;

/**
 * @author jinbinbin
 * @version $Id: StorageEnum.java, v 0.1 2018年03月22日 23:31 jinbinbin Exp $
 */
public enum StorageEnum {
                         //
                         KAFKA(0, "kafka"),
                         //
                         DATA_CENTER(1, "data_center"),
                         //
                         MQ(2, "ddmq"),
                         //
                         HDFS(3, "HDFS"),
                         //
                         ES(4, "ES"),
                         //
                         HBASE(5, "hbase"),
                         //
                         KAFKA_MOCK(6, "kafka mock"),
                         //
                         CEPH(7, "ceph"),
    //
    ;

    private int    code;
    private String desc;

    StorageEnum(int code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
