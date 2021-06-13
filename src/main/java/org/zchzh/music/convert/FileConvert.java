package org.zchzh.music.convert;

import org.springframework.web.multipart.MultipartFile;
import org.zchzh.music.exception.CommonException;
import org.zchzh.music.model.entity.MusicFile;
import org.zchzh.music.model.entity.minio.MinioFile;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

/**
 * @author zengchzh
 * @date 2021/6/13
 */
public class FileConvert {

    public static final String DEFAULT_BUCKET = "test";


    public static MinioFile toMinioFile(MultipartFile file) {
        try {
            return MinioFile.builder()
                    .fileName(file.getOriginalFilename())
                    .contentType(file.getContentType())
//                    .bucketName(DEFAULT_BUCKET)
                    .inputStream(file.getInputStream())
                    .build();
        } catch (IOException e) {
            e.printStackTrace();
            throw new CommonException("转换MinioFile失败");
        }
    }

    public static MusicFile toMusicFile(MultipartFile file) {
        return MusicFile.builder()
                .fileName(getSimpleName(file))
                .originName(file.getOriginalFilename())
                .suffix(getSuffix(file))
                .contentType(file.getContentType())
                .size(file.getSize())
                .build();
    }

    private static final String UNKNOWN  = "unknown";

    private static String getRandomName() {
        return UUID.randomUUID().toString();
    }

    private static String getSimpleName(MultipartFile file) {
        String[] arr = getFileNameArray(file);
        return arr.length == 0 ? UNKNOWN : arr[0];
    }

    private static String getSuffix(MultipartFile file) {
        String[] arr = getFileNameArray(file);
        int len = arr.length;
        return len == 0 ? UNKNOWN : arr[len - 1];
    }

    private static String[] getFileNameArray(MultipartFile file) {
        return Objects.isNull(file.getOriginalFilename())
                ? new String[0]
                : file.getOriginalFilename().split("\\.");
    }



}
