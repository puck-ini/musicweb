package org.zchzh.music.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zchzh.music.entity.LogEntity;

/**
 * @author zengchzh
 * @date 2021/6/8
 */
public interface LogEntityRepo extends JpaRepository<LogEntity, Long> {
}
