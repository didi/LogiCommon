package com.didiglobal.logi.job.mapper;

import com.didiglobal.logi.job.common.po.LogITaskPO;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 * 任务信息 Mapper 接口.
 * </p>
 *
 * @author ds
 * @since 2020-11-10
 */
public interface LogITaskMapper {

    @Insert("INSERT INTO logi_task(task_code, task_name, task_desc, cron, class_name, params, retry_times,"
            + " last_fire_time, timeout, status, sub_task_codes, consensual, task_worker_str, app_name, owner) "
            + "VALUES(#{taskCode}, #{taskName}, #{taskDesc}, #{cron}, #{className}, #{params}, "
            + "#{retryTimes}, #{lastFireTime}, #{timeout}, #{status}, #{subTaskCodes}, "
            + "#{consensual}, #{taskWorkerStr}, #{appName}, #{owner})")
    int insert(LogITaskPO logITaskPO);

    @Delete("delete from logi_task where task_code=#{taskCode} and app_name=#{appName}")
    int deleteByCode(@Param("taskCode") String taskCode, @Param("appName") String appName);

    @Update("update logi_task set task_name=#{taskName}, task_desc=#{taskDesc}, cron=#{cron}, class_name="
            + "#{className}, params=#{params}, retry_times=#{retryTimes}, last_fire_time="
            + "#{lastFireTime}, timeout=#{timeout}, status=#{status}, sub_task_codes=#{subTaskCodes},"
            + " consensual=#{consensual}, task_worker_str=#{taskWorkerStr}, owner=#{owner} where task_code=#{taskCode}")
    int updateByCode(LogITaskPO logITaskPO);

    @Select("select id, task_code, task_name, task_desc, cron, class_name, params, retry_times, "
            + "last_fire_time, timeout, status, sub_task_codes, consensual"
            + ", task_worker_str, create_time, update_time, app_name, owner "
            + "from logi_task where task_code=#{taskCode} and app_name=#{appName}")
    LogITaskPO selectByCode(@Param("taskCode") String code, @Param("appName") String appName);

    @Select("<script>"
            + "select id, task_code, task_name, task_desc, cron, class_name, params, retry_times, "
            + "last_fire_time, timeout, status, sub_task_codes, consensual, task_worker_str, "
            + "create_time, update_time, app_name, owner from logi_task where app_name=#{appName} and codes in "
            + "<foreach collection='codes' item='code' index='index' open='(' close=')' "
            + "separator=','>"
            + "  #{code} "
            + "</foreach> "
            + "</script>")
    List<LogITaskPO> selectByCodes(@Param("codes") List<String> codes, @Param("appName") String appName);

    @Select("select id, task_code, task_name, task_desc, cron, class_name, params, retry_times, "
            + "last_fire_time, timeout, status, sub_task_codes, consensual, task_worker_str,"
            + " create_time, update_time, app_name, owner from logi_task")
    List<LogITaskPO> selectAll();

    @Select("select id, task_code, task_name, task_desc, cron, class_name, params, retry_times, "
            + "last_fire_time, timeout, status, sub_task_codes, consensual, task_worker_str,"
            + " create_time, update_time, app_name, owner from logi_task where app_name=#{appName}")
    List<LogITaskPO> selectByAppName(@Param("appName") String appName);

    @Select("select id, task_code, task_name, task_desc, cron, class_name, params, retry_times, "
            + "last_fire_time, timeout, status, sub_task_codes, consensual, task_worker_str,"
            + " create_time, update_time, app_name, owner from logi_task where app_name=#{appName} and status=1")
    List<LogITaskPO> selectRuningByAppName(@Param("appName") String appName);

    @Select("select id, task_code, task_name, task_desc, cron, class_name, params, retry_times, "
            + "last_fire_time, timeout, status, sub_task_codes, consensual, task_worker_str,"
            + " create_time, update_time, app_name, owner from logi_task where app_name=#{appName} "
            + " order by id desc limit #{start}, #{size} ")
    List<LogITaskPO> selectByAppNameAndSize(@Param("appName") String appName,
                                            @Param("start") int start,
                                            @Param("size") int size);

    @Select("select count(1) from logi_task where app_name=#{appName}")
    int selectCountByAppName(@Param("appName") String appName);

    List<LogITaskPO> pagineListByCondition(@Param("appName") String appName, @Param("id") Long id,
                                           @Param("taskDesc") String taskDesc, @Param("className") String className,
                                           @Param("jobStatus") Integer jobStatus, @Param("start") Integer start,
                                           @Param("size") Integer size);

    Integer pagineCountByCondition(@Param("appName") String appName, @Param("id") Long id,
                                   @Param("taskDesc") String taskDesc, @Param("className") String className,
                                   @Param("jobStatus") Integer jobStatus);
}
