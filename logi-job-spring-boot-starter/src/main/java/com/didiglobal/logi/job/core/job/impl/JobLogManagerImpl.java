package com.didiglobal.logi.job.core.job.impl;

import com.didiglobal.logi.job.LogIJobProperties;
import com.didiglobal.logi.job.common.domain.LogITask;
import com.didiglobal.logi.job.common.dto.TaskLogPageQueryDTO;
import com.didiglobal.logi.job.common.po.LogIJobLogPO;
import com.didiglobal.logi.job.common.vo.LogIJobLogVO;
import com.didiglobal.logi.job.core.job.JobLogManager;
import com.didiglobal.logi.job.core.task.TaskManager;
import com.didiglobal.logi.job.mapper.LogIJobLogMapper;
import com.didiglobal.logi.job.utils.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class JobLogManagerImpl implements JobLogManager {

    private TaskManager taskManager;
    private LogIJobLogMapper logIJobLogMapper;
    private LogIJobProperties logIJobProperties;


    @Autowired
    public JobLogManagerImpl(TaskManager taskManager,
                             LogIJobLogMapper logIJobLogMapper,
                             LogIJobProperties logIJobProperties) {
        this.taskManager = taskManager;
        this.logIJobLogMapper = logIJobLogMapper;
        this.logIJobProperties = logIJobProperties;
    }

    @Override
    public List<LogIJobLogVO> pagineJobLogs(TaskLogPageQueryDTO dto) {
        Map<Long, LogITask> longLogITaskMap = new HashMap<>();

        Timestamp beginTimestamp = null;
        Timestamp endTimestamp = null;

        if (null != dto.getBeginTime()) {
            beginTimestamp = new Timestamp(dto.getBeginTime());
        }

        if (null != dto.getEndTime()) {
            endTimestamp = new Timestamp(dto.getEndTime());
        }

        List<LogIJobLogPO> logIJobLogPOS = logIJobLogMapper.pagineListByCondition(logIJobProperties.getAppName(),
                dto.getTaskId(), dto.getTaskDesc(), dto.getTaskStatus(),
                (dto.getPage() - 1) * dto.getSize(), dto.getSize(),
                beginTimestamp, endTimestamp);

        if (CollectionUtils.isEmpty(logIJobLogPOS)) {
            return null;
        }

        return logIJobLogPOS.stream().map(logIJobLogPO -> {
            LogIJobLogVO logIJobLogVO = BeanUtil.convertTo(logIJobLogPO, LogIJobLogVO.class);

            LogITask logITask = longLogITaskMap.get(logIJobLogPO.getTaskId());
            if (null == logITask) {
                logITask = taskManager.getByCode(logIJobLogPO.getTaskCode());
                longLogITaskMap.put(logIJobLogPO.getTaskId(), logITask);
            }

            List<String> ips = logITask.getTaskWorkers().stream().map(w -> w.getIp()).collect(Collectors.toList());
            logIJobLogVO.setAllWorkerIps(ips);

            logIJobLogVO.setTaskName(logITask.getTaskName());
            return logIJobLogVO;
        }).collect(Collectors.toList());
    }

    @Override
    public int getJobLogsCount(TaskLogPageQueryDTO dto) {

        Timestamp beginTimestamp = null;
        Timestamp endTimestamp = null;

        if (null != dto.getBeginTime()) {
            beginTimestamp = new Timestamp(dto.getBeginTime());
        }

        if (null != dto.getEndTime()) {
            endTimestamp = new Timestamp(dto.getEndTime());
        }

        return logIJobLogMapper.pagineCountByCondition(logIJobProperties.getAppName(),
                dto.getTaskId(), dto.getTaskDesc(), dto.getTaskStatus(),
                beginTimestamp, endTimestamp);
    }
}
