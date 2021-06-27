package org.zchzh.music.service.impl;

import org.springframework.stereotype.Service;
import org.zchzh.music.model.entity.song.Album;
import org.zchzh.music.repository.AlbumRepo;
import org.zchzh.music.repository.BaseRepo;
import org.zchzh.music.service.AlbumService;

/**
 * @author zengchzh
 * @date 2021/6/27
 */
@Service
public class AlbumServiceImpl extends AbstractCrudService<Album, Long> implements AlbumService {

    private final AlbumRepo albumRepo;

    protected AlbumServiceImpl(AlbumRepo albumRepo) {
        super(albumRepo);
        this.albumRepo = albumRepo;
    }
}
