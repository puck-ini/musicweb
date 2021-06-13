package org.zchzh.music.model.entity;

import lombok.*;

import javax.persistence.Entity;

/**
 * @author zengchzh
 * @date 2021/6/7
 */

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MusicFile extends BaseEntity {

    /**
     * 文件新名
     */
    private String fileName;
    /**
     * 原始名
     */
    private String originName;
    /**
     * 后缀名
     */
    private String suffix;
    /**
     * 内容类型
     */
    private String contentType;
    /**
     * 文件类型
     */
    private String type;
    /**
     * 保存路径
     */
    private String path;
    /**
     * 链接
     */
    private String url;
    /**
     * 文件大小
     */
    private Long size;
    /**
     * 上传者
     */
    private Long userId;


    public String getFullName() {
        return fileName + "." + suffix;
    }

}
