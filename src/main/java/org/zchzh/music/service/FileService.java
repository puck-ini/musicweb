package org.zchzh.music.service;

import org.springframework.web.multipart.MultipartFile;
import org.zchzh.music.model.entity.MusicFile;

/**
 * @author zengchzh
 * @date 2021/6/12
 */
public interface FileService extends BaseCrudService<MusicFile, Long>{
    /**
     * 上传文件
     * @param file 文件数据
     * @return 返回上传的文件信息
     */
    MusicFile upload(MultipartFile file);

    /**
     * 下载文件
     */
    void download();
}
