package org.zchzh.music.controller;


import org.zchzh.music.VO.ResultVO;
import org.zchzh.music.VO.SongListTbCreateVO;
import org.zchzh.music.VO.UserTbVO;
import org.zchzh.music.entity.SongListTb;
import org.zchzh.music.entity.UserTb;
import org.zchzh.music.service.impl.SongListTbServiceImpl;
import org.zchzh.music.service.impl.UserTbServiceImpl;
import com.musicweb.music.utils.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.zchzh.music.utils.DateUtil;
import org.zchzh.music.utils.ResultVOUtil;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping(value = "/api")
public class UserController {

    @Autowired
    private UserTbServiceImpl userTbService;

    @Autowired
    private SongListTbServiceImpl songListTbService;


    @ApiOperation(value = "查询所有用户",notes = "查询所有用户信息")
    //返回所有用户
    @GetMapping(value = "/loginUser")
    public ResultVO loginUser() {
        List<UserTb> userList = userTbService.findAll();
        List<UserTbVO> userTbVOList = new ArrayList<>();

        for (UserTb userTb : userList) {
            UserTbVO userTbVO = new UserTbVO();
            BeanUtils.copyProperties(userTb, userTbVO);
            userTbVO.setBirthDate(DateUtil.dateToString(userTb.getBirthDate()));
            userTbVOList.add(userTbVO);
        }

        return ResultVOUtil.success(userTbVOList);
    }


    //用户创建的歌单
    @ApiOperation(value = "用户创建的歌单",notes = "通过用户id查找")
    @GetMapping(value = "/user/createSonglist")
    public ResultVO userCreateSongList(@RequestParam(name = "userId") Integer userId) {

        List<SongListTb> songListTbList = songListTbService.findByUserId(userId);
        List<SongListTbCreateVO> songListTbCreateVOList = new ArrayList<>();
        for (SongListTb songListTb : songListTbList) {
            SongListTbCreateVO songListTbCreateVO = new SongListTbCreateVO();
            BeanUtils.copyProperties(songListTb, songListTbCreateVO);
            UserTb userTb = userTbService.findById(userId);
            songListTbCreateVO.setUserId(userId);
            songListTbCreateVO.setUserNickname(userTb.getUserNickname());
            songListTbCreateVOList.add(songListTbCreateVO);
        }

        return ResultVOUtil.success(songListTbCreateVOList);
    }


    //更改用户权限
    @ApiOperation(value = "更改用户权限")
    @PostMapping(value = "/user/updateStatus")
    public ResultVO updateUsesrStatus(@RequestParam(name = "userId") Integer userId,
                                      @RequestParam(name = "status") Integer status) {

        UserTb userTb = userTbService.findById(userId);
        userTb.setJurisdiction(status);
        UserTb result = userTbService.updateUserTb(userTb);

        return ResultVOUtil.success(result);
    }

}
