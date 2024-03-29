package org.zchzh.music.model.entity;

import lombok.*;
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
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment extends BaseEntity {

    /**
     * 评论内容
     */
    private String content;
    /**
     * 评论类型
     */
    @Enumerated(EnumType.STRING)
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
