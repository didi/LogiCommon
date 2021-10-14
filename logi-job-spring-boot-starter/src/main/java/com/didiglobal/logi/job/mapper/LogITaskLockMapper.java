package com.didiglobal.logi.job.mapper;

import com.didiglobal.logi.job.common.po.LogITaskLockPO;

import java.util.List;

import org.apache.ibatis.annotations.*;

/**
 * <p>
 * 任务锁 Mapper 接口.
 * </p>
 *
 * @author ds
 * @since 2020-11-10
 */
@Mapper
public interface LogITaskLockMapper {

    @Insert("INSERT INTO logi_task_lock(task_code, worker_code, expire_time, create_time, update_time, app_name)"
            + " VALUES(#{taskCode}, #{workerCode}, #{expireTime}, #{createTime}, #{updateTime}, #{appName})")
    int insert(LogITaskLockPO logITaskLockPO);

    @Update("update logi_task_lock set expire_time=#{expireTime} where id=#{id}")
    int update(@Param("id") Long id, @Param("expireTime") Long expireTime);

    @Select("select id, task_code, worker_code, expire_time, create_time, update_time, app_name "
            + "from logi_task_lock where app_name=#{appName}")
    List<LogITaskLockPO> selectByAppName(@Param("appName") String appName);

    @Select("select id, task_code, worker_code, expire_time, create_time, update_time, app_name "
            + "from logi_task_lock where task_code=#{taskCode} and app_name=#{appName}")
    List<LogITaskLockPO> selectByTaskCode(@Param("taskCode") String taskCode, @Param("appName") String appName);

    @Select("select id, task_code, worker_code, expire_time, create_time, update_time, app_name "
            + "from logi_task_lock where worker_code=#{workerCode} and app_name=#{appName}")
    List<LogITaskLockPO> selectByWorkerCode(@Param("workerCode") String workerCode, @Param("appName") String appName);

    @Select("select id, task_code, worker_code, expire_time, create_time, update_time, app_name from "
            + "logi_task_lock where task_code=#{taskCode} and worker_code=#{workerCode} and app_name=#{appName}")
    List<LogITaskLockPO> selectByTaskCodeAndWorkerCode(@Param("taskCode") String taskCode,
                                                       @Param("workerCode") String workerCode,
                                                       @Param("appName") String appName);

    @Delete("delete from logi_task_lock where id=#{id}")
    int deleteById(@Param("id") Long id);

    int deleteByIds(List<Long> ids);

}
