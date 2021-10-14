package com.didiglobal.logi.job.core.job;

import com.didiglobal.logi.job.common.dto.TaskLogPageQueryDTO;
import com.didiglobal.logi.job.common.vo.LogIJobLogVO;

import java.util.List;

public interface JobLogManager {

    /**
     * @param pageQueryDTO 分页查询条件
     * @return 查询信息
     */
    List<LogIJobLogVO> pagineJobLogs(TaskLogPageQueryDTO pageQueryDTO);

    /**
     * @param pageQueryDTO 分页查询条件
     * @return 数量
     */
    int getJobLogsCount(TaskLogPageQueryDTO pageQueryDTO);
}
