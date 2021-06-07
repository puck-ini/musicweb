package org.zchzh.music.service;

import org.zchzh.music.entity.AttentionTb;

import java.util.List;

public interface AttentionTbService {

    List<AttentionTb> findByUserId(Integer userId);

    Integer insertOne(AttentionTb attentionTb);

    Integer deleteByAttentionId(Integer attentionId);

    AttentionTb findByUserIdAndUserAttentionId(Integer userId,Integer userAttentionId);
}
