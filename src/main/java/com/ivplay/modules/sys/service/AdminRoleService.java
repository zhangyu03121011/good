package com.ivplay.modules.sys.service;

import com.ivplay.common.base.service.BaseService;
import com.ivplay.modules.sys.model.AdminRole;

/**
 * @author cuiP
 * Created by JK on 2017/2/16.
 */
public interface AdminRoleService extends BaseService<AdminRole> {
    /**
     * 根据用户ID和角色ID查询用户和角色绑定信息
     * @param userId
     * @param roleId
     * @return
     */
    AdminRole findByUserIdAndRoleId(Long userId, Long roleId);
}
