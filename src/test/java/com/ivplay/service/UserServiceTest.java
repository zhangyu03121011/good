package com.ivplay.service;

import com.github.pagehelper.PageInfo;
import com.ivplay.BaseTest;
import com.ivplay.modules.sys.model.Admin;
import com.ivplay.modules.sys.service.AdminService;
import com.xiaoleilu.hutool.crypto.SecureUtil;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created by JK on 2017/1/20.
 */
public class UserServiceTest extends BaseTest{

    @Autowired
    private AdminService userService;

    /**
     * 保存用户信息
     */
    @Test
    public void testSaveUser() throws Exception{
        Admin record = new Admin();
        record.setUsername("小三");
        record.setPassword(SecureUtil.md5().digestHex("123"));
        record.setEmail("228727120@qq.com");
        record.setMobilePhone("15001338253");
        record.setIsDel(false);
        record.setIsLock(false);
        Boolean flag = userService.saveUserAndUserRole(record, 1L);
        Assert.assertTrue(flag);
    }

    /**
     * 根据开始时间和结束时间 分页模糊匹配某个用户
     * @throws Exception
     */
    @Test
    public void testFindPage() throws Exception {
        String startTime = "2017-01-20";
        String endTime = "2017-01-20";
        PageInfo<Admin> pageInfo = userService.findPage(1 ,10 ,"小三", startTime,endTime);
        log.info("总条数:"+pageInfo.getList().size());
        Assert.assertTrue(pageInfo.getList().size() > 0);
    }

    /**
     * 根据用户名查询用户
     * @throws Exception
     */
    @Test
    public void testFindByUserName() throws Exception{
        Admin user = userService.findByUserName("admin");
        Assert.assertNotNull(user);
    }

    /**
     * 更新用户信息和关联用户和角色
     */
    @Test
    public void testUpdateUserAndUserRole(){
        try {
            Admin user = new Admin();
            user.setId(144L);
            user.setPassword("123");
            user.setIsDel(false);
            user.setIsLock(false);
            user.setMobilePhone("8484887878787");
            user.setEmail("228@qq.com");
            user.setUsername("cuiuiuiui");
            user.setSex(false);
            user.setLoginTime(new Date());
            Long oldRoleId = 3L;
            Long roleId = 1L;
            Boolean flag = userService.updateUserAndUserRole(user, oldRoleId, roleId);
            Assert.assertTrue(flag);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
