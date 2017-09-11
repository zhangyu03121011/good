package com.ivplay.modules.sys.service;

import com.github.pagehelper.PageInfo;
import com.ivplay.common.base.service.BaseService;
import com.ivplay.modules.sys.model.Admin;

/**
 * Created by JK on 2017/1/19.
 */
public interface AdminService extends BaseService<Admin> {
    /**
     *
     * @param pageNum  当前页码
     * @param pageSize  每页显示条数
     * @param username 用户名
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return
     * @throws Exception
     */
    PageInfo<Admin> findPage(Integer pageNum ,Integer pageSize ,String username, String startTime, String endTime) throws Exception;

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    Admin findByUserName(String username) throws Exception;

    /**
     * 保存用户信息和关联用户和角色
     * @param user    用户对象
     * @param roleId  角色ID
     */
    Boolean saveUserAndUserRole(Admin user, Long roleId) throws Exception;

    /**
     * 更新用户信息和关联用户和角色
     * @param user      用户对象
     * @param oldRoleId 旧角色ID
     * @param roleId    角色ID
     * @return
     * @throws Exception
     */
    Boolean updateUserAndUserRole(Admin user, Long oldRoleId, Long roleId) throws Exception;
}
