package com.ivplay;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.ivplay.common.base.mapper.BaseMapper;

/**
 *
 * Created by JK on 2017/1/17.
 */
@SpringBootApplication
@EnableTransactionManagement // 开启注解事务管理，等同于xml配置文件中的 <tx:annotation-driven />
@MapperScan(basePackages = "com.ivplay.**.mapper", markerInterface = BaseMapper.class)
public class RootApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(RootApplication.class, args);
    }
}
