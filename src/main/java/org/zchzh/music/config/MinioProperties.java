package org.zchzh.music.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author zengchzh
 * @date 2021/6/8
 */

@Data
@Component
@ConfigurationProperties(prefix = "minio")
public class MinioProperties {

    private String address;

    private String accessKey;

    private String secretKey;

    private String defaultBucket;
}
