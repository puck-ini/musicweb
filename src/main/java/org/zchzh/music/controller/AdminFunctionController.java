package org.zchzh.music.controller;


import com.github.pagehelper.PageInfo;
import org.zchzh.music.VO.*;
import org.zchzh.music.VO.SearchVO.AlbumSongVO;
import org.zchzh.music.VO.adminVO.SingerSongVO;
import org.zchzh.music.VO.adminVO.SongListSongVO;
import org.zchzh.music.VO.adminVO.UserSongListVO;
import org.zchzh.music.entity.*;
import org.zchzh.music.enums.CommentTypeEnum;
import org.zchzh.music.enums.ExceptionEnum;
import org.zchzh.music.enums.UserJurisdictionEnum;
import org.zchzh.music.exception.CommonException;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.zchzh.music.service.impl.*;
import org.zchzh.music.utils.*;

import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Api(value = "管理员功能")
@RestController
@RequestMapping(value = "/api/admin")
public class AdminFunctionController extends BasePageController {

    @Autowired
    private UserTbServiceImpl userTbService;

    @Autowired
    private CarouselImgTbServiceImpl carouselImgTbService;

    @Autowired
    private SongListTbServiceImpl songListTbService;

    @Autowired
    private SongListSongTbServiceImpl songListSongTbService;

    @Autowired
    private SongTbServiceImpl songTbService;

    @Autowired
    private AlbumTbServiceImpl albumTbService;

    @Autowired
    private SingerTbServiceImpl singerTbService;


    private final String CAROUSELIMGNAME = "carousel_";

    //用户管理
    //显示所有用户
    @ApiOperation(value = "获取所有用户信息")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "type", value = "用户类型user或singer", required = true, dataType = "String"),
//            @ApiImplicitParam(name = "pageNumber", value = "页数", required = false, dataType = "Integer"),
//            @ApiImplicitParam(name = "pageSize", value = "内容数", dataType = "Integer")
//
//    })
    @GetMapping(value = "/all")
    public ResultVO allUser(@RequestParam(value = "type") String userType,
                            @RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber,
                            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {
        PageInfo<UserTb> userTbPageInfo;
        if (userType.equals("user")) {
            userTbPageInfo = userTbService.findAllUser(pageNumber, pageSize);
        } else if (userType.equals("singer")) {
            userTbPageInfo = userTbService.findAllSinger(pageNumber, pageSize);
        } else {
            throw new CommonException(ExceptionEnum.UNKNOWN_TYPE);
        }
        PageResultVO pageResultVO = new PageResultVO(userTbPageInfo.getPages(), userTbPageInfo.getTotal(), userTbPageInfo.getPageNum(), userTbPageInfo.getSize(), getUserVO(userTbPageInfo.getList()));
        return ResultVOUtil.success(pageResultVO);
    }

    private List<UserTbVO> getUserVO(List<UserTb> userTbList) {
        List<UserTbVO> userTbVOList = new ArrayList<>();
        for (UserTb userTb : userTbList) {
            UserTbVO userTbVO = new UserTbVO();
            BeanUtils.copyProperties(userTb, userTbVO);
            userTbVO.setCreateTime(DateUtil.dateToString(userTb.getCreateTime()));
            userTbVO.setUpdateTime(DateUtil.dateToString(userTb.getUpdateTime()));
            String[] roles = {UserJurisdictionEnum.getSting(userTb.getJurisdiction())};
            userTbVO.setRoles(roles);
            userTbVOList.add(userTbVO);
        }
        return userTbVOList;
    }

    //创建用户
    @ApiOperation(value = "创建用户")
    @PostMapping("/insert/user")
    public ResultVO adminInsertUser(@RequestParam("username") String username,
                                    @RequestParam("password") String password,
                                    @RequestParam("userNickname") String userNickname,
                                    @RequestParam("jurisdiction") Integer jurisdiction,
                                    @RequestParam(value = "file", required = false) MultipartFile multipartFile) throws IOException {
        FileInputStream fileInputStream = (FileInputStream) multipartFile.getInputStream();
        UserTb userTb = new UserTb();
        userTb.setUsername(username);
        userTb.setPassword(MD5Util.getMD5(password));
        userTb.setUserNickname(userNickname);
        userTb.setJurisdiction(jurisdiction);
        userTb.setHeadImg(UploadUtil.commonUpload(fileInputStream, multipartFile.getOriginalFilename()));
        userTbService.insertForSignIn(userTb);

        return ResultVOUtil.success();
    }

    //删除用户
    @ApiOperation(value = "删除用户", notes = "在url中输入用户id删除用户")
    @GetMapping(value = "/delete")
    public ResultVO adminDelete(@RequestParam(value = "userId") Integer userId) {
        if (userTbService.deleteById(userId) != 1) {
            throw new CommonException(ExceptionEnum.DELETE_ERROR);
        }
        return ResultVOUtil.success();
    }

    //编辑用户信息
    @ApiOperation(value = "编辑用户信息", notes = "编辑用户信息")
    @PostMapping(value = "/alter/nickname")
    public ResultVO alterJurisdiction(@RequestParam(value = "userId") Integer userId,
                                      @RequestParam(value = "userNickname") String userNickname,
                                      @RequestParam(value = "jurisdiction", required = false) Integer jurisdiction) {
        UserTb userTb = userTbService.findById(userId);

        userTb.setUserNickname(userNickname);
        if (jurisdiction != null) {
            userTb.setJurisdiction(jurisdiction);
        }

        userTbService.compilePersonalData(userTb);

        return ResultVOUtil.success(userTb);
    }

    //查询用户
    @ApiOperation(value = "查询用户")
    @GetMapping(value = "/search/user")
    public ResultVO searchUser(@RequestParam("content") String content,
                               @RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber,
                               @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {
        PageInfo<UserTb> userTbPageInfo = userTbService.searchByUserNickname(content, pageNumber, pageSize);

        PageResultVO pageResultVO = new PageResultVO(userTbPageInfo.getPages(), userTbPageInfo.getTotal(), userTbPageInfo.getPageNum(), userTbPageInfo.getSize(), userTbPageInfo.getList());

        return ResultVOUtil.success(pageResultVO);

    }






    //删除轮播图
    @ApiOperation(value = "删除轮播图")
    @PostMapping("/delete/carousel")
    public ResultVO deleteCarousel(Integer carouselId) {
        Integer result = carouselImgTbService.deleteOne(carouselId);
        if (result != 1) {
            throw new CommonException(ExceptionEnum.DELETE_ERROR);
        }
        return ResultVOUtil.success();
    }

    //查找轮播图
    @ApiOperation(value = "查找轮播图")
    @GetMapping("/search/carousel")
    public ResultVO searchCarousel(@RequestParam("content") String content,
                                   @RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber,
                                   @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {
        PageInfo<CarouselImgTb> carouselImgTbPageInfo = carouselImgTbService.searchByLinkUrl(content, pageNumber, pageSize);
        PageResultVO pageResultVO = new PageResultVO(carouselImgTbPageInfo.getPages(), carouselImgTbPageInfo.getTotal(), carouselImgTbPageInfo.getPageNum(), carouselImgTbPageInfo.getSize(), carouselImgTbPageInfo.getList());

        return ResultVOUtil.success(pageResultVO);

    }

    //认证歌手
    @ApiOperation(value = "认证歌手", notes = "修改用户权限")
    @PostMapping("/identify/singer")
    public ResultVO identifySinger(@RequestParam("userId") Integer userId) {
        UserTb userTb = userTbService.findById(userId);
        userTb.setJurisdiction(UserJurisdictionEnum.SINGER.getCode());
        userTbService.compilePersonalData(userTb);
        return ResultVOUtil.success();
    }




    //更新专辑test
    @ApiOperation(value = "更新专辑")
    @PostMapping("/update/album")
    public ResultVO adminUpdateAlbum(@RequestParam("albumId") Integer albumId,
                                     @RequestParam(value = "file",required = false) MultipartFile multipartFile,
                                     @RequestParam(value = "albumName",required = false) String albumName) throws IOException {
        AlbumTb albumTb = albumTbService.findByAlbumId(albumId);
        if(multipartFile!=null){
            FileInputStream fileInputStream = (FileInputStream) multipartFile.getInputStream();
            albumTb.setAlbumImg(UploadUtil.commonUpload(fileInputStream,multipartFile.getOriginalFilename()));
        }
        if(albumName!=null){
            albumTb.setAlbumName(albumName);
        }
        albumTbService.updateOne(albumTb);

        albumTb = albumTbService.findByAlbumId(albumId);
        AlbumTbVO albumTbVO = new AlbumTbVO();
        BeanUtils.copyProperties(albumTb,albumTbVO);
        return ResultVOUtil.success(albumTbVO);
    }

    //专辑添加歌曲
    @ApiOperation(value = "专辑添加歌曲")
    @PostMapping("/insert/album/song")
    public ResultVO adminInsertAlbumSong(@RequestParam("albumId") Integer albumId,
                                         @RequestParam("songId") Integer songId){
        SongTb songTb = songTbService.findBySongId(songId);
        songTb.setAlbumId(albumId);
        songTbService.updateOne(songTb);
        return ResultVOUtil.success();
    }
    //专辑删除歌曲
    @ApiOperation(value = "专辑删除歌曲")
    @PostMapping("/delete/album/song")
    public ResultVO adminDeleteAlbumSong(@RequestParam("albumId") Integer albumId,
                                         @RequestParam("songId") Integer songId){
        //TODO 删除歌曲
        songTbService.deleteOne(songId);
        return ResultVOUtil.success();
    }


    //批量添加
    @ApiOperation(value = "批量添加歌曲到歌单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "songListId", value = "歌单id", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "idString", value = "歌曲id字符串", required = true, dataType = "String")
    })
    @PostMapping("/inserts")
    public ResultVO inserts(@RequestParam("songListId") Integer songListId,
                            @RequestParam("idString") String idString) {
        String[] idArray = idString.split(",");
        List<Integer> idList = new ArrayList<>();
        for (String s : idArray) {
            idList.add(Integer.valueOf(s));
        }
        Integer result = songListSongTbService.insertList(songListId, idList);
        return ResultVOUtil.success();
    }



    //返回歌手所有歌曲
    @ApiOperation(value = "获取歌手所有的歌曲")
    @GetMapping("/get/singer/song")
    public ResultVO adminGetSingerSong(@RequestParam("singerId") Integer singerId){
        PageInfo<SongTb> songTbPageInfo = songTbService.findBySingerId(singerId,1,20);
        List<SongTbVO> songTbVOList = new ArrayList<>();
        for (SongTb songTb: songTbPageInfo.getList()){
            SongTbVO songTbVO = new SongTbVO();
            BeanUtils.copyProperties(songTb,songTbVO);
            songTbVOList.add(songTbVO);
        }
        return ResultVOUtil.success(songTbVOList);
    }

    //查询所有
    //歌单
    @ApiOperation(value = "后台查询所有歌曲 歌单 专辑")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "content", value = "song  songList  album", required = true, dataType = "String"),
//            @ApiImplicitParam(name = "singerId", value = "歌手id", dataType = "Integer"),
//            @ApiImplicitParam(name = "pageNumber", value = "页数", required = true, dataType = "Integer"),
//            @ApiImplicitParam(name = "pageSize", value = "内容数", dataType = "Integer")
//
//    })
    @GetMapping("/get/all")
    public ResultVO getAllSongList(@RequestParam(value = "title", required = false) String title,
                                   @RequestParam("content") String content,
                                   @RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber,
                                   @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
                                   HttpServletRequest request) {
        PageResultVO pageResultVO = null;
        Claims claims = TokenUtil.parseToken(CookieUtil.get(request,"Token").getValue());
        Integer userId = Integer.valueOf(claims.getId());
        Integer singerId = null;
        SingerTb singerTb = singerTbService.findByUserId(userId);
        if (singerTb != null) {
            singerId=singerTb.getSingerId();
        }
        switch (content) {
            case "song":
                if (title != null) {
                    PageInfo<SongTb> songTbPageInfo = songTbService.searchBySongName(title, pageNumber, pageSize);
                    pageResultVO = new PageResultVO(songTbPageInfo.getPages(), songTbPageInfo.getTotal(), songTbPageInfo.getPageNum(), songTbPageInfo.getSize(), getSingerSongVO(songTbPageInfo.getList()));
                } else {
                    if (singerId != null) {
                        PageInfo<SongTb> songTbPageInfo = songTbService.findBySingerId(singerId, pageNumber, pageSize);
                        pageResultVO = new PageResultVO(songTbPageInfo.getPages(), songTbPageInfo.getTotal(), songTbPageInfo.getPageNum(), songTbPageInfo.getSize(), getSingerSongVO(songTbPageInfo.getList()));
                    } else {
                        PageInfo<SongTb> songTbPageInfo = songTbService.findAll(pageNumber, pageSize);
                        pageResultVO = new PageResultVO(songTbPageInfo.getPages(), songTbPageInfo.getTotal(), songTbPageInfo.getPageNum(), songTbPageInfo.getSize(), getSingerSongVO(songTbPageInfo.getList()));
                    }
                }
                break;
            case "songList":
                if (title != null) {
                    PageInfo<SongListTb> songListTbPageInfo = songListTbService.searchBySongListName(title, pageNumber, pageSize);
                    pageResultVO = new PageResultVO(songListTbPageInfo.getPages(), songListTbPageInfo.getTotal(), songListTbPageInfo.getPageNum(), songListTbPageInfo.getSize(), getUserSongList(songListTbPageInfo.getList()));
                } else {
                    PageInfo<SongListTb> songListTbPageInfo = songListTbService.findAll(pageNumber, pageSize);
                    pageResultVO = new PageResultVO(songListTbPageInfo.getPages(), songListTbPageInfo.getTotal(), songListTbPageInfo.getPageNum(), songListTbPageInfo.getSize(), getUserSongList(songListTbPageInfo.getList()));
                }

                break;
            case "album":
                if (title != null) {
                    PageInfo<AlbumTb> albumTbPageInfo = albumTbService.searchByAlbumName(title, pageNumber, pageSize);
                    pageResultVO = new PageResultVO(albumTbPageInfo.getPages(), albumTbPageInfo.getTotal(), albumTbPageInfo.getPageNum(), albumTbPageInfo.getSize(), getSingerAlbum(albumTbPageInfo.getList()));
                } else {
                    if (singerId != null) {
                        PageInfo<AlbumTb> albumTbPageInfo = albumTbService.findBySingerIdSortIssueTime(singerId, pageNumber, pageSize);
                        pageResultVO = new PageResultVO(albumTbPageInfo.getPages(), albumTbPageInfo.getTotal(), albumTbPageInfo.getPageNum(), albumTbPageInfo.getSize(), getSingerAlbum(albumTbPageInfo.getList()));
                    } else {
                        PageInfo<AlbumTb> albumTbPageInfo = albumTbService.findAll(pageNumber, pageSize);
                        pageResultVO = new PageResultVO(albumTbPageInfo.getPages(), albumTbPageInfo.getTotal(), albumTbPageInfo.getPageNum(), albumTbPageInfo.getSize(), getSingerAlbum(albumTbPageInfo.getList()));
                    }
                }
                break;
            default:
                throw new CommonException(ExceptionEnum.UNKNOWN_TYPE);
        }
        return ResultVOUtil.success(pageResultVO);
    }

    private List<SingerSongVO> getSingerSongVO(List<SongTb> songTbList) {
        List<SingerSongVO> singerSongVOList = new ArrayList<>();
        for (SongTb songTb : songTbList) {
            SongTbVO songTbVO = new SongTbVO();
            BeanUtils.copyProperties(songTb,songTbVO);
            songTbVO.setImage(albumTbService.findByAlbumId(songTb.getAlbumId()).getAlbumImg());
            songTbVO.setLabels(songTb.getSingStyle().split(","));
            SingerTbVO singerTbVO = new SingerTbVO();
            SingerTb singerTb = singerTbService.findBySingerId(songTb.getSingerId());
            BeanUtils.copyProperties(singerTb,singerTbVO);
            SingerSongVO singerSongVO = new SingerSongVO();
            singerSongVO.setSongTbVO(songTbVO);
            singerSongVO.setSingerTbVO(singerTbVO);
            singerSongVOList.add(singerSongVO);
        }
        return singerSongVOList;
    }

    private List<UserSongListVO> getUserSongList(List<SongListTb> songListTbList) {
        List<UserSongListVO> userSongListVOList =new ArrayList<>();
        for (SongListTb songListTb: songListTbList) {
            UserSongListVO userSongListVO = new UserSongListVO();
            SongListSongVO songListSongVO = new SongListSongVO();
            UserTbVO userTbVO = new UserTbVO();
            UserTb userTb = userTbService.findById(songListTb.getUserId());
            BeanUtils.copyProperties(userTb,userTbVO);
            List<SongTbVO> songTbVOList = getInclude(songListTb.getSongListId(),CommentTypeEnum.SONG_LIST_COMMENT.getCode());
            BeanUtils.copyProperties(songListTb,songListSongVO);
            songListSongVO.setCreateTime(DateUtil.dateToString(songListTb.getCreateTime()));
            songListSongVO.setUpdateTime(DateUtil.dateToString(songListTb.getUpdateTime()));
            songListSongVO.setSongCount(songTbVOList.size());
            songListSongVO.setSongTbVOList(songTbVOList);
            songListSongVO.setLabel(songListTb.getLabel().split(","));
            userSongListVO.setSongListSongVO(songListSongVO);
            userSongListVO.setUserTbVO(userTbVO);
            userSongListVOList.add(userSongListVO);

        }
        return userSongListVOList;
    }


    private List<AlbumSongVO> getSingerAlbum(List<AlbumTb> albumTbList) {
        List<AlbumSongVO> albumTbPageVOList = new ArrayList<>();
        for (AlbumTb albumTb : albumTbList) {
            // 专辑相关信息返回
            AlbumTbVO albumTbVO = new AlbumTbVO();
            BeanUtils.copyProperties(albumTb,albumTbVO);
            albumTbVO.setIssueTime(DateUtil.dateToString(albumTb.getIssueTime()));
            List<SongTb> songTbs = songTbService.findByAlbumId(albumTb.getAlbumId());
            List<SongTbVO> songTbVOS = new ArrayList<SongTbVO>();
            for (SongTb tb : songTbs) {
                SongTbVO songTbVO = new SongTbVO();
                BeanUtils.copyProperties(tb,songTbVO);
                songTbVO.setImage(albumTbService.findByAlbumId(tb.getAlbumId()).getAlbumImg());
                songTbVO.setLabels(tb.getSingStyle().split(","));
                songTbVOS.add(songTbVO);
            }
            albumTbVO.setSongs(songTbVOS);
            AlbumSongVO albumSongVO = new AlbumSongVO();
            albumSongVO.setAlbumTbVO(albumTbVO);

            // 为albumSongVO添加歌手相关信息返回
            SingerTbVO singerTbVO = new SingerTbVO();
            SingerTb singerTb = singerTbService.findBySingerId(albumTb.getSingerId());
            BeanUtils.copyProperties(singerTb,singerTbVO);
            albumSongVO.setSingerTbVO(singerTbVO);

            albumTbPageVOList.add(albumSongVO);
        }
        return albumTbPageVOList;
    }


//    //TODO 创建排行榜
////    @ApiOperation(value = "创建排行榜", notes = "传入排行榜名和描述")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "songListName", value = "排行榜名", required = true, dataType = "String"),
//            @ApiImplicitParam(name = "songListIntro", value = "排行榜描述", dataType = "String"),
//            @ApiImplicitParam(name = "file", value = "图片", required = true, dataType = "String")
//    })
//    @PostMapping("/create/toplist")
//    public ResultVO createTopList(@RequestParam("songListName") String songListName,
//                                  @RequestParam("songListIntro") String songListIntro,
//                                  @RequestParam("file") MultipartFile multipartFile) throws IOException {
//        FileInputStream fileInputStream = (FileInputStream) multipartFile.getInputStream();
//        SongListTb songListTb = new SongListTb();
//        songListTb.setSongListName(songListName);
//        songListTb.setSongListIntro(songListIntro);
//
//        songListTb.setSongListImg(UploadUtil.commonUpload(fileInputStream, "test", "jpg"));
//
//        songListTbService.insertOne(songListTb);
//        return ResultVOUtil.success(songListTb);
//    }
//
//    //排行榜修改
//    @PostMapping(value = "/update/top/{songListId}")
//    public ResultVO updateTop(@PathVariable("songListId") Integer songListId) {
//        return ResultVOUtil.success();
//    }

    @PostMapping("/test")
    public ResultVO test(@RequestBody CarouselImgTb carouselImgTb) {
        return ResultVOUtil.success();
    }

    @PostMapping("/test/login")
    public ResultVO testLogin(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        return ResultVOUtil.success(0, username, password);
    }

    @PostMapping("/test/upload")
    public ResultVO testUpload(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        FileInputStream fileInputStream = (FileInputStream) multipartFile.getInputStream();
        return ResultVOUtil.success(UploadUtil.commonUpload(fileInputStream, multipartFile.getOriginalFilename()));
    }
}
