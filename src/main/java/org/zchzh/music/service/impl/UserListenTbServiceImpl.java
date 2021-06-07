package org.zchzh.music.service.impl;

import org.zchzh.music.dao.UserListenTbMapper;
import org.zchzh.music.entity.UserListenTb;
import org.zchzh.music.service.UserListenTbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserListenTbServiceImpl implements UserListenTbService {

    @Autowired
    private UserListenTbMapper mapper;

    @Override
    public List<UserListenTb> findSortPlayNumber(Integer userId) {
        return mapper.findByUserId(userId);
    }

    @Override
    public UserListenTb findByUserIdAndSongId(Integer userId, Integer songId) {
        return mapper.findByUserIdAndSongId(userId,songId);
    }

    @Override
    public Integer insertOne(UserListenTb userListenTb) {
        userListenTb.setPlayNumber(1);
        return mapper.insertOne(userListenTb);
    }

    @Override
    public Integer updateOne(UserListenTb userListenTb) {
        return mapper.updateOne(userListenTb);
    }

    @Override
    public List<UserListenTb> findByUserId(Integer userId) {
        return mapper.findByUserId(userId);
    }
}
