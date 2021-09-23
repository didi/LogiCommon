package com.didiglobal.logi.security.common.entity;

import lombok.Data;

/**
 * @author cjm
 *
 * 操作日志信息（操作页面、操作类型、对象分类）
 */
@Data
public class OplogExtra {

    private Integer id;

    /**
     * 操作页面、操作类型、对象分类的信息
     */
    private String info;

    /**
     * 哪种信息：
     * 1：操作页面
     * 2：操作类型
     * 3：对象分类
     */
    private Integer type;
}
