package org.zchzh.music.model.entity.song;

import lombok.*;
import org.zchzh.music.model.entity.BaseEntity;
import org.zchzh.music.types.LanguageType;

import javax.persistence.*;

/**
 * @author zengchzh
 * @date 2021/6/11
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(callSuper = true)
public class Song extends BaseEntity {

    /**
     * 歌曲名
     */
    private String name;
//    /**
//     * 专辑id
//     */
//    private Long albumId;
//    /**
//     * mv id
//     */
//    private Long mvId;
    /**
     * 语言类型
     */
    @Enumerated(EnumType.STRING)
    private LanguageType languageType;
    /**
     * 歌词
     */
    private String lyric;
    /**
     * 歌曲文件连接
     */
    private String link;
//    /**
//     * 统计数据外键id
//     */
//    private Long dataId;
    /**
     * 统计数据
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private SongData songData;
    /**
     * mv
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Mv mv;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Album album;

}
