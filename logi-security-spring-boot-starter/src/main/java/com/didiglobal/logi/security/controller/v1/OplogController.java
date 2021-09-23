package com.didiglobal.logi.security.controller.v1;

import com.didiglobal.logi.security.common.constant.Constants;
import com.didiglobal.logi.security.common.PagingData;
import com.didiglobal.logi.security.common.PagingResult;
import com.didiglobal.logi.security.common.Result;
import com.didiglobal.logi.security.common.dto.oplog.OplogQueryDTO;
import com.didiglobal.logi.security.common.enums.oplog.OplogCode;
import com.didiglobal.logi.security.common.vo.oplog.OplogVO;
import com.didiglobal.logi.security.service.OplogExtraService;
import com.didiglobal.logi.security.service.OplogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author cjm
 */
@RestController
@Api(value = "oplog相关API接口", tags = "操作日志相关API接口")
@RequestMapping(Constants.V1 + "/logi-security/oplog")
public class OplogController {

    @Autowired
    private OplogService oplogService;

    @Autowired
    private OplogExtraService oplogExtraService;

    @PostMapping("/page")
    @ApiOperation(value = "查询操作日志列表", notes = "分页和条件查询")
    public PagingResult<OplogVO> page(@RequestBody OplogQueryDTO queryDTO) {
        PagingData<OplogVO> pageOplog = oplogService.getOplogPage(queryDTO);
        return PagingResult.success(pageOplog);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "获取操作日志详情", notes = "根据操作日志id获取操作日志详情")
    @ApiImplicitParam(name = "id", value = "操作日志id", dataType = "int", required = true)
    public Result<OplogVO> get(@PathVariable Integer id) {
        OplogVO oplogVO = oplogService.getOplogDetailByOplogId(id);
        return Result.success(oplogVO);
    }

    @GetMapping("/extra/{type}")
    @ApiOperation(value = "获取操作日志列表的查询条件信息", notes = "操作日志列表/查询条件中操作类型、对象类型的下拉信息")
    @ApiImplicitParam(name = "type", value = "哪种信息：1：操作页面、2：操作类型、3：对象分类", required = true)
    public Result<List<String>> extra(@PathVariable Integer type) {
        List<String> oplogExtraList = oplogExtraService.getOplogExtraNameListByType(type);
        return Result.success(oplogExtraList);
    }

    @PostMapping("/extra/import/operate/page")
    @ApiOperation(value = "导入操作日志extra信息（操作页面）", notes = "批量导入操作页面nameList")
    public Result<String> operatePage(@RequestBody @ApiParam(name = "list", value = "操作页面nameList") List<String> list) {
        oplogExtraService.saveOplogExtraList(list, OplogCode.OPERATE_PAGE);
        return Result.success();
    }

    @PostMapping("/extra/import/operate/type")
    @ApiOperation(value = "导入操作日志extra信息（操作类型）", notes = "批量导入操作类型nameList")
    public Result<String> operateType(@RequestBody @ApiParam(name = "list", value = "操作类型nameList") List<String> list) {
        oplogExtraService.saveOplogExtraList(list, OplogCode.OPERATE_TYPE);
        return Result.success();
    }

    @PostMapping("/extra/import/target/type")
    @ApiOperation(value = "导入操作日志extra信息（对象分类）", notes = "批量导入对象分类nameList")
    public Result<String> targetType(@RequestBody @ApiParam(name = "list", value = "对象分类nameList") List<String> list) {
        oplogExtraService.saveOplogExtraList(list, OplogCode.TARGET_TYPE);
        return Result.success();
    }
}
