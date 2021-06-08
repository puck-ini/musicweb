package org.zchzh.music.repository;


import org.zchzh.music.entity.minio.MinioBucket;
import org.zchzh.music.entity.minio.MinioFile;

import java.util.List;

/**
 * @author zengchzh
 * @date 2021/6/8
 */

public interface MinioRepo {

    /**
     * 判断 bucket 是否存在
     * @param bucket bucket 对象
     * @return 如果存在返回 true
     */
    boolean exists(MinioBucket bucket);

    /**
     * 列出所有 bucket
     * @return 返回 bucket 列表
     */
    List<MinioBucket> list();
    /**
     * 创建 bucket
     * @param bucket bucket 对象
     * @return 返回创建的 bucket 对象
     */
    MinioBucket create(MinioBucket bucket);

    /**
     * 更新 bucket
     * @param bucket bucket 对象
     * @return 返回更新的 bucket 对象
     */
    MinioBucket update(MinioBucket bucket);

    /**
     * 删除 bucket
     * @param bucket bucket 对象
     * @return 返回删除的 bucket
     */
    MinioBucket remove(MinioBucket bucket);

    /**
     * 判断 file 是否存在
     * @param file 文件对象
     * @return 存在返回 true
     */
    boolean exists(MinioFile file);

    /**
     * 获取一个 file 对象
     * @param file 文件对象
     * @return 返回获取到的文件
     */
    MinioFile get(MinioFile file);
    /**
     * 创建文件
     * @param file 文件对象
     * @return 返回创建的 file 对象
     */
    MinioFile upload(MinioFile file);

    /**
     * 下载文件
     * @param file 文件对象
     * @param targetPath 下载到的路径
     * @return 返回下载的文件对象
     */
    MinioFile download(MinioFile file, String targetPath);

    /**
     * 删除文件
     * @param file 文件对象
     * @return 返回删除的文件
     */
    MinioFile remove(MinioFile file);

}
