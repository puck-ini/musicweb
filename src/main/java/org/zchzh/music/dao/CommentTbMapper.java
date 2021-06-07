package org.zchzh.music.dao;

import org.zchzh.music.entity.CommentTb;

import java.util.List;

public interface CommentTbMapper {

    int insertOne(CommentTb commentTb);

    int deleteOne(Integer commentId);

    List<CommentTb> findByObjectId(Integer objectId,Integer objectType);

    List<CommentTb> findByPage(Integer objectId, Integer objectType);

    List<CommentTb> findSortAdmireNumber(Integer objectId, Integer objectType,Integer minAdmireNumber);
}
