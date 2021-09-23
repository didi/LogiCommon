package com.didiglobal.logi.job.rest;

import com.didiglobal.logi.job.common.PagingResult;
import com.didiglobal.logi.job.common.Result;
import com.didiglobal.logi.job.common.domain.LogITask;
import com.didiglobal.logi.job.common.dto.TaskPageQueryDTO;
import com.didiglobal.logi.job.common.vo.LogITaskVO;
import com.didiglobal.logi.job.core.consensual.ConsensualEnum;
import com.didiglobal.logi.job.core.task.TaskManager;
import com.didiglobal.logi.job.utils.BeanUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * task controller.
 *
 * @author ds
 */
@RestController
@RequestMapping(Constants.V1 + "/logi-job/task")
@Api(tags = "logi-job 配置的 task 相关接口")
public class TaskController {

    @Autowired
    private TaskManager taskManager;

    @PostMapping("/{taskCode}/do")
    public Result<Boolean> execute(@PathVariable String taskCode) {
        return taskManager.execute(taskCode, false);
    }

    @PostMapping("/list")
    public PagingResult<LogITaskVO> getAll(@RequestBody TaskPageQueryDTO taskPageQueryDTO) {
        List<LogITask> logITasks = taskManager.getPagineList(taskPageQueryDTO);
        int count = taskManager.pagineTaskConut(taskPageQueryDTO);

        return PagingResult.buildSucc(
                logITask2LogITaskVO(logITasks), count, taskPageQueryDTO.getPage(), taskPageQueryDTO.getSize()
        );
    }

    @PostMapping("/{taskCode}/{status}")
    public Result<Boolean> status(@PathVariable String taskCode, @PathVariable Integer status) {
        return taskManager.updateTaskStatus(taskCode, status);
    }

    @GetMapping("/{taskCode}/detail")
    public Result<LogITaskVO> detail(@PathVariable String taskCode) {
        return Result.buildSucc(logITask2LogITaskVO(taskManager.getByCode(taskCode)));
    }

    @PostMapping("/{taskCode}/{workerCode}/release")
    public Result<Boolean> release(@PathVariable String taskCode, @PathVariable String workerCode) {
        return taskManager.release(taskCode, workerCode);
    }

    @DeleteMapping("/{taskCode}")
    public Result<Boolean> delete(@PathVariable String taskCode) {
        return taskManager.delete(taskCode);
    }

    /**************************************** private method ****************************************************/
    private List<LogITaskVO> logITask2LogITaskVO(List<LogITask> logITasks) {
        if (CollectionUtils.isEmpty(logITasks)) {
            return new ArrayList<>();
        }

        return logITasks.stream().map(l -> logITask2LogITaskVO(l)).collect(Collectors.toList());
    }

    private LogITaskVO logITask2LogITaskVO(LogITask logITask) {
        LogITaskVO logITaskVO = BeanUtil.convertTo(logITask, LogITaskVO.class);

        if (!CollectionUtils.isEmpty(logITask.getTaskWorkers())) {
            List<String> ips = logITask.getTaskWorkers().stream().map(w -> w.getIp()).collect(Collectors.toList());

            logITaskVO.setRouting(ConsensualEnum.getByName(logITask.getConsensual()).getDesc());
            logITaskVO.setWorkerIps(ips);
        }

        return logITaskVO;
    }
}
