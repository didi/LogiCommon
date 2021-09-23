package com.didiglobal.logi.security.controller.v1;

import com.didiglobal.logi.security.common.constant.Constants;
import com.didiglobal.logi.security.common.PagingData;
import com.didiglobal.logi.security.common.PagingResult;
import com.didiglobal.logi.security.common.Result;
import com.didiglobal.logi.security.common.dto.project.ProjectQueryDTO;
import com.didiglobal.logi.security.common.dto.project.ProjectSaveDTO;
import com.didiglobal.logi.security.common.vo.project.ProjectBriefVO;
import com.didiglobal.logi.security.common.vo.project.ProjectDeleteCheckVO;
import com.didiglobal.logi.security.common.vo.project.ProjectVO;
import com.didiglobal.logi.security.exception.LogiSecurityException;
import com.didiglobal.logi.security.service.ProjectService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author cjm
 */
@RestController
@Api(value = "project相关API接口", tags = "项目相关API接口")
@RequestMapping(Constants.V1 + "/logi-security/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/{id}")
    @ApiOperation(value = "获取项目详情", notes = "根据项目id获取项目详情")
    @ApiImplicitParam(name = "id", value = "项目id", dataType = "int", required = true)
    public Result<ProjectVO> detail(@PathVariable Integer id) {
        try {
            ProjectVO projectVO = projectService.getProjectDetailByProjectId(id);
            return Result.success(projectVO);
        } catch (LogiSecurityException e) {
            return Result.fail(e);
        }
    }

    @PutMapping("/switch/{id}")
    @ApiOperation(value = "更改项目运行状态", notes = "调用该接口则项目运行状态被反转")
    @ApiImplicitParam(name = "id", value = "项目id", dataType = "int", required = true)
    public Result<String> switched(@PathVariable Integer id, HttpServletRequest request) {
        projectService.changeProjectStatus(id, request);
        return Result.success();
    }

    @PutMapping
    @ApiOperation(value = "更新项目", notes = "根据项目id更新项目信息")
    public Result<String> update(@RequestBody ProjectSaveDTO saveDTO, HttpServletRequest request) {
        try {
            projectService.updateProject(saveDTO, request);
        } catch (LogiSecurityException e) {
            e.printStackTrace();
            return Result.fail(e);
        }
        return Result.success();
    }

    @PostMapping
    @ApiOperation(value = "创建项目", notes = "创建项目")
    public Result<ProjectVO> create(@RequestBody ProjectSaveDTO saveDTO, HttpServletRequest request) {
        try {
            ProjectVO projectVO = projectService.createProject(saveDTO, request);
            return Result.success(projectVO);
        } catch (LogiSecurityException e) {
            e.printStackTrace();
            return Result.fail(e);
        }
    }

    @GetMapping("/delete/check/{id}")
    @ApiOperation(value = "删除项目前的检查", notes = "检查是否有服务引用了该项目、是否有具体资源挂上了该项目")
    @ApiImplicitParam(name = "id", value = "项目id", dataType = "int", required = true)
    public Result<ProjectDeleteCheckVO> deleteCheck(@PathVariable Integer id) {
        ProjectDeleteCheckVO deleteCheckVO = projectService.checkBeforeDelete(id);
        return Result.success(deleteCheckVO);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除项目", notes = "根据项目id删除项目")
    @ApiImplicitParam(name = "id", value = "项目id", dataType = "int", required = true)
    public Result<String> delete(@PathVariable Integer id, HttpServletRequest request) {
        projectService.deleteProjectByProjectId(id, request);
        return Result.success();
    }

    @PostMapping("/page")
    @ApiOperation(value = "分页查询项目列表", notes = "分页和条件查询")
    public PagingResult<ProjectVO> page(@RequestBody ProjectQueryDTO queryDTO) {
        PagingData<ProjectVO> pageProject = projectService.getProjectPage(queryDTO);
        return PagingResult.success(pageProject);
    }

    @GetMapping("/list")
    @ApiOperation(value = "获取所有项目简要信息", notes = "获取全部项目简要信息（只返回id、项目名）")
    public Result<List<ProjectBriefVO>> list() {
        List<ProjectBriefVO> projectBriefVOList = projectService.getProjectBriefList();
        return Result.success(projectBriefVOList);
    }
}
