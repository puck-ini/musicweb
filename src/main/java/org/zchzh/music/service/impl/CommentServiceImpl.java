package org.zchzh.music.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.zchzh.music.model.entity.Comment;
import org.zchzh.music.repository.BaseRepo;
import org.zchzh.music.repository.CommentRepo;
import org.zchzh.music.service.CommentService;
import org.zchzh.music.types.CommentType;

/**
 * @author zengchzh
 * @date 2021/6/14
 */
@Service
public class CommentServiceImpl extends AbstractCrudService<Comment, Long> implements CommentService {

    private final CommentRepo commentRepo;

    protected CommentServiceImpl(CommentRepo commentRepo) {
        super(commentRepo);
        this.commentRepo = commentRepo;
    }
}
