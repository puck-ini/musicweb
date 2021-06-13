package org.zchzh.music.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.zchzh.music.config.MinioProperties;
import org.zchzh.music.convert.FileConvert;
import org.zchzh.music.model.entity.MusicFile;
import org.zchzh.music.model.entity.minio.MinioFile;
import org.zchzh.music.repository.MinioRepo;
import org.zchzh.music.repository.MusicFileRepo;
import org.zchzh.music.service.FileService;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zengchzh
 * @date 2021/6/12
 */
@Service
public class FileServiceImpl extends AbstractCrudService<MusicFile, Long> implements FileService {

    private final MusicFileRepo fileRepo;

    private final MinioRepo minioRepo;

    private final HttpServletRequest request;

    private final MinioProperties minioProp;

    protected FileServiceImpl(MusicFileRepo fileRepo,
                              MinioRepo minioRepo,
                              HttpServletRequest request,
                              MinioProperties minioProp) {
        super(fileRepo);
        this.fileRepo = fileRepo;
        this.minioRepo = minioRepo;
        this.request = request;
        this.minioProp = minioProp;
    }

    @Override
    public MusicFile upload(MultipartFile file) {
        Long userId = getUserId(request);

        MinioFile minioFile = FileConvert.toMinioFile(file);
        minioFile.setBucketName(minioProp.getDefaultBucket());
        MusicFile musicFile = FileConvert.toMusicFile(file);
        musicFile.setUserId(userId);
        musicFile.setUrl(buildUrl(minioFile));
        minioFile.setFileName(musicFile.getFullName());

        minioRepo.upload(minioFile);
        return fileRepo.save(musicFile);
    }

    private Long getUserId(HttpServletRequest request) {
        return 0L;
    }

    private String buildUrl(MinioFile file) {
        return minioProp.getAddress() + "/" + file.getFileName();
    }


    @Override
    public void download() {

    }



}
