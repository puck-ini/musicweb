package org.zchzh.music.model.request;

import lombok.Data;
import org.zchzh.music.types.CommentType;

import java.io.Serializable;

/**
 * @author zengchzh
 * @date 2021/6/14
 */
@Data
public class CommentListReq implements Serializable {

    private Long targetId;

    private CommentType type;

    private Integer pageNum;

    private Integer pageSize;
}
