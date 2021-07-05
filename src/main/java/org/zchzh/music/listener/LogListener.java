package org.zchzh.music.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.zchzh.music.model.entity.LogEntity;
import org.zchzh.music.event.LogEvent;
import org.zchzh.music.repository.LogEntityRepo;

/**
 * @author zengchzh
 * @date 2021/6/8
 */

@Slf4j
@Component
public class LogListener {

    @Autowired
    private LogEntityRepo repo;

    @Async
    @Order
    @EventListener(LogEvent.class)
    public void saveDemoLog(LogEvent event) {
        LogEntity logEntity = event.getLogEntity();
        log.info(logEntity.toString());
        repo.save(logEntity);
    }
}
