package org.zchzh.music.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zchzh.music.model.dto.PageDTO;
import org.zchzh.music.model.entity.Comment;
import org.zchzh.music.model.request.CommentListReq;
import org.zchzh.music.service.CommentService;
import org.zchzh.music.types.CommentType;

/**
 * @author zengchzh
 * @date 2021/6/14
 */

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;


    @GetMapping("/list")
    public PageDTO<Comment> list(CommentListReq req) {
        return new PageDTO<>(commentService.list(
                Example.of(Comment.builder().targetId(req.getTargetId()).commentType(req.getType()).build()),
                PageRequest.of(req.getPageNum(), req.getPageSize())
        ));
    }

    @GetMapping("/song")
    public PageDTO<Comment> listSong(Long id, Integer pageNum, Integer pageSize) {
        return new PageDTO<>(commentService.list(
                Example.of(Comment.builder().targetId(id).commentType(CommentType.SONG).build()),
                PageRequest.of(pageNum, pageSize)
        ));
    }

    @GetMapping("/mv")
    public PageDTO<Comment> listMv(Long id, Integer pageNum, Integer pageSize) {
        return new PageDTO<>(commentService.list(
                Example.of(Comment.builder().targetId(id).commentType(CommentType.MV).build()),
                PageRequest.of(pageNum, pageSize)
        ));
    }

    @GetMapping("/album")
    public PageDTO<Comment> listAlbum(Long id, Integer pageNum, Integer pageSize) {
        return new PageDTO<>(commentService.list(
                Example.of(Comment.builder().targetId(id).commentType(CommentType.ALBUM).build()),
                PageRequest.of(pageNum, pageSize)
        ));
    }


}
