package org.zchzh.music.model.entity.song;

import lombok.*;
import org.zchzh.music.model.entity.BaseEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

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
public class Mv extends BaseEntity {

    @ToString.Exclude
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "mv")
    private Song song;
    /**
     * 播放次数
     */
    private Long playNumber;
    /**
     * mv链接
     */
    private String link;
}
