package org.zchzh.music.service;

import org.zchzh.music.model.entity.Thumb;
import org.zchzh.music.types.ThumbObjectType;
import org.zchzh.music.types.UserTargetId;

/**
 * @author zengchzh
 * @date 2021/6/12
 */
public interface ThumbService extends BaseCrudService<Thumb, UserTargetId> {

    /**
     * 点赞
     * @param id 操作的对象id
     * @param type 点赞的对象类型
     */
    void up(UserTargetId id, ThumbObjectType type);

    /**
     * 点踩
     * @param id 操作的对象id
     * @param type 点赞的对象类型
     */
    void down(UserTargetId id, ThumbObjectType type);

    /**
     * 获取点赞总数
     * @param targetId 操作的对象id
     * @param type 点赞的对象类型
     * @return 返回统计的总数
     */
    long countUp(Long targetId, ThumbObjectType type);



}
