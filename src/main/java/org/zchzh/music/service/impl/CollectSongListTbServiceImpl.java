package org.zchzh.music.service.impl;

import org.zchzh.music.dao.CollectSongListTbMapper;
import org.zchzh.music.dao.SongListTbMapper;
import org.zchzh.music.entity.SongListTb;
import org.zchzh.music.entity.collecttable.CollectSongListTb;
import org.zchzh.music.service.CollectSongListTbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CollectSongListTbServiceImpl implements CollectSongListTbService {

    @Autowired
    private CollectSongListTbMapper mapper;

    @Autowired
    private SongListTbMapper songListTbMapper;

    @Override
    public List<CollectSongListTb> findByUserId(Integer userId) {
        return mapper.findByUserId(userId);
    }

    @Override
    public List<CollectSongListTb> findBySongListId(Integer songListId) {
        return mapper.findBySongListId(songListId);
    }

    @Override
    public Integer insertOne(CollectSongListTb collectSongListTb) {
        SongListTb songListTb = songListTbMapper.findBySongListId(collectSongListTb.getSongListId());
        if (songListTb.getCollectNumber() == null){
            songListTb.setCollectNumber(1);
        }else{
            songListTb.setCollectNumber(songListTb.getCollectNumber()+1);
        }
        return mapper.insertOne(collectSongListTb);
    }

    @Override
    public Integer deleteOne(Integer collectSongListId) {
        return mapper.deleteOne(collectSongListId);
    }

    @Override
    public CollectSongListTb findBySongListIdAndUserId(Integer songListId, Integer userId) {
        return mapper.findBySongListIdAndUserId(songListId,userId);
    }
}
