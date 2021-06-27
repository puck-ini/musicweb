package org.zchzh.music.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.zchzh.music.convert.SongConvert;
import org.zchzh.music.exception.CommonException;
import org.zchzh.music.model.dto.PageDTO;
import org.zchzh.music.model.dto.SongDTO;
import org.zchzh.music.model.entity.song.Album;
import org.zchzh.music.model.entity.song.Song;
import org.zchzh.music.model.entity.song.SongData;
import org.zchzh.music.repository.AlbumRepo;
import org.zchzh.music.repository.SongDataRepo;
import org.zchzh.music.repository.SongRepo;
import org.zchzh.music.service.SongService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zengchzh
 * @date 2021/6/12
 */
@Service
public class SongServiceImpl extends AbstractCrudService<Song, Long> implements SongService {

    private final SongRepo songRepo;

    private final SongDataRepo songDataRepo;

    private final AlbumRepo albumRepo;


    protected SongServiceImpl(SongRepo songRepo, SongDataRepo songDataRepo, AlbumRepo albumRepo) {
        super(songRepo);
        this.songRepo = songRepo;
        this.songDataRepo = songDataRepo;
        this.albumRepo = albumRepo;
    }


    @Override
    public PageDTO<SongDTO> list(Integer pageNum, Integer pageSize) {
        Page<Song> songPage = list(PageRequest.of(pageNum, pageSize));
        List<SongDTO> songDTOList = songPage.getContent().stream().map(SongConvert::toDTO).collect(Collectors.toList());
        return PageDTO.<SongDTO>builder()
                .currentPage(pageNum)
                .currentSize(pageSize)
                .total(songPage.getTotalElements())
                .totalPages(songPage.getTotalPages())
                .content(songDTOList)
                .build();
    }
}
