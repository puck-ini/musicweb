package org.zchzh.music.entity.newentity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.zchzh.music.entity.newentity.BaseEntity;

import javax.persistence.Entity;

/**
 * @author zengchzh
 * @date 2021/6/7
 */

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class MusicFile extends BaseEntity {

    private String fileName;

    private String originName;

    private String suffix;

    private String contentType;

    private String type;

    private String path;

    private String url;

    private Integer size;

}
