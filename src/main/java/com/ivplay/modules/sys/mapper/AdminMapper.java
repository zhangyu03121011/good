package com.ivplay.modules.sys.mapper;

import com.ivplay.common.base.mapper.BaseMapper;
import com.ivplay.modules.sys.model.Admin;

/**
 * Created by JK on 2017/1/19.
 */
public interface AdminMapper extends BaseMapper<Admin> {
    /**
     * 根据用户ID查询用户信息
     * @param id
     * @return
     */
    Admin findById(Long id);

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    Admin findByUserName(String username);
}
