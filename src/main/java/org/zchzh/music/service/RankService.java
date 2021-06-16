package org.zchzh.music.service;


import org.zchzh.music.model.entity.Rank;
import org.zchzh.music.types.RankSort;

/**
 * @author zengchzh
 * @date 2021/6/15
 */
public interface RankService {


    /**
     * 创建排行榜
     * @param rank 排行榜
     * @return 返回创建的排行榜
     */
    Rank create(Rank rank);

    /**
     * 更新排行榜
     * @param rank 更新的信息
     * @return 返回更新的排行榜
     */
    Rank update(Rank rank);

    /**
     * 获取排行榜
     * @param id 排行榜key
     * @return 返回获取的排行榜
     */
    Rank get(String id);

    /**
     * 获取排行榜
     * @param id 排行榜key
     * @param start 开始索引
     * @param end 结束索引
     * @return 返回获取的排行榜
     */
    Rank getAsc(String id, long start, long end);
    /**
     * 获取排行榜
     * @param id 排行榜key
     * @param start 开始索引
     * @param end 结束索引
     * @return 返回获取的排行榜
     */
    Rank getDesc(String id, long start, long end);

    /**
     * 获取排行榜
     * @param id 排行榜key
     * @param start 开始索引
     * @param end 结束索引
     * @param sort 排序方式
     * @return 返回获取的排行榜
     */
    Rank get(String id, long start, long end, RankSort sort);
}
