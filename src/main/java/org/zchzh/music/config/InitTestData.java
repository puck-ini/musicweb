package org.zchzh.music.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.zchzh.music.model.entity.song.Song;
import org.zchzh.music.service.SongService;

import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * @author zengchzh
 * @date 2021/6/12
 */

@Component
public class InitTestData implements InitializingBean {

    @Autowired
    private SongService songService;


    @Override
    public void afterPropertiesSet() throws Exception {
        createSongData();
    }

    private static final int LOOP = 100;

    private void createSongData() {
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
