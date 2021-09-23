package com.didiglobal.logi.security.controller.v1;

import com.didiglobal.logi.security.common.constant.Constants;
import com.didiglobal.logi.security.common.Result;
import com.didiglobal.logi.security.common.dto.dept.DeptDTO;
import com.didiglobal.logi.security.common.vo.dept.DeptTreeVO;
import com.didiglobal.logi.security.service.DeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author cjm
 */
@RestController
@Api(value = "dept相关API接口", tags = "部门相关API接口")
@RequestMapping(Constants.V1 + "/logi-security/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    @GetMapping("/tree")
    @ApiOperation(value = "获取所有部门", notes = "以树的形式返回所有部门")
    public Result<DeptTreeVO> tree() {
        DeptTreeVO deptTreeVO = deptService.buildDeptTree();
        return Result.success(deptTreeVO);
    }

    @PostMapping("/import")
    @ApiOperation(value = "部门信息导入", notes = "部门信息导入")
    public Result<String> imports(
            @RequestBody @ApiParam(name = "deptDTOList", value = "部门信息List") List<DeptDTO> deptDTOList) {
        deptService.saveDept(deptDTOList);
        return Result.success();
    }
}
