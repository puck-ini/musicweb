package org.zchzh.music.types;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author zengchzh
 * @date 2021/6/12
 */

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTargetId implements Serializable {

    /**
     * 点赞的用户id
     */
    private Long userId;
    /**
     * 点赞的对象id
     */
    private Long targetId;
}
