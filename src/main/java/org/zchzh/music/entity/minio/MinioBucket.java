package org.zchzh.music.entity.minio;

import lombok.Data;

/**
 * @author zengchzh
 * @date 2021/6/8
 */
@Data
public class MinioBucket {

    private String bucketName;

    public MinioBucket() {}

    public MinioBucket(String bucketName) {
        this.bucketName = bucketName;
    }
}
