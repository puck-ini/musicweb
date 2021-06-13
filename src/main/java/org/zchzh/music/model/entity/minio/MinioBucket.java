package org.zchzh.music.model.entity.minio;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zengchzh
 * @date 2021/6/8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MinioBucket {

    private String bucketName;
}
