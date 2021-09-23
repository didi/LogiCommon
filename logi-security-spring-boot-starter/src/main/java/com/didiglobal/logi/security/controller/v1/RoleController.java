package com.didiglobal.logi.security.controller.v1;

import com.didiglobal.logi.security.common.constant.Constants;
import com.didiglobal.logi.security.common.PagingData;
import com.didiglobal.logi.security.common.PagingResult;
import com.didiglobal.logi.security.common.Result;
import com.didiglobal.logi.security.common.vo.role.AssignInfoVO;
import com.didiglobal.logi.security.common.dto.role.RoleAssignDTO;
import com.didiglobal.logi.security.common.dto.role.RoleQueryDTO;
import com.didiglobal.logi.security.common.dto.role.RoleSaveDTO;
import com.didiglobal.logi.security.common.vo.role.RoleBriefVO;
import com.didiglobal.logi.security.common.vo.role.RoleDeleteCheckVO;
import com.didiglobal.logi.security.common.vo.role.RoleVO;
import com.didiglobal.logi.security.exception.LogiSecurityException;
import com.didiglobal.logi.security.service.RoleService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author cjm
 */
@RestController
@Api(value = "role相关API接口", tags = "角色相关API接口")
@RequestMapping(Constants.V1 + "/logi-security/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/{id}")
    @ApiOperation(value = "获取角色详情", notes = "根据角色id或角色code获取角色详情")
    @ApiImplicitParam(name = "id", value = "角色id", dataType = "int", required = true)
    public Result<RoleVO> detail(@PathVariable Integer id) {
        RoleVO roleVo = roleService.getRoleDetailByRoleId(id);
        return Result.success(roleVo);
    }

    @PutMapping
    @ApiOperation(value = "更新角色信息", notes = "根据角色id更新角色信息")
    public Result<String> update(@RequestBody RoleSaveDTO saveDTO, HttpServletRequest request) {
        try {
            roleService.updateRole(saveDTO, request);
            return Result.success();
        } catch (LogiSecurityException e) {
            e.printStackTrace();
            return Result.fail(e);
        }
    }

    @PostMapping
    @ApiOperation(value = "创建角色", notes = "创建角色")
    public Result<String> create(@RequestBody RoleSaveDTO saveDTO, HttpServletRequest request) {
        try {
            roleService.createRole(saveDTO, request);
            return Result.success();
        } catch (LogiSecurityException e) {
            e.printStackTrace();
            return Result.fail(e);
        }
    }

    @DeleteMapping("/delete/check/{id}")
    @ApiOperation(value = "删除角色前的检查", notes = "判断该角色是否已经分配给用户，如有分配给用户，则返回用户的信息list")
    @ApiImplicitParam(name = "id", value = "角色id", dataType = "int", required = true)
    public Result<RoleDeleteCheckVO> check(@PathVariable Integer id) {
        RoleDeleteCheckVO deleteCheckVO = roleService.checkBeforeDelete(id);
        return Result.success(deleteCheckVO);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除角色", notes = "根据角色id删除角色")
    @ApiImplicitParam(name = "id", value = "角色id", dataType = "int", required = true)
    public Result<String> delete(@PathVariable Integer id, HttpServletRequest request) {
        try {
            roleService.deleteRoleByRoleId(id, request);
        } catch (LogiSecurityException e) {
            e.printStackTrace();
            return Result.fail(e);
        }
        return Result.success();
    }

    @PostMapping("/page")
    @ApiOperation(value = "分页查询角色列表", notes = "分页和条件查询")
    public PagingResult<RoleVO> page(@RequestBody RoleQueryDTO queryDTO) {
        PagingData<RoleVO> pageRole = roleService.getRolePage(queryDTO);
        return PagingResult.success(pageRole);
    }

    @PostMapping("/assign")
    @ApiOperation(value = "分配角色", notes = "分配一个角色给多个用户或分配多个角色给一个用户")
    public Result<String> assign(@RequestBody RoleAssignDTO assignDTO, HttpServletRequest request) {
        try {
            roleService.assignRoles(assignDTO, request);
            return Result.success();
        } catch (LogiSecurityException e) {
            e.printStackTrace();
            return Result.fail(e);
        }
    }

    @GetMapping(value = "/assign/list/{roleId}")
    @ApiOperation(value = "角色管理/分配用户/列表", notes = "查询所有用户列表，并根据角色id，标记哪些用户拥有该角色")
    @ApiImplicitParam(name = "roleId", value = "角色id", dataType = "int", required = true)
    public Result<List<AssignInfoVO>> assignList(@PathVariable Integer roleId) {
        List<AssignInfoVO> assignInfoVOList = roleService.getAssignInfoByRoleId(roleId);
        return Result.success(assignInfoVOList);
    }

    @GetMapping(value = {"/list/{roleName}", "/list"})
    @ApiOperation(value = "根据角色名模糊查询", notes = "用户管理/列表查询条件/分配角色框，这里会用到此接口")
    @ApiImplicitParam(name = "roleName", value = "角色名（为null，查询全部）", dataType = "String")
    public Result<List<RoleBriefVO>> list(@PathVariable(required = false) String roleName) {
        List<RoleBriefVO> roleBriefVOList = roleService.getRoleBriefListByRoleName(roleName);
        return Result.success(roleBriefVOList);
    }
}