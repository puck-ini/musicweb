package org.zchzh.music.service;

import org.zchzh.music.model.dto.UserDTO;
import org.zchzh.music.model.request.LoginReq;
import org.zchzh.music.model.request.RegisterReq;

/**
 * @author zengchzh
 * @date 2021/6/8
 */
public interface UserService {

    /**
     * 登录
     * @param loginName 登录名
     * @param password 密码
     * @return 返回登录的用户信息
     */
    UserDTO login(String loginName, String password);

    /**
     * 登出当前用户
     */
    void logout();


    /**
     * 注册
     * @param req 注册参数
     * @return 返回注册用户信息
     */
    UserDTO register(RegisterReq req);


}
