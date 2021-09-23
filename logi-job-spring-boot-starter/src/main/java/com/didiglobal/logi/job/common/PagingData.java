package com.didiglobal.logi.job.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * 分页信息
 *
 * @author cjm
 */
@Data
@ApiModel
public class PagingData<T> {

    @ApiModelProperty(value = "返回数据")
    private List<T> bizData;

    @ApiModelProperty(value = "分页信息")
    private Pagination pagination;

    public PagingData(List<T> bizData, long total, long pageNo, long pageSize) {
        this.bizData = bizData;
        this.pagination = new Pagination(total, pageNo, pageSize);
    }

    @Data
    @Builder
    @ApiModel(description = "分页基本信息")
    protected static class Pagination {
        @ApiModelProperty(value = "总记录数")
        private long total;

        @ApiModelProperty(value = "当前页码")
        private long pageNo;

        @ApiModelProperty(value = "单页大小")
        private long pageSize;
    }

}
