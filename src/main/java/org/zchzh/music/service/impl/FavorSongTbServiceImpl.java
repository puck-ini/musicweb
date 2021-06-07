package org.zchzh.music.service.impl;

import org.zchzh.music.dao.FavorSongTbMapper;
import org.zchzh.music.entity.favortable.FavorSongTb;
import org.zchzh.music.enums.ExceptionEnum;
import org.zchzh.music.exception.CommonException;
import org.zchzh.music.service.FavorSongTbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FavorSongTbServiceImpl implements FavorSongTbService{

    @Autowired
    private FavorSongTbMapper mapper;

    @Override
    public List<FavorSongTb> findBySongId(Integer songId) {
        return mapper.findBySongId(songId);
    }

    @Override
    public List<FavorSongTb> findByUserId(Integer userId) {
        return mapper.findByUserId(userId);
    }

    @Override
    public Integer insertOne(FavorSongTb favorSongTb) {
        FavorSongTb favorSongTb1 = mapper.findByUserIdAndSongId(favorSongTb.getUserId(),favorSongTb.getSongId());
        if (favorSongTb1 !=null){
            mapper.deleteOne(favorSongTb.getUserId(),favorSongTb.getSongId());
            throw new CommonException(ExceptionEnum.DATA_EXIST);
        }
        return mapper.insertOne(favorSongTb);
    }

    @Override
    public Integer deleteOne(Integer userId, Integer songId) {
        return mapper.deleteOne(userId,songId);
    }
}
