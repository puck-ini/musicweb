package org.zchzh.music.dao;

import org.zchzh.music.entity.CommentAdmireTb;

import java.util.List;

public interface CommentAdmireTbMapper {

    int insertOne(CommentAdmireTb commentAdmireTb);

    int deleteById(Integer commentAdmireId);

    List<CommentAdmireTb> findByCommentType(Integer commentType);
}
