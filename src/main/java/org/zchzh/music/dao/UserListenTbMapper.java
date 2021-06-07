package org.zchzh.music.dao;

import org.zchzh.music.entity.UserListenTb;

import java.util.List;

public interface UserListenTbMapper {

    List<UserListenTb> findByUserId(Integer userId);

    UserListenTb findByUserIdAndSongId(Integer userId, Integer songId);

    int insertOne(UserListenTb userListenTb);

    int updateOne(UserListenTb userListenTb);
}
