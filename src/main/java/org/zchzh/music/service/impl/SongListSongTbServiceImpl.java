package org.zchzh.music.service.impl;

import org.zchzh.music.dao.SongListSongTbMapper;
import org.zchzh.music.entity.SongListSongTb;
import org.zchzh.music.service.SongListSongTbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class SongListSongTbServiceImpl implements SongListSongTbService{

    @Autowired
    private SongListSongTbMapper mapper;

    @Override
    public List<SongListSongTb> findBySongListId(Integer songListId) {
        return mapper.findBySongListId(songListId);
    }

    @Override
    public Integer insertOne(SongListSongTb songListSongTb) {
        return mapper.insertOne(songListSongTb);
    }

    @Override
    public Integer deleteBySongListSongId(Integer songListSongId) {
        return mapper.deleteBySongListSongId(songListSongId);
    }

    @Override
    public List<SongListSongTb> findBySongId(Integer songId) {
        return mapper.findBySongId(songId);
    }

    @Override
    @Transactional
    public Integer insertList(Integer songListId,List<Integer> list) {
        List<SongListSongTb> songListSongTbList = new ArrayList<>();
        for (Integer integer: list){
            SongListSongTb songListSongTb = new SongListSongTb();
            songListSongTb.setSongListId(songListId);
            songListSongTb.setSongId(integer);
            songListSongTbList.add(songListSongTb);
        }

        return mapper.insertList(songListSongTbList);
    }

    @Override
    public SongListSongTb findBySongListIdAndSongId(Integer songListId, Integer songId) {
        return mapper.findBySongListIdAndSongId(songListId,songId);
    }
}
