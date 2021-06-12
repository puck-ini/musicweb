package org.zchzh.music.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.zchzh.music.types.CommentType;
import org.zchzh.music.types.ThumbObjectType;
import org.zchzh.music.types.ThumbType;
import org.zchzh.music.types.UserTargetId;

import javax.persistence.*;

/**
 * @author zengchzh
 * @date 2021/6/8
 */
@DynamicUpdate
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Thumb {

    @EmbeddedId
    private UserTargetId userTargetId;

    @Enumerated(EnumType.STRING)
    private ThumbType thumbType;
    /**
     * 点赞类型
     */
    @Enumerated(EnumType.STRING)
    private ThumbObjectType thumbObjectType;

}
