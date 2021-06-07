package org.zchzh.music.service.impl;

import org.zchzh.music.dao.CommentAdmireTbMapper;
import org.zchzh.music.entity.CommentAdmireTb;
import org.zchzh.music.service.CommentAdmireTbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CommentAdmireTbServiceImpl implements CommentAdmireTbService{

    @Autowired
    private CommentAdmireTbMapper mapper;

    @Override
    public Integer insertOne(CommentAdmireTb commentAdmireTb) {
        return mapper.insertOne(commentAdmireTb);
    }

    @Override
    public Integer deleteById(Integer commentAdmireId) {
        return mapper.deleteById(commentAdmireId);
    }

    @Override
    public List<CommentAdmireTb> findBycCommentType(Integer commentType) {
        return mapper.findByCommentType(commentType);
    }
}
