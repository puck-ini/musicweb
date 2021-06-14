package org.zchzh.music.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.zchzh.music.model.entity.Comment;
import org.zchzh.music.types.CommentType;

import java.util.List;

/**
 * @author zengchzh
 * @date 2021/6/14
 */
public interface CommentService extends BaseCrudService<Comment, Long> {
}
