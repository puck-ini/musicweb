package org.zchzh.music.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.zchzh.music.types.LanguageType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

/**
 * @author zengchzh
 * @date 2021/6/15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SongDTO implements Serializable {

    private Long id;
    /**
     * 歌曲名
     */
    private String name;
    /**
     * 专辑id
     */
    private Long albumId;
    /**
     * 专辑名
     */
    private String albumName;
    /**
     * mv id
     */
    private Long mvId;
    /**
     * 语言类型
     */
    private LanguageType languageType;
    /**
     * 歌词
     */
    private String lyric;
    /**
     * 歌曲文件连接
     */
    private String link;
    /**
     * 统计数据外键id
     */
    private Long dataId;
    /**
     * 点赞数
     */
    private Long thumbNumber;
    /**
     * 播放数
     */
    private Long playNumber;
}
