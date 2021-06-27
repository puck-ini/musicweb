package org.zchzh.music.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.zchzh.music.model.entity.minio.MinioBucket;
import org.zchzh.music.model.entity.song.Album;
import org.zchzh.music.model.entity.song.Mv;
import org.zchzh.music.model.entity.song.Song;
import org.zchzh.music.model.entity.song.SongData;
import org.zchzh.music.repository.MinioRepo;
import org.zchzh.music.service.AlbumService;
import org.zchzh.music.service.SongService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

    @Autowired
    private AlbumService albumService;


    @Override
    public void afterPropertiesSet() throws Exception {
        createDefaultBucket();
        createDefaultData();
        printData();
    }


    private void createDefaultBucket() {
        log.info("init minio default bucket - {}", new Date());
        minioRepo.create(MinioBucket.builder().bucketName(minioProp.getDefaultBucket()).build());
    }

    private static final int LOOP = 100;

    private void createDefaultData() {
        log.info("init song test data - {}", new Date());
        List<Album> albums = new ArrayList<>();
        IntStream.rangeClosed(1, 10).forEach(i -> {
            albums.add(Album.builder()
                    .name("test_album_" + i)
                    .description("123456 desc")
                    .issueTime(new Date())
                    .imgLink("localhost")
                    .playNumber(0L)
                    .songs(new ArrayList<>())
                    .build());
        });

//        albums.forEach(item -> log.info(item.toString()));
        IntStream.rangeClosed(1, LOOP).forEach(i -> {
            SongData songData = SongData.builder().playNumber(0L).thumbNumber(0L).build();
            Mv mv = Mv.builder().playNumber(0L).link("localhost").build();
            Song song = Song.builder()
                    .name("song-" + i)
                    .songData(songData)
                    .mv(mv)
                    .album(albums.get(i % albums.size()))
                    .lyric("lyric-" + i)
                    .link("link-" + i)
                    .build();
            albums.get(i % albums.size()).getSongs().add(song);
//            songService.create(song);
        });
        albumService.createAll(albums);
        albumService.flush();
    }

    private void printData() {
        albumService.list().forEach(i -> log.info(i.toString()));
        songService.list().forEach(i -> log.info(i.toString()));
    }
}
