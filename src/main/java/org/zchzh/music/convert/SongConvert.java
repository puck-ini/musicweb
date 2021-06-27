package org.zchzh.music.convert;

import org.zchzh.music.model.dto.SongDTO;
import org.zchzh.music.model.entity.song.Album;
import org.zchzh.music.model.entity.song.Mv;
import org.zchzh.music.model.entity.song.Song;
import org.zchzh.music.model.entity.song.SongData;

/**
 * @author zengchzh
 * @date 2021/6/15
 */
public class SongConvert {

    public static SongDTO toDTO(Song song) {
        return SongDTO.builder()
                .id(song.getId())
                .name(song.getName())
                .albumId(song.getAlbum().getId())
                .albumName(song.getAlbum().getName())
                .mvId(song.getMv().getId())
                .languageType(song.getLanguageType())
                .lyric(song.getLyric())
                .link(song.getLink())
                .dataId(song.getSongData().getId())
                .thumbNumber(song.getSongData().getThumbNumber())
                .playNumber(song.getSongData().getPlayNumber())
                .build();
    }

    public static Song toSong(SongDTO dto) {
        SongData songData = toSongData(dto);
        Song song =  Song.builder()
                .name(dto.getName())
                .languageType(dto.getLanguageType())
                .lyric(dto.getLyric())
                .link(dto.getLink())
                .songData(songData)
                .build();
        song.setId(dto.getId());
        return song;
    }

    public static SongData toSongData(SongDTO dto) {
        SongData songData = SongData.builder()
                .playNumber(dto.getPlayNumber())
                .thumbNumber(dto.getThumbNumber()).build();
        songData.setId(dto.getDataId());
        return songData;
    }

}
