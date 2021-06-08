package org.zchzh.music.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author zengchzh
 * @date 2021/6/8
 */
@Configuration
public class MinioConfig {

    @Autowired
    private MinioProperties prop;


    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(prop.getAddress())
                .credentials(prop.getAccessKey(), prop.getSecretKey())
                .build();
    }
}
