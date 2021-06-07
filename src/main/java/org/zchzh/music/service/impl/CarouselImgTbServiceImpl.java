package org.zchzh.music.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.zchzh.music.dao.CarouselImgTbMapper;
import org.zchzh.music.entity.CarouselImgTb;
import org.zchzh.music.service.CarouselImgTbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarouselImgTbServiceImpl implements CarouselImgTbService{

    @Autowired
    private CarouselImgTbMapper mapper;


    @Override
    public PageInfo<CarouselImgTb> findAll(Integer pageNumber,Integer pageSize) {
        PageHelper.startPage(pageNumber,pageSize);
        return new PageInfo<>(mapper.findAll());
    }

    @Override
    public Integer insertOne(CarouselImgTb carouselImgTb) {
        return mapper.insertOne(carouselImgTb);
    }

    @Override
    public Integer deleteOne(Integer carouselId) {
        return mapper.deleteById(carouselId);
    }

    @Override
    public PageInfo<CarouselImgTb> searchByLinkUrl(String content, Integer pageNumber, Integer pageSize) {

        PageHelper.startPage(pageNumber,pageSize);
        return new PageInfo<>(mapper.searchByLinkUrl(content));
    }

    @Override
    public Integer updateOne(CarouselImgTb carouselImgTb) {
        return mapper.updateOne(carouselImgTb);
    }

    public CarouselImgTb getCarouselImgTbById(Integer id){
        return mapper.findById(id);
    }
}
