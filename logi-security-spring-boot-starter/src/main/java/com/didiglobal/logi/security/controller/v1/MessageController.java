package com.didiglobal.logi.security.controller.v1;

import com.didiglobal.logi.security.common.constant.Constants;
import com.didiglobal.logi.security.common.Result;
import com.didiglobal.logi.security.common.vo.message.MessageVO;
import com.didiglobal.logi.security.service.MessageService;
import com.didiglobal.logi.security.util.HttpRequestUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author cjm
 */
@RestController
@Api(value = "message相关API接口", tags = "消息相关API接口")
@RequestMapping(Constants.V1 + "/logi-security/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping(value = {"/list/{readTag}", "/list"})
    @ApiOperation(value = "获取所有消息", notes = "根据是否读已读获取消息")
    @ApiImplicitParam(name = "readTag", value = "消息状态（true已读，false未读，null全部）", dataType = "Boolean")
    public Result<List<MessageVO>> list(@PathVariable(required = false) Boolean readTag, HttpServletRequest request) {
        // 获取当前用户id
        Integer userId = HttpRequestUtil.getOperatorId(request);
        List<MessageVO> messageVOList = messageService.getMessageListByUserIdAndReadTag(userId, readTag);
        return Result.success(messageVOList);
    }

    @PutMapping("/switch")
    @ApiOperation(value = "更改消息状态", notes = "调用该接口则消息状态被反转")
    public Result<String> switched(@RequestBody @ApiParam(name = "idList", value = "消息idList") List<Integer> idList) {
        messageService.changeMessageStatus(idList);
        return Result.success();
    }
}
