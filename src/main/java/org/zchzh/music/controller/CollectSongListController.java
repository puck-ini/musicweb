package org.zchzh.music.controller;


import org.zchzh.music.VO.ResultVO;
import org.zchzh.music.VO.SongListTbVO;
import org.zchzh.music.entity.SongListTb;
import org.zchzh.music.entity.UserTb;
import org.zchzh.music.entity.collecttable.CollectSongListTb;
import org.zchzh.music.service.impl.CollectSongListTbServiceImpl;
import org.zchzh.music.service.impl.SongListTbServiceImpl;
import org.zchzh.music.service.impl.UserTbServiceImpl;
import org.zchzh.music.utils.ResultVOUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value = "/api")
public class CollectSongListController {

    @Autowired
    private CollectSongListTbServiceImpl collectSongListTbService;

    @Autowired
    private SongListTbServiceImpl songListTbService;

    @Autowired
    private UserTbServiceImpl userTbService;

    // 用户收藏的歌单
    @ApiOperation(value = "获取用户收藏的歌单",notes = "在url中添加用户id获取")
    @GetMapping(value = "/storeSonglist")
    public ResultVO storeSongList(@RequestParam("userId") Integer userId){

        List<CollectSongListTb> collectSongListTbList = collectSongListTbService.findByUserId(userId);
        List<SongListTbVO> songListTbVOList = new ArrayList<>();
        for(CollectSongListTb collectSongListTb: collectSongListTbList){
            SongListTbVO songListTbVO = new SongListTbVO();
            UserTb userTb = userTbService.findById(songListTbService.findBySongListId(collectSongListTb.getSongListId()).getUserId());
            SongListTb songListTb = songListTbService.findBySongListId(collectSongListTb.getSongListId());
            songListTbVO.setSongListName(songListTb.getSongListName());
            songListTbVO.setSongListId(songListTb.getSongListId());
            songListTbVO.setUserId(userTb.getUserId());
            songListTbVO.setUserNickname(userTb.getUserNickname());
            songListTbVO.setLabel(songListTb.getLabel());
            songListTbVO.setSongListImg(songListTb.getSongListImg());
            songListTbVO.setPlayNumber(songListTb.getPlayNumber());
            songListTbVOList.add(songListTbVO);
        }
        return ResultVOUtil.success(songListTbVOList);
    }
}
