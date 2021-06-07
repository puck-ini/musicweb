package org.zchzh.music.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.zchzh.music.dao.MvTbMapper;
import org.zchzh.music.entity.MvTb;
import org.zchzh.music.service.MvTbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MvTbServiceImpl implements MvTbService{

    @Autowired
    private MvTbMapper mapper;

    @Override
    public PageInfo<MvTb> searchByMvName(String content, Integer pageNumber, Integer pageSize) {
        PageHelper.startPage(pageNumber,pageSize);
        return new PageInfo<>(mapper.searchByMvName(content));
    }

    @Override
    public PageInfo<MvTb> findBySingerId(Integer singerId, Integer pageNumber, Integer pageSize) {
        return new PageInfo<>(mapper.findBySingerId(singerId));
    }
}
