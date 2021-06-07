package org.zchzh.music.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.zchzh.music.dao.SongListTbMapper;
import org.zchzh.music.entity.SongListSongTb;
import org.zchzh.music.entity.SongListTb;
import org.zchzh.music.enums.ExceptionEnum;
import org.zchzh.music.exception.CommonException;
import org.zchzh.music.service.SongListSongTbService;
import org.zchzh.music.service.SongListTbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongListTbServiceImpl implements SongListTbService{

    @Autowired
    private SongListTbMapper mapper;

    @Autowired
    private SongListSongTbService songListSongTbService;

    @Override
    public List<SongListTb> findAllSortCommentNumber() {
        return mapper.findSortCommentNumber();
    }

    @Override
    public List<SongListTb> findAllSortPlayNumber() {
        return mapper.findSortPlayNumber();
    }

    @Override
    public List<SongListTb> findAllSortShareNumber() {
        return mapper.findSortShareNumber();
    }

    @Override
    public List<SongListTb> findAllSortCollectNumber() {
        return mapper.findSortCollectNumber();
    }

    @Override
    public List<SongListTb> findByLabel(String label) {
        return mapper.findByLabel(label);
    }

    @Override
    public List<SongListTb> findByUserId(Integer userId) {
        return mapper.findByUserId(userId);
    }

    @Override
    public SongListTb findBySongListId(Integer songListId) {
        return mapper.findBySongListId(songListId);
    }

    @Override
    public Integer insertOne(SongListTb songListTb) {
        return mapper.insertOne(songListTb);
    }

    @Override
    public Integer updateOne(SongListTb songListTb) {
        return mapper.updateOne(songListTb);
    }

    @Override
    public Integer deleteOne(Integer songListId) {
        List<SongListSongTb> songListSongTbs = songListSongTbService.findBySongListId(songListId);
        for (SongListSongTb songListSongTb : songListSongTbs) {
            songListSongTbService.deleteBySongListSongId(songListSongTb.getSongListSongId());
        }
        return mapper.deleteBySongListId(songListId);
    }

    @Override
    public PageInfo<SongListTb> searchBySongListName(String content, Integer pageNumber, Integer pageSize) {

        PageHelper.startPage(pageNumber,pageSize);
        return new PageInfo<SongListTb>(mapper.searchBySongListName(content));
    }

    @Override
    public PageInfo<SongListTb> searchByLabel(String content, Integer pageNumber, Integer pageSize,String order) {
        PageHelper.startPage(pageNumber,pageSize);
        if (order.equals("hot")){
            PageInfo<SongListTb> songListTbPageInfo = new PageInfo<>(mapper.findByLabel(content));
            return songListTbPageInfo;
        }
        if (order.equals("new")){
            PageInfo<SongListTb> songListTbPageInfo = new PageInfo<>(mapper.findByLabelSort(content));
            return songListTbPageInfo;
        }else{
            throw new CommonException(ExceptionEnum.UNKNOWN_TYPE);
        }
    }

    @Override
    public PageInfo<SongListTb> searchByLabel(Integer pageNumber, Integer pageSize,String order) {
        PageHelper.startPage(pageNumber,pageSize);
        if (order.equals("hot")){
            PageInfo<SongListTb> songListTbPageInfo = new PageInfo<>(mapper.findSortPlayNumber());
            return songListTbPageInfo;
        }
        if (order.equals("new")){
            PageInfo<SongListTb> songListTbPageInfo = new PageInfo<>(mapper.findSortCreateTime());
            return songListTbPageInfo;
        }else{
            throw new CommonException(ExceptionEnum.UNKNOWN_TYPE);
        }
    }

    @Override
    public PageInfo<SongListTb> findAll(Integer pageNumber, Integer pageSize) {
        PageHelper.startPage(pageNumber,pageSize);
        return new PageInfo<>(mapper.findAll());
    }

    @Override
    public List<SongListTb> findBySongListNameAndUserId(String songListName, Integer userId) {
        return mapper.findBySongListNameAndUserId(songListName,userId);
    }
}
