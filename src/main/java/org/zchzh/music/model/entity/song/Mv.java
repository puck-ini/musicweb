package org.zchzh.music.model.entity.song;

import lombok.*;
import org.zchzh.music.model.entity.BaseEntity;

import javax.persistence.Entity;

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

    /**
     * 播放次数
     */
    private Long playNumber;
}
