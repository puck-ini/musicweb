package org.zchzh.music.dao;

import org.zchzh.music.entity.AttentionTb;

import java.util.List;

public interface AttentionTbMapper {

    List<AttentionTb> findByUserId(Integer userId);

    AttentionTb findByAttentionId(Integer attentionId);

    int insertOne(AttentionTb attentionTb);

    int deleteByAttentionId(Integer attentionId);

    int updateByAttentionId(Integer attentionId);

    AttentionTb findByUserIdAndUserAttentionId(Integer userId,Integer userAttentionId);
}