package com.didiglobal.logi.job.mapper;

import com.didiglobal.logi.job.common.po.LogIWorkerBlacklistPO;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * worker黑名单列表信息 Mapper 接口.
 * </p>
 *
 * @author ds
 * @since 2020-11-10
 */
public interface LogIWorkerBlacklistMapper {

    @Insert("INSERT INTO logi_worker_blacklist(worker_code) VALUES(#{workerCode})")
    int insert(LogIWorkerBlacklistPO logIWorkerBlacklistPO);

    @Delete("delete from logi_worker_blacklist where worker_code=#{workerCode}")
    int deleteByWorkerCode(@Param("workerCode") String workerCode);

    @Select("select id, worker_code from logi_worker_blacklist")
    List<LogIWorkerBlacklistPO> selectAll();

}
