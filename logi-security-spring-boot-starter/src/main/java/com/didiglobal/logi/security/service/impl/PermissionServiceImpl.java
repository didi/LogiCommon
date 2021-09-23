package com.didiglobal.logi.security.service.impl;

import com.didiglobal.logi.security.common.dto.permission.PermissionDTO;
import com.didiglobal.logi.security.common.entity.Permission;
import com.didiglobal.logi.security.common.enums.ResultCode;
import com.didiglobal.logi.security.common.vo.permission.PermissionTreeVO;
import com.didiglobal.logi.security.dao.PermissionDao;
import com.didiglobal.logi.security.exception.LogiSecurityException;
import com.didiglobal.logi.security.service.PermissionService;
import com.didiglobal.logi.security.service.RolePermissionService;
import com.didiglobal.logi.security.util.CopyBeanUtil;
import com.didiglobal.logi.security.util.MathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @author cjm
 */
@Service("logiSecurityPermissionServiceImpl")
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private RolePermissionService rolePermissionService;

    private PermissionTreeVO buildPermissionTree(Set<Integer> permissionHasSet) throws LogiSecurityException {
        // 获取全部权限，根据level小到大排序
        List<Permission> permissionList = permissionDao.selectAllAndAscOrderByLevel();

        // 创建一个虚拟根节点
        PermissionTreeVO root = PermissionTreeVO
                .builder().leaf(false).has(true).id(0).childList(new ArrayList<>()).build();

        // 转成树
        Map<Integer, PermissionTreeVO> parentMap = new HashMap<>(permissionList.size());
        parentMap.put(0, root);
        for(Permission permission : permissionList) {
            PermissionTreeVO permissionTreeVO = CopyBeanUtil.copy(permission, PermissionTreeVO.class);
            if(permission.getLeaf() != null && !permission.getLeaf()) {
                permissionTreeVO.setChildList(new ArrayList<>());
            }
            PermissionTreeVO parent = parentMap.get(permission.getParentId());
            if (parent == null) {
                // 如果parent为null，则需要查看下数据库权限表的数据是否有误
                // 1.可能出现了本来该是父节点的节点（有其他子节点的parent为它），但该节点parent为其他子节点的情况（数据异常）
                // 2.也可能是level填写错了（因为前面根据level大小排序）
                throw new LogiSecurityException(ResultCode.PERMISSION_DATA_ERROR);
            }
            // 父权限拥有，子权限才肯定拥有
            permissionTreeVO.setHas(parent.getHas() && permissionHasSet.contains(permission.getId()));
            parent.getChildList().add(permissionTreeVO);
            parentMap.put(permissionTreeVO.getId(), permissionTreeVO);
        }
        return root;
    }

    @Override
    public PermissionTreeVO buildPermissionTreeWithHas(List<Integer> permissionHasList) {
        PermissionTreeVO permissionTreeVO = null;
        try {
            permissionTreeVO = buildPermissionTree(new HashSet<>(permissionHasList));
        } catch (LogiSecurityException e) {
            e.printStackTrace();
        }
        return permissionTreeVO;
    }

    @Override
    public PermissionTreeVO buildPermissionTree() {
        return buildPermissionTreeWithHas(new ArrayList<>());
    }

    @Override
    public PermissionTreeVO buildPermissionTreeByRoleId(Integer roleId) {
        // 获取该角色拥有的全部权限id
        List<Integer> permissionIdList = rolePermissionService.getPermissionIdListByRoleId(roleId);
        return buildPermissionTreeWithHas(permissionIdList);
    }

    @Override
    public void savePermission(List<PermissionDTO> permissionDTOList) {
        if(CollectionUtils.isEmpty(permissionDTOList)) {
            return;
        }

        List<Permission> permissionList = new ArrayList<>();

        Map<PermissionDTO, Integer> permissionDTOMap = new HashMap<>();

        Queue<PermissionDTO> queue = new LinkedList<>();
        // 创建虚拟根节点
        PermissionDTO permissionDTO = new PermissionDTO();
        permissionDTO.setChildPermissionDTOList(permissionDTOList);
        queue.offer(permissionDTO);

        int level = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size-- > 0) {
                PermissionDTO dto = queue.poll();
                if(dto == null) {
                    continue;
                }
                Permission permission = CopyBeanUtil.copy(dto, Permission.class);

                // 设置层级
                permission.setLevel(level);
                // 设置父节点id
                permission.setParentId(permissionDTOMap.get(dto));
                // 没有子节点就是叶子节点
                permission.setLeaf(CollectionUtils.isEmpty(dto.getChildPermissionDTOList()));

                permission.setId(0);
                if(level > 0) {
                    // 设置id
                    permission.setId((int) getPermissionId());
                    permissionList.add(permission);
                }

                for(PermissionDTO temp : dto.getChildPermissionDTOList()) {
                    permissionDTOMap.put(temp, permission.getId());
                    queue.offer(temp);
                }
            }
            level++;
        }
        permissionDao.insertBatch(permissionList);
    }

    private long getPermissionId() {
        return System.currentTimeMillis() % 1000 * (long) Math.pow(10, 5) + MathUtil.getRandomNumber(5);
    }
}
