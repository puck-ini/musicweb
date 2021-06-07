package org.zchzh.music.service;

import com.github.pagehelper.PageInfo;
import org.zchzh.music.entity.CarouselImgTb;

public interface CarouselImgTbService {

    PageInfo<CarouselImgTb> findAll(Integer pageNumber, Integer pageSize);

    Integer insertOne(CarouselImgTb carouselImgTb);

    Integer deleteOne(Integer carouselId);

    PageInfo<CarouselImgTb> searchByLinkUrl(String content,Integer pageNumber, Integer pageSize);

    Integer updateOne(CarouselImgTb carouselImgTb);
}
