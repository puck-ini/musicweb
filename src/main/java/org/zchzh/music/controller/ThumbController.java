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

    @PutMapping("/down")
    public void down(@Valid ThumbDownReq req) {
        thumbService.down(new UserTargetId(getUserId(), req.getTargetId()), req.getType());
    }

    @GetMapping("/count")
    public Long countUp(@Valid ThumbCountReq req) {
        return thumbService.countUp(req.getTargetId(), req.getType());
    }

    private Long getUserId() {
        return 0L;
    }
}
