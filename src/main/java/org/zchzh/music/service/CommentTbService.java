package org.zchzh.music.service;

import com.github.pagehelper.PageInfo;
import org.zchzh.music.entity.CommentTb;

import java.util.List;

public interface CommentTbService {

    Integer insertOne(CommentTb commentTb);

    Integer deleteOne(Integer commentId);

    List<CommentTb> findByObjectId(Integer objectId, Integer objectType);

    PageInfo<CommentTb> findByPage(Integer objectId, Integer objectType, Integer pageNumber, Integer pageSize);

    List<CommentTb> findSortAdmireNumber(Integer objectId, Integer objectType,Integer minAdmireNumber);

}
