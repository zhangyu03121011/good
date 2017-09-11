package com.ivplay.modules.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ivplay.common.base.service.impl.BaseServiceImpl;
import com.ivplay.modules.sys.mapper.AdminMapper;
import com.ivplay.modules.sys.mapper.AdminRoleMapper;
import com.ivplay.modules.sys.mapper.RoleMapper;
import com.ivplay.modules.sys.model.Admin;
import com.ivplay.modules.sys.model.AdminRole;
import com.ivplay.modules.sys.model.Role;
import com.ivplay.modules.sys.service.AdminService;
import com.xiaoleilu.hutool.crypto.SecureUtil;
import com.xiaoleilu.hutool.date.DateUtil;
import com.xiaoleilu.hutool.util.StrUtil;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.util.List;

/**
 *
 * Created by JK on 2017/1/19.
 */
@Transactional
@Service
public class UserServiceImpl extends BaseServiceImpl<Admin> implements AdminService{

    @Autowired
    private AdminMapper userMapper;
    @Autowired
    private AdminRoleMapper userRoleMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Transactional(readOnly=true)
    @Override
    public PageInfo<Admin> findPage(Integer pageNum ,Integer pageSize ,String username, String startTime, String endTime) throws Exception {
        Example example = new Example(Admin.class);
        Criteria criteria = example.createCriteria();
        if(StringUtils.isNotEmpty(username)){
            criteria.andLike("username", "%"+username+"%");
        }if(StrUtil.isNotEmpty(startTime) && StrUtil.isNotEmpty(endTime)){
            criteria.andBetween("createTime", DateUtil.beginOfDay(DateUtil.parse(startTime)), DateUtil.endOfDay(DateUtil.parse(endTime)));
        }
        PageHelper.startPage(pageNum,pageSize);
        List<Admin> userList = this.selectByExample(example);

        for (Admin user : userList) {
            Role role = roleMapper.findByUserId(user.getId());
            if (null != role){
                user.setRoleName(role.getName());
            }
        }
        return new PageInfo<Admin>(userList);
    }

    @Transactional(readOnly=true)
    @Override
    public Admin findByUserName(String username) {
        Admin user = new Admin();
        user.setUsername(username);
        return this.findOne(user);
    }

    @Override
    public Boolean saveUserAndUserRole(Admin user, Long roleId) throws Exception{
        int count = 0;
        //加密
        user.setPassword(SecureUtil.md5().digestHex(user.getPassword()));
        user.setIsLock(false);
        user.setIsDel(false);
        Role role = roleMapper.selectByPrimaryKey(roleId);
        if(Role.ROLE_TYPE.equalsIgnoreCase(role.getPerms())){
            user.setIsAdmin(true);
        }else {
            user.setIsAdmin(false);
        }
        count = this.save(user);

        //关联用户和角色信息
        AdminRole userRole = new AdminRole();
        userRole.setRoleId(roleId);
        userRole.setUserId(user.getId());
        userRole.setCreateTime(user.getCreateTime());
        userRole.setModifyTime(user.getCreateTime());
        count = userRoleMapper.insert(userRole);

        return count == 1;
    }

    @Override
    public Boolean updateUserAndUserRole(Admin user, Long oldRoleId, Long roleId) throws Exception {
        int count = 0;
        //加密
        user.setPassword(SecureUtil.md5().digestHex(user.getPassword()));
        if(!oldRoleId.equals(roleId)){
            Role role = roleMapper.selectByPrimaryKey(roleId);
            if(Role.ROLE_TYPE.equalsIgnoreCase(role.getPerms())){
                user.setIsAdmin(true);
            }else {
                user.setIsAdmin(false);
            }
        }
        count = this.updateSelective(user);

        //更新用户角色信息
        if(!oldRoleId.equals(roleId)){
            AdminRole userRole = new AdminRole();
            userRole.setRoleId(oldRoleId);
            userRole.setUserId(user.getId());
            AdminRole ur = userRoleMapper.selectOne(userRole);
            ur.setRoleId(roleId);
            ur.setModifyTime(user.getModifyTime());
            count = userRoleMapper.updateByPrimaryKeySelective(ur);
        }
        return count == 1;
    }
}
