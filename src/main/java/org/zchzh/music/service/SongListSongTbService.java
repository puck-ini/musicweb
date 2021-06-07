package org.zchzh.music.service;

import org.zchzh.music.entity.SongListSongTb;

import java.util.List;

public interface SongListSongTbService {

    List<SongListSongTb> findBySongListId(Integer songListId);

    Integer insertOne(SongListSongTb songListSongTb);

    Integer deleteBySongListSongId(Integer songListSongId);

    List<SongListSongTb> findBySongId(Integer songId);

    Integer insertList(Integer songListId,List<Integer> list);

    SongListSongTb findBySongListIdAndSongId(Integer songListId,Integer songId);

}
