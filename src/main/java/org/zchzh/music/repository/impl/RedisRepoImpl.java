package org.zchzh.music.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.zchzh.music.exception.CommonException;
import org.zchzh.music.repository.RedisRepo;
import org.zchzh.music.utils.RedisUtil;

/**
 * @author zengchzh
 * @date 2021/6/9
 */

@Component
public class RedisRepoImpl implements RedisRepo {

    @Autowired
    private RedisUtil redisUtil;



    /**
     * 限制登录时间，单位秒
     */
    private final static Integer LIMIT_TIME = 600;

    @Override
    public void cacheUserLoginCount(String loginName) {
        int count = getUserLoginCount(loginName);
        Object o = redisUtil.getAndSetString(loginName, count + 1, LIMIT_TIME);
    }

    @Override
    public void cacheUserIpCount(String loginName, String ip) {
        int count = getUserIpCount(loginName, ip);
        String ipKey = loginName + ":" + ip;
        Object o = redisUtil.getAndSetString(ipKey, count + 1, LIMIT_TIME);
    }

    @Override
    public int getUserLoginCount(String loginName) {
        Object o = redisUtil.getString(loginName);
        return o == null ? 0 : (int) o;
    }

    @Override
    public int getUserIpCount(String loginName, String ip) {
        String ipKey = loginName + ":" + ip;
        Object o = redisUtil.getString(ipKey);
        return o == null ? 0 : (int) o;
    }
}
