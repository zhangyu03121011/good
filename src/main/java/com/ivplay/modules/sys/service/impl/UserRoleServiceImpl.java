package com.ivplay.modules.sys.service.impl;

import com.ivplay.common.base.service.impl.BaseServiceImpl;
import com.ivplay.modules.sys.model.AdminRole;
import com.ivplay.modules.sys.service.AdminRoleService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author cuiP
 * Created by JK on 2017/2/16.
 */
@Transactional
@Service
public class UserRoleServiceImpl extends BaseServiceImpl<AdminRole> implements AdminRoleService{
    @Transactional(readOnly = true)
    @Override
    public AdminRole findByUserIdAndRoleId(Long userId, Long roleId) {
        AdminRole userRole = new AdminRole();
        userRole.setUserId(userId);
        userRole.setRoleId(roleId);
        return super.findOne(userRole);
    }
}
