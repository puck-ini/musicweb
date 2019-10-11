package com.musicweb.music.controller;


import com.github.pagehelper.PageInfo;
import com.musicweb.music.VO.*;
import com.musicweb.music.entity.AlbumTb;
import com.musicweb.music.entity.SingerTb;
import com.musicweb.music.enums.CommentTypeEnum;
import com.musicweb.music.service.impl.AlbumTbServiceImpl;
import com.musicweb.music.service.impl.CommentTbServiceImpl;
import com.musicweb.music.service.impl.SingerTbServiceImpl;
import com.musicweb.music.service.impl.SongTbServiceImpl;
import com.musicweb.music.utils.DateUtil;
import com.musicweb.music.utils.ResultVOUtil;
import com.musicweb.music.utils.UploadUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class AlbumController extends BasePageController {

    @Autowired
    private AlbumTbServiceImpl albumTbService;

    @Autowired
    private SingerTbServiceImpl singerTbService;

    @Autowired
    private CommentTbServiceImpl commentTbService;

    @Autowired
    private SongTbServiceImpl songTbService;

    //首页最新专辑
    @ApiOperation(value = "获取首页最新专辑")
    @GetMapping(value = "/newAlbum")
    public ResultVO newAlbum(@RequestParam(value = "n") Integer n) {
        List<AlbumTb> albumTbList = albumTbService.findSortPlayNumberAndIssueTime();
        List<AlbumTbVO> albumTbVOList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            AlbumTbVO albumTbVO = new AlbumTbVO();
            BeanUtils.copyProperties(albumTbList.get(i), albumTbVO);
            albumTbVO.setSingerName(singerTbService.findBySingerId(albumTbList.get(i).getSingerId()).getSingerName());
            albumTbVOList.add(albumTbVO);
        }
        return ResultVOUtil.success(albumTbVOList);
    }

    //专辑页
    @ApiOperation(value = "专辑页", notes = "获取专辑页信息")
    @GetMapping(value = "/albumpage")
    public ResultVO albumPage(@RequestParam("albumId") Integer albumId) {
        AlbumTb albumTb = albumTbService.findByAlbumId(albumId);
        AlbumTbPageVO albumTbPageVO = new AlbumTbPageVO();
        BeanUtils.copyProperties(albumTb, albumTbPageVO);
        List<SongTbVO> songTbVOList = getInclude(albumId, CommentTypeEnum.ALBUM_COMMENT.getCode());
        List<SongTbVO> songTbVOListResult = new ArrayList<>();
        for (SongTbVO songTbVO : songTbVOList) {
            songTbVO.setSingerName(singerTbService.findBySingerId(songTbService.findBySongId(songTbVO.getSongId()).getSingerId()).getSingerName());
            songTbVO.setSingerId(singerTbService.findBySingerId(songTbService.findBySongId(songTbVO.getSongId()).getSingerId()).getSingerId());
            songTbVO.setAlbumName(albumTbService.findByAlbumId(songTbVO.getAlbumId()).getAlbumName());
            songTbVO.setImage(albumTbService.findByAlbumId(songTbVO.getAlbumId()).getAlbumImg());
            songTbVOListResult.add(songTbVO);
        }
        albumTbPageVO.setSingerName(singerTbService.findBySingerId(albumTb.getSingerId()).getSingerName());
        albumTbPageVO.setIssueTime(DateUtil.dateToString(albumTb.getIssueTime()));
        albumTbPageVO.setSongTotal(songTbVOList.size());
        albumTbPageVO.setAlbumLiker(getLiker(albumId, CommentTypeEnum.ALBUM_COMMENT.getCode()));
        albumTbPageVO.setInAlbum(songTbVOListResult);
        albumTbPageVO.setSimilarAlbum(getSimilar(albumTb.getCompanyName(), CommentTypeEnum.ALBUM_COMMENT.getCode()));
        albumTbPageVO.setAlbumGoodComment(handleComment(commentTbService.findSortAdmireNumber(albumId, CommentTypeEnum.ALBUM_COMMENT.getCode(), 100)));
        albumTbPageVO.setAlbumComment(getCommentPage(1, albumId, CommentTypeEnum.ALBUM_COMMENT.getCode()));
        return ResultVOUtil.success(albumTbPageVO);
    }


    //TODO 新碟上架
    @ApiOperation(value = "新碟上架模块", notes = "热门新碟")
    @GetMapping(value = "/discover/hotalbum")
    public ResultVO discoverHotAlbum() {
        //热门新碟 10
        List<AlbumTb> albumTbList = albumTbService.findSortPlayNumberAndIssueTime();
        List<AlbumTbVO> albumTbVOList = getAlbumTbVOList(albumTbList);

        return ResultVOUtil.success(albumTbVOList);
    }

    @ApiOperation(value = "新碟上架模块", notes = "全部新碟")
    @GetMapping(value = "/discover/allalbum")
    public ResultVO discoverAllAlbum() {
        //全部新碟
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, -1);
        PageInfo<AlbumTb> albumTbPageInfo = albumTbService.findSortIssueTime(calendar.getTime(), PAGEMINNUMBER, PAGEMAXSIZE);
        List<AlbumTbVO> albumTbVOList1 = getAlbumTbVOList(albumTbPageInfo.getList());

        return ResultVOUtil.success(albumTbVOList1);
    }

    //全部新碟
    @ApiOperation(value = "全部新碟页面 一个月前到现在的新专辑", notes = "根据页数获取相应的内容 一个月前到现在的新专辑")
    @GetMapping(value = "/discover/all/album")
    public ResultVO allNewAlbum(@RequestParam(value = "pagenumber") Integer pageNumber) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, -1);
        PageInfo<AlbumTb> albumTbPageInfo = albumTbService.findSortIssueTime(calendar.getTime(), pageNumber, PAGEMAXSIZE);
        List<AlbumTbVO> albumTbVOList = getAlbumTbVOList(albumTbPageInfo.getList());
        PageResultVO pageResultVO = new PageResultVO(albumTbPageInfo.getPages(), albumTbPageInfo.getTotal(), albumTbPageInfo.getPageNum(), albumTbPageInfo.getSize(), albumTbVOList);
        return ResultVOUtil.success(pageResultVO);
    }

    private List<AlbumTbVO> getAlbumTbVOList(List<AlbumTb> list) {
        List<AlbumTbVO> albumTbVOList = new ArrayList<>();
        for (AlbumTb albumTb : list) {
            AlbumTbVO albumTbVO = new AlbumTbVO();
            BeanUtils.copyProperties(albumTb, albumTbVO);
            SingerTb singerTb = singerTbService.findBySingerId(albumTb.getSingerId());
            albumTbVO.setSingerName(singerTb.getSingerName());
            albumTbVOList.add(albumTbVO);
        }
        return albumTbVOList;
    }

    @ApiOperation(value="获取专辑信息")
    @GetMapping(value = "/album")
    public ResultVO<AlbumTb> getAlbumById(@RequestParam(value = "id") Integer albumId){
        AlbumTbVO albumTbVO = albumTbService.getAlbumTbVObyAlbumId(albumId);
        return ResultVOUtil.success(albumTbVO);
    }

    @ApiOperation(value="删除专辑")
    @GetMapping(value = "/album/delete")
    public ResultVO<AlbumTb> deleteAlbumById(@RequestParam(value = "id") Integer albumId){
        albumTbService.deleteOne(albumId);
        return ResultVOUtil.success();
    }

    @ApiOperation(value="创建专辑")
    @PostMapping(value = "/album/create")
    public ResultVO<AlbumTb> createAlbum(@RequestParam(value = "albumName") String albumName,
                                         @RequestParam(value = "company") String company,
                                         @RequestParam(value = "intro") String intro,
                                         @RequestParam(value = "singerId") Integer singerId,
                                         @RequestParam(value = "file") MultipartFile image) throws IOException {
        AlbumTb albumTb = new AlbumTb();
        albumTb.setAlbumName(albumName);
        albumTb.setCompanyName(company);
        albumTb.setAlbumIntro(intro);
        albumTb.setSingerId(singerId);
        FileInputStream fileInputStream = (FileInputStream) image.getInputStream();
        String filePath = UploadUtil.commonUpload(fileInputStream,image.getOriginalFilename());
        albumTb.setAlbumImg(filePath);

        albumTbService.insertOne(albumTb);

        return ResultVOUtil.success();
    }
}
