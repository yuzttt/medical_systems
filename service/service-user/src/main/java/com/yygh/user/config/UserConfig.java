package com.yygh.user.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created on 2022/7/8.
 *
 * @author Ann Zhou
 */
@Configuration
@MapperScan("com.yygh.user.mapper.UserInfoMapper")
public class UserConfig {

}
