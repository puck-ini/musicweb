package org.zchzh.music.controller;


import com.github.pagehelper.PageInfo;
import org.zchzh.music.VO.PageResultVO;
import org.zchzh.music.VO.ResultVO;
import org.zchzh.music.VO.SongTbVO;
import org.zchzh.music.entity.SongTb;
import org.zchzh.music.service.impl.AlbumTbServiceImpl;
import org.zchzh.music.service.impl.SingerTbServiceImpl;
import org.zchzh.music.service.impl.SongTbServiceImpl;
import org.zchzh.music.utils.CookieUtil;
import org.zchzh.music.utils.ResultVOUtil;
import org.zchzh.music.utils.TokenUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/singer")
public class SingerFunctionController {
    @Autowired
    private SongTbServiceImpl songTbService;

    @Autowired
    private AlbumTbServiceImpl albumTbService;

    @Autowired
    private SingerTbServiceImpl singerTbService;

    //发布歌曲1.上传歌曲2.添加入数据库
    //TODO

    //TODO 未测试 查询个人发布的歌曲
    @GetMapping("/get/song")
    public ResultVO getSingerSong(HttpServletRequest request,
                                  @RequestParam("pageNumber") Integer pageNumber,
                                  @RequestParam(value = "pageSize",defaultValue = "20") String pageSize) {
        Claims claims = TokenUtil.parseToken(CookieUtil.get(request, "Token").getValue());
        Integer userId = Integer.valueOf(claims.getId());
        PageInfo<SongTb> songTbPageInfo = songTbService.findBySingerId(singerTbService.findByUserId(userId).getSingerId(), pageNumber, Integer.valueOf(pageSize));
        List<SongTbVO> songTbVOList = new ArrayList<>();
        for (SongTb songTb:songTbPageInfo.getList()){
            SongTbVO songTbVO = new SongTbVO();
            BeanUtils.copyProperties(songTb,songTbVO);
            songTbVOList.add(songTbVO);
        }
        PageResultVO pageResultVO = new PageResultVO(songTbPageInfo.getPages(),songTbPageInfo.getTotal(),songTbPageInfo.getPageNum(),songTbPageInfo.getSize(),songTbPageInfo.getList());
        return ResultVOUtil.success(pageNumber);
    }
    //TODO 未测试通过歌名查找
    @GetMapping("/search")
    public ResultVO searchSong(@PathVariable("content") String songName,
                               @RequestParam(value = "pageNumber",defaultValue = "1") String pageNumber,
                               @RequestParam(value = "pageSize",defaultValue = "20") String pageSize){
        PageInfo<SongTb> songTbPageInfo = songTbService.searchBySongName(songName,Integer.valueOf(pageNumber),Integer.valueOf(pageSize));
        List<SongTbVO> songTbVOList = new ArrayList<>();
        for (SongTb songTb:songTbPageInfo.getList()){
            SongTbVO songTbVO = new SongTbVO();
            BeanUtils.copyProperties(songTb,songTbVO);
            songTbVOList.add(songTbVO);
        }
        PageResultVO pageResultVO = new PageResultVO(songTbPageInfo.getPages(),songTbPageInfo.getTotal(),songTbPageInfo.getPageNum(),songTbPageInfo.getSize(),songTbPageInfo.getList());
        return ResultVOUtil.success(pageResultVO);
    }
    //编辑个人发布的歌曲
    @PostMapping("/update")
    public ResultVO updateSong(SongTb songTb){
        return ResultVOUtil.success();
    }
    //发布专辑
    //编辑个人发布的专辑
}
