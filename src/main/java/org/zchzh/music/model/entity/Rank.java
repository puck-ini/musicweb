package org.zchzh.music.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.zchzh.music.types.RankSort;

import java.util.Map;

/**
 * @author zengchzh
 * @date 2021/6/16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rank {

    private String id;

    private String desc;

    private Map<String, Double> map;

    private RankSort sort;
}
