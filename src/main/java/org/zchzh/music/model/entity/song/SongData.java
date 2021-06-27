package org.zchzh.music.model.entity.song;

import lombok.*;
import org.zchzh.music.model.entity.BaseEntity;

import javax.persistence.*;

/**
 * @author zengchzh
 * @date 2021/6/15
 */

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SongData extends BaseEntity {

    @ToString.Exclude
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "songData")
    private Song song;
    /**
     * 点赞数
     */
    private Long thumbNumber;
    /**
     * 播放数
     */
    private Long playNumber;
}
