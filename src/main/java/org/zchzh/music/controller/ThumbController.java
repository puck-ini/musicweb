package org.zchzh.music.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zchzh.music.model.request.ThumbCountReq;
import org.zchzh.music.model.request.ThumbDownReq;
import org.zchzh.music.model.request.ThumbUpReq;
import org.zchzh.music.service.ThumbService;
import org.zchzh.music.types.ThumbObjectType;
import org.zchzh.music.types.UserTargetId;

import javax.validation.Valid;

/**
 * @author zengchzh
 * @date 2021/6/12
 */

@RestController
@RequestMapping("/thumb")
public class ThumbController {

    @Autowired
    private ThumbService thumbService;


    @PutMapping("/up")
    public void up(@Valid ThumbUpReq req) {
        thumbService.up(new UserTargetId(getUserId(), req.getTargetId()), req.getType());
    }

    @PutMapping("/up/song")
    public void upSong(Long id) {
        thumbService.up(new UserTargetId(getUserId(), id), ThumbObjectType.SONG);
    }

    @PutMapping("/up/mv")
    public void upMv(Long id) {
        thumbService.up(new UserTargetId(getUserId(), id), ThumbObjectType.MV);
    }

    @PutMapping("/up/album")
    public void upAlbum(Long id) {
        thumbService.up(new UserTargetId(getUserId(), id), ThumbObjectType.ALBUM);
    }

    @PutMapping("/up/comment")
    public void upComment(Long id) {
        thumbService.up(new UserTargetId(getUserId(), id), ThumbObjectType.COMMENT);
    }

    @PutMapping("/down")
    public void down(@Valid ThumbDownReq req) {
        thumbService.down(new UserTargetId(getUserId(), req.getTargetId()), req.getType());
    }

    @PutMapping("/down/song")
    public void downSong(Long id) {
        thumbService.down(new UserTargetId(getUserId(), id), ThumbObjectType.SONG);
    }

    @PutMapping("/down/mv")
    public void downMv(Long id) {
        thumbService.down(new UserTargetId(getUserId(), id), ThumbObjectType.MV);
    }

    @PutMapping("/down/album")
    public void downAlbum(Long id) {
        thumbService.down(new UserTargetId(getUserId(), id), ThumbObjectType.ALBUM);
    }

    @PutMapping("/down/comment")
    public void downComment(Long id) {
        thumbService.down(new UserTargetId(getUserId(), id), ThumbObjectType.COMMENT);
    }

    @GetMapping("/count")
    public Long countUp(@Valid ThumbCountReq req) {
        return thumbService.countUp(req.getTargetId(), req.getType());
    }

    private Long getUserId() {
        return 0L;
    }
}
