package org.zchzh.music.model.entity.song;

import lombok.*;
import org.zchzh.music.model.entity.BaseEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

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
public class Album extends BaseEntity {

    /**
     * 专辑名
     */
    private String name;
    /**
     * 专辑介绍
     */
    private String description;
    /**
     * 发行时间
     */
    private Date issueTime;
    /**
     * 专辑图链接
     */
    private String imgLink;
    /**
     * 播放次数
     */
    private Long playNumber;
    /**
     * 专辑中的歌
     */
    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "album", fetch = FetchType.EAGER)
    private List<Song> songs;
}
