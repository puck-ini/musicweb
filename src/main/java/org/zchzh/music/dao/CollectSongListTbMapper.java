package org.zchzh.music.dao;

import org.zchzh.music.entity.collecttable.CollectSongListTb;

import java.util.List;

public interface CollectSongListTbMapper {

    List<CollectSongListTb> findByUserId(Integer userId);

    List<CollectSongListTb> findBySongListId(Integer songListId);

    int insertOne(CollectSongListTb collectSongListTb);

    int deleteOne(Integer collectSongListId);

    CollectSongListTb findBySongListIdAndUserId(Integer SongListId,Integer userId);
}
