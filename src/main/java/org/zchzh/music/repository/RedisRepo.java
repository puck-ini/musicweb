package org.zchzh.music.repository;

/**
 * @author zengchzh
 * @date 2021/6/9
 */

public interface RedisRepo {

    /**
     * 缓存用户登录错误次数
     * @param loginName 登录名
     */
    void cacheUserLoginCount(String loginName);

    /**
     * 缓存用户登录错误时的ip限制次数
     * @param loginName 登录名
     * @param ip 登录时的ip
     */
    void cacheUserIpCount(String loginName, String ip);

    /**
     * 获取登录错误次数
     * @param loginName 登录名
     * @return 返回错误总数
     */
    int getUserLoginCount(String loginName);

    /**
     * 获取当先登录名的ip限制次数
     * @param loginName 限制次数
     * @param ip 登录时的ip
     * @return 返回ip限制总数
     */
    int getUserIpCount(String loginName, String ip);
}
