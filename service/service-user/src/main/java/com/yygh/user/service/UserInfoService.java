package com.yygh.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yygh.model.user.UserInfo;
import com.yygh.vo.user.LoginVo;

import java.util.Map;

/**
 * Created on 2022/7/8.
 *
 * @author Ann Zhou
 */
public interface UserInfoService extends IService<UserInfo> {
    Map<String, Object> login(LoginVo loginVo);
}
