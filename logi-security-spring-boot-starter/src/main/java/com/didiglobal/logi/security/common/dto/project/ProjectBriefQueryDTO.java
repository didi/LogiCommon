package com.didiglobal.logi.security.common.dto.project;

import com.didiglobal.logi.security.common.dto.PageParamDTO;
import com.didiglobal.logi.security.common.dto.resource.MByRQueryDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author cjm
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ProjectBriefQueryDTO extends PageParamDTO {

    /**
     * 项目名
     */
    private String projectName;

    public ProjectBriefQueryDTO(MByRQueryDTO queryDTO) {
        this.setPage(queryDTO.getPage());
        this.setSize(queryDTO.getSize());
        this.projectName = queryDTO.getName();
    }
}
