package org.zchzh.music.service.impl;

import org.zchzh.music.dao.CollectAlbumTbMapper;
import org.zchzh.music.entity.collecttable.CollectAlbumTb;
import org.zchzh.music.service.CollectAlbumTbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CollectAlbumTbServiceImpl implements CollectAlbumTbService {

    @Autowired
    private CollectAlbumTbMapper mapper;


    @Override
    public List<CollectAlbumTb> findByUserId(Integer userId) {
        return mapper.findByUserId(userId);
    }

    @Override
    public List<CollectAlbumTb> findByAlbumId(Integer albumId) {
        return mapper.findByAlbumId(albumId);
    }

    @Override
    public CollectAlbumTb findByUserIdAndAlbumId(Integer userId, Integer albumId) {
        return mapper.findByUserIdAndAlbumId(userId,albumId);
    }

    @Override
    public Integer insertOne(CollectAlbumTb collectAlbumTb) {

        return mapper.insertOne(collectAlbumTb);
    }

    @Override
    public Integer deleteOne(Integer userId, Integer albumId) {
        return mapper.deleteOne(userId,albumId);
    }
}
