package com.didiglobal.logi.security.common;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
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

    public PagingData() {}

    public PagingData(List<T> bizData, Pagination pagination) {
        this.pagination = pagination;
        this.bizData = bizData;
    }

    public PagingData(List<T> bizData, IPage<?> pageInfo) {
        this.pagination = Pagination.builder()
                .total(pageInfo.getTotal()).pages(pageInfo.getPages())
                .pageNo(pageInfo.getCurrent()).pageSize(pageInfo.getSize())
                .build();
        this.bizData = bizData;
    }

    public PagingData(IPage<?> pageInfo) {
        this.pagination = Pagination.builder()
                .total(pageInfo.getTotal()).pages(pageInfo.getPages())
                .pageNo(pageInfo.getCurrent()).pageSize(pageInfo.getSize())
                .build();
        this.bizData = new ArrayList<>();
    }

    @Data
    @Builder
    @ApiModel(description = "分页基本信息")
    public static class Pagination {
        @ApiModelProperty(value = "总记录数")
        private long total;

        @ApiModelProperty(value = "页面总数")
        private long pages;

        @ApiModelProperty(value = "当前页码")
        private long pageNo;

        @ApiModelProperty(value = "单页大小")
        private long pageSize;
    }
}
