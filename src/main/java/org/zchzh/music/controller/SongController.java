package org.zchzh.music.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zchzh.music.model.dto.PageDTO;
import org.zchzh.music.model.dto.SongDTO;
import org.zchzh.music.model.entity.song.Song;
import org.zchzh.music.service.SongService;

/**
 * @author zengchzh
 * @date 2021/6/12
 */

@RestController
@RequestMapping("/song")
public class SongController {

    @Autowired
    private SongService songService;


    @GetMapping("/list")
    public PageDTO<SongDTO> list(@RequestParam("pageNum") Integer pageNum,
                                 @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize) {
        return songService.list(pageNum, pageSize);
    }

}
