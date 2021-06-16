package org.zchzh.music.service;

import org.zchzh.music.model.dto.PageDTO;
import org.zchzh.music.model.dto.SongDTO;
import org.zchzh.music.model.entity.song.Song;

/**
 * @author zengchzh
 * @date 2021/6/12
 */
public interface SongService extends BaseCrudService<Song, Long> {

    /**
     * 封装dto
     * @param pageNum 页数
     * @param pageSize 一页记录数
     * @return dto
     */
    PageDTO<SongDTO> list(Integer pageNum, Integer pageSize);

}
