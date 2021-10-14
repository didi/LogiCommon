package com.didiglobal.logi.log.common.api;

/**
 * @author jinbinbin
 * @version $Id: ResultCode.java, v 0.1 2018年01月08日 20:22 jinbinbin Exp $
 */
public interface ResultCode {
    /**
     * 成功
     */
    ResultCodeEntry SUCCESS            = new ResultCodeEntry(0, "成功");
    /**
     * 参数错误
     */
    ResultCodeEntry ILLEGAL_PARAM      = new ResultCodeEntry(10000, "参数错误");
    /**
     * 资源未就绪
     */
    ResultCodeEntry RESOURCE_NOT_READY = new ResultCodeEntry(10001, "资源未就绪");
    /**
     * 资源审批中
     */
    ResultCodeEntry RESOURCE_APPROVING = new ResultCodeEntry(10002, "资源审批中");
}
