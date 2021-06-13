package org.zchzh.music.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.zchzh.music.model.entity.minio.MinioBucket;
import org.zchzh.music.model.entity.song.Song;
import org.zchzh.music.repository.MinioRepo;
import org.zchzh.music.service.SongService;

import java.util.Date;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * @author zengchzh
 * @date 2021/6/12
 */

@Slf4j
@Component
public class InitTestData implements InitializingBean {

    @Autowired
    private MinioRepo minioRepo;

    @Autowired
    private MinioProperties minioProp;

    @Autowired
    private SongService songService;


    @Override
    public void afterPropertiesSet() throws Exception {
        createDefaultBucket();
        createSongData();
    }


    private void createDefaultBucket() {
        log.info("init minio default bucket - {}", new Date());
        minioRepo.create(MinioBucket.builder().bucketName(minioProp.getDefaultBucket()).build());
    }

    private static final int LOOP = 100;

    private void createSongData() {
        log.info("init song test data - {}", new Date());
        LongStream.rangeClosed(0, LOOP).forEach(i -> {
            songService.create(
                    Song.builder()
                            .name("song-" + i)
                            .albumId(i)
                            .mvId(i)
                            .lyric("lyric-" + i)
                            .link("link-" + i)
                            .playNumber(0L)
                            .thumbNumber(0L).build()
            );
        });
    }
}
