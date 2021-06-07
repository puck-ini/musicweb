package org.zchzh.music.service;

import com.github.pagehelper.PageInfo;
import org.zchzh.music.entity.SingerTb;

import java.util.List;

public interface SingerTbService {


    SingerTb findBySingerId(Integer singerId);

    List<SingerTb> findAll();

    List<SingerTb> findAllInSinger();

    List<SingerTb> findByType(Integer singerType);

    List<SingerTb> findByTypeAndInitial(Integer singerType,String initial);

    PageInfo<SingerTb> searchBySingerName(String content, Integer pageNumber, Integer pageSize);

    SingerTb findByUserId(Integer userId);

    PageInfo<SingerTb> findAllPage(Integer pageNumber, Integer pageSize);
}
