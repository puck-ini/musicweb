package org.zchzh.music.service.impl;

import org.zchzh.music.dao.SongCommentTbMapper;
import org.zchzh.music.entity.commenttable.SongCommentTb;
import org.zchzh.music.service.SongCommentTbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SongCommentTbServiceImpl implements SongCommentTbService{

    @Autowired
    private SongCommentTbMapper mapper;

    @Override
    public Integer insertOne(SongCommentTb songCommentTb) {
        return mapper.insertOne(songCommentTb);
    }
}
