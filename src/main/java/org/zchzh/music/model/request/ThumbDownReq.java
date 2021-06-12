package org.zchzh.music.model.request;

import lombok.Data;
import org.zchzh.music.types.ThumbObjectType;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author zengchzh
 * @date 2021/6/12
 */
@Data
public class ThumbDownReq implements Serializable {


    @NotNull
    private Long targetId;

    @NotNull
    private ThumbObjectType type;
}
