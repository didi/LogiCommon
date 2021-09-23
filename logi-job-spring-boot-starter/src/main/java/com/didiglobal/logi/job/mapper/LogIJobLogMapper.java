package com.didiglobal.logi.job.mapper;

import com.didiglobal.logi.job.common.po.LogIJobLogPO;

import java.sql.Timestamp;
import java.util.List;

import com.didiglobal.logi.job.common.po.LogITaskPO;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.*;

/**
 * <p>
 * job执行历史日志 Mapper 接口.
 * </p>
 *
 * @author ds
 * @since 2020-11-10
 */
public interface LogIJobLogMapper {

    @Insert("INSERT INTO logi_job_log(job_code, task_code, task_id, task_name, "
            + "task_desc, class_name, try_times, worker_code, worker_ip,"
            + "start_time, end_time, status, error, result, create_time, update_time, app_name) "
            + "VALUES(#{jobCode}, #{taskCode}, #{taskId}, #{taskName}, #{taskDesc},#{className}, "
            + "#{tryTimes}, #{workerCode}, #{workerIp}, #{startTime}"
            + ", #{endTime}, #{status}, #{error}, #{result}, #{createTime}, #{updateTime}, #{appName})")
    int insert(LogIJobLogPO logIJobLogPO);

    @Select("select id, job_code, task_code, task_id, task_name, task_desc, "
            + "class_name, try_times, worker_code, worker_ip, start_time, "
            + "end_time, status, error, result, create_time, update_time, app_name from logi_job_log where "
            + "task_code=#{taskCode} and app_name=#{appName} order by id desc limit #{start}, #{size}")
    List<LogIJobLogPO> selectByTaskCode(@Param("taskCode") String taskCode,
                                        @Param("appName") String appName,
                                        @Param("start") Integer start,
                                        @Param("size") Integer size);

    @Update("update logi_job_log set job_code=#{jobCode}, task_code=#{taskCode}, task_id=#{taskId}, "
            + "task_name=#{taskName}, task_desc=#{taskDesc}, class_name=#{className}, try_times="
            + "#{tryTimes}, worker_code=#{workerCode}, worker_ip=#{workerIp}, start_time="
            + "#{startTime}, end_time=#{endTime}, status=#{status}, error=#{error}, result=#{result}, "
            + " create_time=#{createTime}, update_time=#{updateTime}, app_name=#{appName} where job_code=#{jobCode}")
    int updateByCode(LogIJobLogPO logIJobLogPO);

    @Delete("delete from logi_job_log where create_time<=#{createTime} and app_name=#{appName}")
    int deleteByCreateTime(@Param("createTime") Timestamp createTime, @Param("appName") String appName);

    @Select("select count(1) from logi_job_log where app_name=#{appName} and task_code=#{taskCode}")
    int selectCountByAppName(@Param("appName") String appName, @Param("taskCode") String taskCode);

    List<LogIJobLogPO> pagineListByCondition(@Param("appName") String appName,
                                             @Param("taskId") Long taskId,
                                             @Param("taskDesc") String taskDesc,
                                             @Param("jobStatus") Integer jobStatus,
                                             @Param("start") Integer start, @Param("size") Integer size,
                                             @Param("beginTime") Timestamp beginTime,
                                             @Param("endTime") Timestamp endTime);

    Integer pagineCountByCondition(@Param("appName") String appName, @Param("taskId") Long taskId,
                                   @Param("taskDesc") String taskDesc, @Param("jobStatus") Integer jobStatus,
                                   @Param("beginTime") Timestamp beginTime, @Param("endTime") Timestamp endTime);
}
