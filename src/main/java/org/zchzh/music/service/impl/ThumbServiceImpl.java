package org.zchzh.music.service.impl;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.zchzh.music.constants.RedisKey;
import org.zchzh.music.model.entity.Thumb;
import org.zchzh.music.repository.ThumbRepo;
import org.zchzh.music.service.ThumbService;
import org.zchzh.music.types.ThumbObjectType;
import org.zchzh.music.types.ThumbType;
import org.zchzh.music.types.UserTargetId;
import org.zchzh.music.utils.RedisUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author zengchzh
 * @date 2021/6/12
 */
@Service
public class ThumbServiceImpl extends AbstractCrudService<Thumb, UserTargetId> implements ThumbService {

    private final ThumbRepo thumbRepo;

    private final RedisUtil redisUtil;

    protected ThumbServiceImpl(ThumbRepo thumbRepo, RedisUtil redisUtil) {
        super(thumbRepo);
        this.thumbRepo = thumbRepo;
        this.redisUtil = redisUtil;
    }

    @Override
    public void up(UserTargetId id, ThumbObjectType type) {
        Thumb thumb = get(id).orElse(
                Thumb.builder().userTargetId(id).thumbObjectType(type).thumbType(ThumbType.UP).build()
        );
        thumbRepo.save(thumb);
        cacheThumb(thumb);
    }

    @Override
    public void down(UserTargetId id, ThumbObjectType type) {
        Thumb thumb = get(id).orElse(
                Thumb.builder().userTargetId(id).thumbObjectType(type).thumbType(ThumbType.DOWN).build()
        );
        thumbRepo.save(thumb);
        cacheThumb(thumb);
    }

    @Override
    public long countUp(Long targetId, ThumbObjectType type) {
        String key = RedisKey.makeThumbKey(targetId, type);
        Map<Object, Object> map = redisUtil.getHashMap(key);
        List<Object> list = (List<Object>) map.values();
        if (list.size() == 0) {
            Example<Thumb> example = Example.of(
                    Thumb.builder()
                            .userTargetId(new UserTargetId(null, targetId))
                            .thumbObjectType(type)
                            .build()
            );
            List<Thumb> thumbList = thumbRepo.findAll(example);
            thumbList.forEach(this::cacheThumb);
            return thumbList.stream().filter(item -> item.getThumbType() == ThumbType.UP).count();
        }
        return list.stream().filter(item -> item == ThumbType.UP).count();
    }

    private void cacheThumb(Thumb thumb) {
        String key = RedisKey.makeThumbKey(thumb.getUserTargetId().getTargetId(), thumb.getThumbObjectType());
        Long userId = thumb.getUserTargetId().getUserId();
        redisUtil.setHash(key, String.valueOf(userId), thumb.getThumbType());
    }
}
