package org.zchzh.music.entity.minio;

import lombok.Data;

import java.io.InputStream;

/**
 * @author zengchzh
 * @date 2021/6/8
 */
@Data
public class MinioFile {

    private String fileName;

    private String contentType;

    private String path;

    private String bucketName;

    private InputStream inputStream;
}
