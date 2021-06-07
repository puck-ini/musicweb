package org.zchzh.music.service.impl;

import org.zchzh.music.dao.SongListCommentTbMapper;
import org.zchzh.music.entity.commenttable.SongListCommentTb;
import org.zchzh.music.service.SongListCommentTbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SongListCommentTbServiceImpl implements SongListCommentTbService{

    @Autowired
    private SongListCommentTbMapper mapper;

    @Override
    public List<SongListCommentTb> findBySongListId(Integer songListId) {
        return mapper.findBySongListId(songListId);
    }

    @Override
    public List<SongListCommentTb> findByUserId(Integer userId) {
        return mapper.findByUserId(userId);
    }

    @Override
    public List<SongListCommentTb> findSortAdmireNumber() {
        return mapper.findSortAdmireNumber();
    }

    @Override
    public Integer insertOne(SongListCommentTb songListCommentTb) {
        return mapper.insertOne(songListCommentTb);
    }
}
