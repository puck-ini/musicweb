package org.zchzh.music.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.zchzh.music.types.CommentType;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * @author zengchzh
 * @date 2021/6/8
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Comment extends BaseEntity {

    /**
     * 评论内容
     */
    private String content;
    /**
     * 评论类型
     */
    @Enumerated(EnumType.ORDINAL)
    private CommentType commentType;
    /**
     * 评论的用户id
     */
    private Long userId;
    /**
     * 评论的对象id
     */
    private Long targetId;
    /**
     * 点赞数
     */
    private Long thumbNumber;

}
