package org.zchzh.music.service.newservice.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.zchzh.music.entity.newentity.MusicUser;
import org.zchzh.music.exception.CommonException;
import org.zchzh.music.repository.MusicUserRepo;
import org.zchzh.music.repository.RedisRepo;
import org.zchzh.music.service.newservice.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

/**
 * @author zengchzh
 * @date 2021/6/8
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private MusicUserRepo userRepo;

    @Autowired
    private RedisRepo redisRepo;

    @Autowired
    private HttpServletRequest request;
    /**
     * 登录名限制次数
     */
    private static final int LOGIN_NAME_FAIL_COUNT = 20;

    /**
     * ip 限制次数
     */
    private static final int IP_FAIL_COUNT = 10;

    @Override
    public MusicUser login(String loginName, String password) {
        List<MusicUser> musicUserList = userRepo.findAllByLoginName(loginName);
        if (CollectionUtils.isEmpty(musicUserList)) {
            throw new CommonException("用户名或密码错误");
        }
        String ip = request.getRemoteAddr();
        checkLoginNameLimiter(loginName);
        checkIpLimiter(loginName, ip);
        MusicUser musicUser = musicUserList.get(0);
        // TODO 加密
        if (Objects.equals(password, musicUser.getPassword())) {
            cacheLimiterInfo(loginName, ip);
        }
        return musicUser;
    }

    private void checkLoginNameLimiter(String loginName) {
        int count = redisRepo.getUserLoginCount(loginName);
        if (count > LOGIN_NAME_FAIL_COUNT) {
            throw new CommonException("登录限制");
        }
    }

    private void checkIpLimiter(String loginName, String ip) {
        int count = redisRepo.getUserIpCount(loginName, ip);
        if (count > IP_FAIL_COUNT) {
            throw new CommonException("ip登录限制");
        }
    }

    private void cacheLimiterInfo(String loginName, String ip) {
        redisRepo.cacheUserLoginCount(loginName);
        redisRepo.cacheUserIpCount(loginName, ip);
    }

    @Override
    public void logout() {

    }
}
