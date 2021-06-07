package org.zchzh.music.controller;


import org.zchzh.music.VO.ResultVO;
import org.zchzh.music.service.impl.CommentTbServiceImpl;
import org.zchzh.music.utils.ResultVOUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api")
public class CommentController extends BasePageController{

    @Autowired
    private CommentTbServiceImpl commentTbService;


    @ApiOperation(value = "获取评论",notes = "输入页数、id、评论类型（0：歌曲，1：歌单，2：专辑）")
    @PostMapping(value = "/commentpages")
    public ResultVO comment(@RequestParam(value = "pageNumber") Integer pageNumber,
                                    @RequestParam("objectId") Integer objectId,
                                    @RequestParam("objectType") Integer objectType){
        return ResultVOUtil.success(getCommentPage(pageNumber,objectId,objectType));
    }
}
