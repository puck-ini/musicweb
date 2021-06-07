package org.zchzh.music.service;

import org.zchzh.music.entity.favortable.FavorSongTb;

import java.util.List;

public interface FavorSongTbService {

    List<FavorSongTb> findBySongId(Integer songId);

    List<FavorSongTb> findByUserId(Integer userId);

    Integer insertOne(FavorSongTb favorSongTb);

    Integer deleteOne(Integer userId, Integer songId);
}
