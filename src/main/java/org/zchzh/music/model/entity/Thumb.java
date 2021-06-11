package org.zchzh.music.model.entity;

import lombok.Data;
import org.zchzh.music.types.ThumbType;

import javax.persistence.*;

/**
 * @author zengchzh
 * @date 2021/6/8
 */
@Data
@Entity
public class Thumb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long targetId;

    @Enumerated(EnumType.ORDINAL)
    private ThumbType type;

}
