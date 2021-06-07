package org.zchzh.music.dao;

import org.zchzh.music.entity.favortable.FavorSongTb;

import java.util.List;

public interface FavorSongTbMapper {

    List<FavorSongTb> findBySongId(Integer songId);

    List<FavorSongTb> findByUserId(Integer userId);

    FavorSongTb findByUserIdAndSongId(Integer userId, Integer songId);

    int insertOne(FavorSongTb favorSongTb);

    int deleteOne(Integer userId, Integer songId);
}
