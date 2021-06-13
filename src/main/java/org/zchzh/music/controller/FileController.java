package org.zchzh.music.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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

    @PutMapping("/upload")
    public void upload(@RequestPart("file") MultipartFile file) {
        fileService.upload(file);
    }
}
