package org.zchzh.music.repository.impl;

import cn.hutool.core.util.StrUtil;
import io.minio.*;
import io.minio.messages.Bucket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.zchzh.music.entity.minio.MinioBucket;
import org.zchzh.music.entity.minio.MinioFile;
import org.zchzh.music.repository.MinioRepo;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zengchzh
 * @date 2021/6/8
 */

@Component
public class MinioRepoImpl implements MinioRepo {

    @Autowired
    private MinioClient minioClient;

    @Override
    public boolean exists(MinioBucket bucket) {
        try {
            return minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucket.getBucketName()).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<MinioBucket> list() {
        try {
            return minioClient.listBuckets().stream()
                    .map(bucket -> new MinioBucket(bucket.name()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public MinioBucket create(MinioBucket bucket) {
        try {
            if(!this.exists(bucket)) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucket.getBucketName()).build());
            }
            return bucket;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public MinioBucket update(MinioBucket bucket) {
        throw new UnsupportedOperationException();
    }

    @Override
    public MinioBucket remove(MinioBucket bucket) {
        try {
            if (this.exists(bucket)){
                minioClient.removeBucket(RemoveBucketArgs.builder().bucket(bucket.getBucketName()).build());
            }
            return bucket;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean exists(MinioFile file) {
        throw new UnsupportedOperationException();
//        return false;
    }

    @Override
    public MinioFile get(MinioFile file) {
        InputStream inputStream;
        try {
            inputStream = minioClient.getObject(GetObjectArgs.builder()
                    .bucket(file.getBucketName())
                    .object(file.getFileName()).build());
            file.setInputStream(inputStream);
            return file;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public MinioFile upload(MinioFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(file.getBucketName())
                    .object(file.getFileName())
                    .stream(inputStream,inputStream.available(),-1)
                    .contentType(file.getContentType()).build());
            return file;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public MinioFile download(MinioFile file, String targetPath) {
        try {
            minioClient.downloadObject(DownloadObjectArgs.builder()
                    .bucket(file.getBucketName())
                    .object(file.getFileName())
                    .filename(targetPath).build());
            return file;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public MinioFile remove(MinioFile file) {
        try {
            minioClient.removeObject(RemoveObjectArgs.builder()
                    .bucket(file.getBucketName())
                    .object(file.getFileName()).build());
            return file;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
