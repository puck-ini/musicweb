package org.zchzh.music.repository;

import org.zchzh.music.entity.newentity.MusicUser;

import java.util.List;

/**
 * @author zengchzh
 * @date 2021/6/8
 */
public interface MusicUserRepo extends BaseRepo<MusicUser, Long> {
    List<MusicUser> findAllByLoginName(String loginName);
}
