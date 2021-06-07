package org.zchzh.music.service;

import org.zchzh.music.entity.collecttable.CollectSongListTb;

import java.util.List;

public interface CollectSongListTbService {

    List<CollectSongListTb> findByUserId(Integer userId);

    List<CollectSongListTb> findBySongListId(Integer songListId);

    Integer insertOne(CollectSongListTb collectSongListTb);

    Integer deleteOne(Integer collectSongListId);

    CollectSongListTb findBySongListIdAndUserId(Integer songListId,Integer userId);
}