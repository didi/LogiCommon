package com.didiglobal.logi.security.common.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author cjm
 *
 * 用户项目关系
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "logi_user_project")
public class UserProjectPO extends AppBasePO {

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 项目id
     */
    private Integer projectId;
}
