package org.zchzh.music.service;

import org.zchzh.music.entity.CommentAdmireTb;

import java.util.List;

public interface CommentAdmireTbService {

    Integer insertOne(CommentAdmireTb commentAdmireTb);

    Integer deleteById(Integer commentAdmireId);

    List<CommentAdmireTb> findBycCommentType(Integer commentType);
}
