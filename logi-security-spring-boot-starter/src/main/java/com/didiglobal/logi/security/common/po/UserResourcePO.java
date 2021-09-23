package com.didiglobal.logi.security.common.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author cjm
 *
 * 用户资源关系
 * 1.如果不启用资源查看控制权限，则所有人默认都具有所有资源的查看控制权限；
 *      此时该实体对应的表，只记录管理控制权限
 * 2.如果启用资源查看控制权限，则所有人默认都不具有所有资源的查看控制权限；
 *      此时该实体对应的表，不仅要只记录管理控制权限，还要记录查看权限和不具备任何权限
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "logi_user_resource")
public class UserResourcePO extends AppBasePO {

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 项目id
     */
    private Integer projectId;

    /**
     * 资源类别Id
     */
    private Integer resourceTypeId;

    /**
     * 资源id
     */
    private Integer resourceId;

    /**
     * 资源管理级别：
     * 1（查看权限）
     * 2（管理权限）
     */
    private Integer controlLevel;
}
