package org.zchzh.music.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import org.zchzh.music.model.entity.Rank;
import org.zchzh.music.service.RankService;
import org.zchzh.music.types.RankSort;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zengchzh
 * @date 2021/6/16
 */
@Service
public class RankServiceImpl implements RankService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private static final Map<String, String> RANK_CACHE = new ConcurrentHashMap<>();

    private static final long START = 0L;
    private static final long END = 10L;


    @Override
    public void create(Rank rank) {
        String key = rank.getId();
        redisTemplate.opsForZSet().add(key, toRedisSet(rank.getMap()));
        RANK_CACHE.putIfAbsent(key, rank.getDesc());
    }

    @Override
    public Rank get(String id) {
        return getDesc(id, START, END);
    }

    @Override
    public Rank getAsc(String id, long start, long end) {
        return get(id, start, end, RankSort.ASC);
    }

    @Override
    public Rank getDesc(String id, long start, long end) {
        return get(id, start, end, RankSort.DESC);
    }

    @Override
    public Rank get(String id, long start, long end, RankSort sort) {
        Map<String, Double> map = new HashMap<>();
        if (sort == RankSort.ASC) {
            map = toMap(redisTemplate.opsForZSet().rangeWithScores(id, start, end));
        }else if (sort == RankSort.DESC) {
            map = toMap(redisTemplate.opsForZSet().reverseRangeWithScores(id, start, end));
        }
        return Rank.builder().id(id).desc(RANK_CACHE.get(id)).map(map).sort(sort).build();
    }


    private Set<ZSetOperations.TypedTuple<String>> toRedisSet(Map<String, Double> map) {
        List<ZSetOperations.TypedTuple<String>> list = new ArrayList<>();
        for (Map.Entry<String, Double> entry : map.entrySet()) {
            ZSetOperations.TypedTuple<String> tuple = new DefaultTypedTuple<String>(entry.getKey(), entry.getValue());
            list.add(tuple);
        }
        return new HashSet<>(list);
    }

    private Map<String, Double> toMap(Set<ZSetOperations.TypedTuple<String>> set) {
        Map<String, Double> map = new HashMap<>();
        if (Objects.isNull(set)) {
            return map;
        }
        for (ZSetOperations.TypedTuple<String> item : set) {
            map.put(item.getValue(), item.getScore());
        }
        return map;
    }
}
