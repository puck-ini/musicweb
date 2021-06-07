package org.zchzh.music.service;

import com.github.pagehelper.PageInfo;
import org.zchzh.music.entity.MvTb;

public interface MvTbService {

    PageInfo<MvTb> searchByMvName(String content, Integer pageNumber, Integer pageSize);

    PageInfo<MvTb> findBySingerId(Integer singerId, Integer pageNumber, Integer pageSize);
}
