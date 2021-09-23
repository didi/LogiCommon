package com.didiglobal.logi.job.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 分页数据统一返回规范
 *
 * @author cjm
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "分页统一返回格式")
public class PagingResult<T> extends BaseResult {

    @ApiModelProperty(value = "返回分页基本信息")
    private PagingData<T> data;

    public PagingResult(PagingData<T> data) {
        this.data = data;
    }

    public PagingResult(List<T> records, long total, long pageNo, long pageSize) {
        this.data = new PagingData<>(records, total, pageNo, pageSize);
    }

    public static <T> PagingResult<T> buildSucc(List<T> records, long total, long pageNo, long pageSize) {
        PagingResult paginationResult = new PagingResult(records, total, pageNo, pageSize);
        paginationResult.setCode(ResultType.SUCCESS.getCode());
        paginationResult.setMessage(ResultType.SUCCESS.getMessage());
        return paginationResult;
    }

}