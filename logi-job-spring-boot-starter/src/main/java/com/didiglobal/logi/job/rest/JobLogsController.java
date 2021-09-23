package com.didiglobal.logi.job.rest;

import com.didiglobal.logi.job.common.PagingResult;
import com.didiglobal.logi.job.common.dto.TaskLogPageQueryDTO;
import com.didiglobal.logi.job.common.dto.TaskPageQueryDTO;
import com.didiglobal.logi.job.common.vo.LogIJobLogVO;
import com.didiglobal.logi.job.core.job.JobLogManager;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * job 的启动、暂停、job信息、job日志相关操作.
 *
 * @author ds
 */
@RestController
@RequestMapping(Constants.V1 + "/logi-job/logs")
@Api(tags = "logi-job 执行生成的任务日志相关接口")
public class JobLogsController {
    @Autowired
    private JobLogManager jobLogManager;

    @PostMapping("/list")
    public PagingResult<LogIJobLogVO> getJobLogs(@RequestBody TaskLogPageQueryDTO pageQueryDTO) {
        List<LogIJobLogVO> logIJobLogVOS = jobLogManager.pagineJobLogs(pageQueryDTO);
        int taotalCount = jobLogManager.getJobLogsCount(pageQueryDTO);

        return PagingResult.buildSucc(logIJobLogVOS, taotalCount, pageQueryDTO.getPage(), pageQueryDTO.getSize());
    }
}
