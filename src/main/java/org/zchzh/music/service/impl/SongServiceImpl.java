package org.zchzh.music.service.impl;

import org.springframework.stereotype.Service;
import org.zchzh.music.model.entity.song.Song;
import org.zchzh.music.repository.BaseRepo;
import org.zchzh.music.repository.SongRepo;
import org.zchzh.music.service.SongService;

/**
 * @author zengchzh
 * @date 2021/6/12
 */
@Service
public class SongServiceImpl extends AbstractCrudService<Song, Long> implements SongService {

    private final SongRepo songRepo;


    protected SongServiceImpl(SongRepo songRepo) {
        super(songRepo);
        this.songRepo = songRepo;
    }
}
