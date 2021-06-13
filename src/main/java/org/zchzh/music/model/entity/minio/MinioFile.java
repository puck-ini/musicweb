package org.zchzh.music.model.entity.minio;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.InputStream;

/**
 * @author zengchzh
 * @date 2021/6/8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MinioFile {

    private String fileName;

    private String contentType;

    private String path;

    private String bucketName;

    private InputStream inputStream;
}
