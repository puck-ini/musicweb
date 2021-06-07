package org.zchzh.music.service.impl;

import org.zchzh.music.dao.AlbumCommentTbMapper;
import org.zchzh.music.entity.commenttable.AlbumCommentTb;
import org.zchzh.music.service.AlbumCommentTbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AlbumCommentTbServiceImpl implements AlbumCommentTbService{

    @Autowired
    private AlbumCommentTbMapper mapper;

    @Override
    public Integer insertOne(AlbumCommentTb albumCommentTb) {
        return mapper.insertOne(albumCommentTb);
    }
}
