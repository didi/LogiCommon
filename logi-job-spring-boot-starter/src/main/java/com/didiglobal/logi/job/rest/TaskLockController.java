package com.didiglobal.logi.job.rest;

import com.didiglobal.logi.job.core.task.TaskLockService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constants.V1 + "/logi-job/taskLock")
@Api(tags = "logi-job 的任务锁相关接口")
public class TaskLockController {

    @Autowired
    private TaskLockService taskLockService;

    @PostMapping("/release")
    public Object release(@RequestParam String taskCode, @RequestParam String workerCode) {
        return taskLockService.tryRelease(taskCode, workerCode);
    }

    @GetMapping("/getAll")
    public Object getAll() {
        return taskLockService.getAll();
    }

}