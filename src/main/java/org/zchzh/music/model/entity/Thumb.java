package org.zchzh.music.model.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.zchzh.music.types.CommentType;
import org.zchzh.music.types.ThumbObjectType;
import org.zchzh.music.types.ThumbType;

import javax.persistence.*;

/**
 * @author zengchzh
 * @date 2021/6/8
 */
@DynamicUpdate
@Data
@Entity
public class Thumb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 点赞的用户id
     */
    private Long userId;
    /**
     * 点赞的对象id
     */
    private Long targetId;

    @Enumerated(EnumType.ORDINAL)
    private ThumbType thumbType;
    /**
     * 点赞类型
     */
    @Enumerated(EnumType.ORDINAL)
    private ThumbObjectType thumbObjectType;

}
