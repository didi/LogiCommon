package com.didiglobal.logi.job.core.job;

import com.didiglobal.logi.job.common.dto.TaskLogPageQueryDTO;
import com.didiglobal.logi.job.common.vo.LogIJobLogVO;

import java.util.List;

public interface JobLogManager {

    /**
     * @param pageQueryDTO
     * @return
     */
    List<LogIJobLogVO> pagineJobLogs(TaskLogPageQueryDTO pageQueryDTO);

    /**
     * @param pageQueryDTO
     * @return
     */
    int getJobLogsCount(TaskLogPageQueryDTO pageQueryDTO);
}
