package org.zchzh.music.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.zchzh.music.model.dto.PageDTO;
import org.zchzh.music.model.entity.MusicFile;
import org.zchzh.music.service.FileService;

/**
 * @author zengchzh
 * @date 2021/6/12
 */

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @GetMapping("/list")
    public PageDTO<MusicFile> list(@RequestParam("pageNum") Integer pageNum,
                                   @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize) {
        return new PageDTO<>(fileService.list(PageRequest.of(pageNum, pageSize)));
    }

    @PutMapping("/upload")
    public void upload(@RequestPart("file") MultipartFile file) {
        fileService.upload(file);
    }
}
