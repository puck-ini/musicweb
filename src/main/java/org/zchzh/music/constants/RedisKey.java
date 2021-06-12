package org.zchzh.music.constants;

import org.zchzh.music.types.ThumbObjectType;


import java.util.HashMap;
import java.util.Map;

/**
 * @author zengchzh
 * @date 2021/6/12
 */
public class RedisKey {

    public static final String SONG_PREFIX = "song#";
    public static final String MV_PREFIX = "mv#";
    public static final String ALBUM_PREFIX = "album#";

    public static final Map<ThumbObjectType, String> PREFIX_MAP = new HashMap<>();

    static {
        PREFIX_MAP.put(ThumbObjectType.SONG, SONG_PREFIX);
        PREFIX_MAP.put(ThumbObjectType.MV, MV_PREFIX);
        PREFIX_MAP.put(ThumbObjectType.ALBUM, ALBUM_PREFIX);
    }


    public static String makeThumbKey(Long targetId, ThumbObjectType type) {
        return RedisKey.PREFIX_MAP.get(type) + targetId;
    }
}
